package com.alex.txjob.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = {"com.alex.txjob.feign"})
//@EnableFeignClients
@Configuration
//@EnableHystrix
//@EnableCircuitBreaker
//@EnableHystrixDashboard
public class FeignConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
