package cn.ztuo.bitrade;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.*;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ContractApiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ContractApiApplication.class, args);
    }
}
