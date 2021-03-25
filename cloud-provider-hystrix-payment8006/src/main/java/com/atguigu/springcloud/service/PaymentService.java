package com.atguigu.springcloud.service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

@Service
public class PaymentService
{

    public String paymentInfo_OK(Integer id)
    {
        System.out.println("Thread:" + Thread.currentThread().getName() + " execute [PaymentService.paymentInfo_OK]");
        return "Thread: [" + Thread.currentThread().getName() + "], function: [PaymentService.paymentInfo_OK], parameter=[id=" + id + "]";
    }

    @HystrixCommand(//If something wrong, who can help me
            fallbackMethod = "paymentInfo_TimeOutHandler",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")//设置线程超时等待
    })
    public String paymentInfo_TimeOut(Integer id)
    {
        try
        {
            TimeUnit.SECONDS.sleep(5);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        System.out.println("Thread:" + Thread.currentThread().getName() + " execute [PaymentService.paymentInfo_TimeOut]");
        return "Thread: [" + Thread.currentThread().getName() + "], function: [PaymentService.paymentInfo_TimeOut], parameter=[id=" + id + "]";
    }

    public String paymentInfo_TimeOutHandler(Integer id)//参数要完全一致
    {
        return "Server [PaymentService.paymentInfo_TimeOut] fallback handler, id = " + id;
    }

    //=====服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数, 以最近10秒为一个单位
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期，在 10 秒后再重连
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸，10秒里面有60% 是失败的话
    })
    public String paymentCircuitBreaker(@PathVariable("id") Integer id)
    {
        if (id < 0)
        {
            throw new RuntimeException("******id 不能负数");
        }

        return "Thread: [" + Thread.currentThread().getName() + "] serialNumber = " + UUID.randomUUID().toString();
    }

    public String paymentCircuitBreaker_fallback(@PathVariable("id") Integer id)
    {
        return "Server [PaymentService][paymentCircuitBreaker_fallback] id=" + id;
    }

}