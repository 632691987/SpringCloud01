package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextConfig
{
    @Bean(name = "restTemplate")
    @LoadBalanced// 一定要使用这个，否则前面的 String PAYMENT_URL = "http://CLOUD-PAYMENT-SERVICE"; 会报错，因为不知道 "CLOUD-PAYMENT-SERVICE" 是什么
    public RestTemplate getRestTemplate()
    {
        return new RestTemplate();
    }

    @Bean(name = "ribbonRestTemplate")
    public RestTemplate customerRestTemplate()
    {
        return new RestTemplate();
    }
}
