package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConfigCenterMain3344
{
    /**
     * 访问规则详细看png文件，其中一种比较直接的是
     * http://config-3344.com:3344/master/app1-dev1.yml
     */

    /**
     * application=config (actial this is pre-fix of file name)
     * profile=dev  (actually this means which profile application use, it indicate in file name)
     * label=master (actually this means branch name)
     *
     * which means, this will read from {application}-{profile}.yml from {label} branch
     */

    /**
     * 一旦在 github 提交配置文件之后，就去 console 发送以下命令：
     * curl -X POST "http://localhost:3344/actuator/bus-refresh"
     */
    public static void main(String[] args) {
        SpringApplication.run(ConfigCenterMain3344.class, args);
    }
}