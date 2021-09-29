package com.example.demo.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringCloudConfig {

    @Bean
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/hi/**")
                        .uri("lb://HI-MICROSERVICE")
                        )

                .route(r -> r.path("/hello/**")
                        .uri("lb://HELLO-MICROSERVICE")
                        )
                .build();
    }

}