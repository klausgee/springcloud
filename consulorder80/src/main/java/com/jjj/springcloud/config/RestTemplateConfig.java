package com.jjj.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Bean
    @LoadBalanced //使用自载均衡， 轮着使用服务
     public RestTemplate getRestTemplate(){
         return new RestTemplate();
     }
}
