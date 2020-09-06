package com.atguigu.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GateWayConfig
{
    // http://localhost:9527/guonei?uname=vincent
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();

        /**
         * spring.cloud.gateway.routes[0].id = path_route_atguigu
         * spring.cloud.gateway.routes[0].uri = http://news.baidu.com/guonei
         * spring.cloud.gateway.routes[0].predicates=/guonei
         */
        routes.route("path_route_atguigu",
                r -> r.path("/guonei")
                        .uri("http://news.baidu.com/guonei")).build();

        return routes.build();
    }
}