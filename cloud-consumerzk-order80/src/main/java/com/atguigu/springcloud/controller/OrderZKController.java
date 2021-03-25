package com.atguigu.springcloud.controller;

import java.util.List;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @auther zzyy
 * @create 2020-02-19 15:21
 */
@RestController
public class OrderZKController
{
    public static final String INVOKE_URL = "http://cloud-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private DiscoveryClient discoveryClient;

    @GetMapping(value = "/consumer/discovery/client")
    public String findDifferentService() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance serviceInstance : serviceInstances) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(";host:" + serviceInstance.getHost());
            stringBuffer.append(";uri:" + serviceInstance.getUri());
            stringBuffer.append(";scheme:" + serviceInstance.getScheme());
            stringBuffer.append(";serviceID:" + serviceInstance.getServiceId());
            stringBuffer.append(";port:" + serviceInstance.getPort());
            System.out.println(stringBuffer.toString());
        }
        return "" + serviceInstances.size();
    }

    @GetMapping(value = "/consumer/payment/zk")
    public String paymentInfo()
    {
        return restTemplate.getForObject(INVOKE_URL+"/payment/zk",String.class);
    }
}