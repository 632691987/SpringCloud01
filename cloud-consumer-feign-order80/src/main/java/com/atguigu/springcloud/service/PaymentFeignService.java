package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")//这个 @FeignClient 之能够放在 interface 上面
public interface PaymentFeignService
{
    /**
     * 一定要添加这个 @GetMapping, 否则调用不起来
     */
    @GetMapping(value = "/payment/get/{id}")
    CommonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    /**
     * 一定要添加这个 @GetMapping, 否则调用不起来
     */
    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();
}
