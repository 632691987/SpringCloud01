package com.atguigu.springcloud.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.PaymentService;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PaymentController
{
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/create")
    public CommonResult create(@RequestBody  Payment payment) {
        int result = paymentService.create(payment);
        log.info("Result:" + result);
        return new CommonResult(result > 0 ? HttpStatus.OK.value(): HttpStatus.NO_CONTENT.value(), "", result);
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentById(@PathVariable("id") Long id)
    {
        Payment payment = paymentService.getPaymentById(id);
        log.info("From server port:" + serverPort);

        if(payment != null)
        {
            return new CommonResult(HttpStatus.OK.value(),"Query success, port = " + serverPort,payment);
        }else{
            return new CommonResult(HttpStatus.NOT_FOUND.value(),"没有对应记录,查询ID: "+id,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClient.getServices();//获取所有注册过的服务
        log.info("=================================================");
        log.info("Part 1: =========================================");
        for (String service : services) {
            log.info("**** Service = " + service);
        }
        log.info("Part 2: =========================================");
        List<ServiceInstance> list = discoveryClient.getInstances("cloud-payment-service".toUpperCase());
        log.info("=========================================");
        for (ServiceInstance serviceInstance : list) {
            log.info(serviceInstance.getInstanceId() + "\t" + serviceInstance.getHost() + "\t" + serviceInstance.getPort() + "\t" + serviceInstance.getUri());
        }
        log.info("=================================================");
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLB()
    {
        return serverPort;
    }
}
