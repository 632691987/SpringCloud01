package com.atguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope//刷新功能，每次获取都从 server 端获取
public class ConfigClientController
{
    @Value("${server.port}")
    private String serverPort;

    @Value("${config.info}")
    private String configInfo;

    @GetMapping("/configInfo")//直接读取 cloud-config-center-3344 module. 等于 http://config-3344.com:3344/master/config-dev.yml
    public String getConfigInfo()
    {
        return "serverPort: " + serverPort + "; configInfo: " + configInfo;
    }
}