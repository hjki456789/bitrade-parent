package cn.ztuo.bitrade.config;

import org.springframework.web.client.*;
import org.springframework.context.annotation.*;
import org.springframework.cloud.client.loadbalancer.*;

@Configuration
public class RestTemplateConfig
{
    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
