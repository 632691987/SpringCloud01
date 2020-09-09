package com.atguigu.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;

/**
 * 一旦使用，就在主类处使用 @RibbonClient(name = "CLOUD-PAYMENT-SERVICE", configuration = MySelfRule.class)
 */
@Configuration
public class MySelfRule
{
    @Bean
    public IRule myRule()
    {
        return new RoundRobinRule();
    }
}