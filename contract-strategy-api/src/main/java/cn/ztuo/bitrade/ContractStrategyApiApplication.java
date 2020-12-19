package cn.ztuo.bitrade;

import org.springframework.scheduling.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.client.discovery.*;
import org.springframework.cloud.netflix.eureka.*;
import org.springframework.boot.*;

@EnableScheduling
@SpringBootApplication
@EnableDiscoveryClient
@EnableEurekaClient
public class ContractStrategyApiApplication {
    public static void main(final String[] args) {
        SpringApplication.run(ContractStrategyApiApplication.class, args);
    }
}
