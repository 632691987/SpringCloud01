package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@DefaultProperties(defaultFallback = "payment_Global_FallbackMethod")
public class OrderHystirxController
{
    @Resource
    private PaymentHystrixService paymentHystrixService;

    @Resource(name = "restTemplate")
    private RestTemplate restTemplate;

    @GetMapping("/consumer/payment/hystrix/ok/{id}")
    public String paymentInfo_OK(@PathVariable("id") Integer id)
    {
        return paymentHystrixService.paymentInfo_OK(id);
    }

    @GetMapping("/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod",commandProperties = {//This is second level
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value="1500")
    })
    public String paymentInfo_TimeOut(@PathVariable("id") Integer id)
    {
        String result = paymentHystrixService.paymentInfo_TimeOut(id);
        return result;
    }

    @GetMapping("/consumer/hystrix/{times}")
    public ResponseEntity callHystrixBoard(@PathVariable @Valid @Min(1) int times) {
        String hystrixBoardServiceName = "http://cloud-provider-hystrix-payment";
        Map<String, Object> urlVariables = new HashMap<>();

        int success = 0;
        int fail = 0;

        for (int index = 0; index < times; index ++) {
            int id = new Random().nextInt(10) - 5;
            urlVariables.put("id", id);
            if (id < 0) {
                fail++;
            } else {
                success++;
            }
            restTemplate.getForObject(hystrixBoardServiceName+"/payment/circuit/{id}", String.class, urlVariables);
        }

        return new ResponseEntity<>("Success:" + success + ", fail:" + fail, HttpStatus.ACCEPTED);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id)
    {
        return "[OrderHystirxController][paymentTimeOutFallbackMethod], id = " + id;
    }

    // This is global
    // This is the third level
    public String payment_Global_FallbackMethod()
    {
        return "[OrderHystirxController][payment_Global_FallbackMethod], Global level fallback";
    }
}