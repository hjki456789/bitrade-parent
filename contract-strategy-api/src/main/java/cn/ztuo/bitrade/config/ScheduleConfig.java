package cn.ztuo.bitrade.config;

import org.springframework.scheduling.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.scheduling.config.*;

import java.util.concurrent.*;

@Configuration
public class ScheduleConfig implements SchedulingConfigurer {
    public void configureTasks(final ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(50));
    }
}
