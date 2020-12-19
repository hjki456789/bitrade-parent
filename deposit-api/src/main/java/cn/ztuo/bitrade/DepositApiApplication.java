package cn.ztuo.bitrade;

import org.springframework.scheduling.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.*;

@EnableScheduling
@SpringBootApplication
public class DepositApiApplication
{
    public static void main(final String[] args) {
        SpringApplication.run(DepositApiApplication.class, args);
    }
}
