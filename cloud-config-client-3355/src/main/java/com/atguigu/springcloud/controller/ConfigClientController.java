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

    /**
     * 会在连接的一刻，去config-server的
     * http://config-3344.com:3344/master/app1-dev1.yml
     * 读取 propertyA.value
     */
    @Value("${propertyA.value}")
    private String value;

    /**
     * 会在连接的一刻，去config-server的
     * http://config-3344.com:3344/master/app1-dev1.yml
     * 读取 propertyA.version
     */
    @Value("${propertyA.version}")
    private String version;

    /**
     *
     * 其实加了很多例如 @RefreshScope 等手段都是不足以令它即使更新的，必须要做下面的手法：
     *
     * curl -X POST "localhost:3355/actuator/refresh"
     *
     */
    @GetMapping("/configInfo")
    public String getConfigInfo()
    {
        return "serverPort: " + serverPort + "; value: " + value + "; version: " + version;
    }
}