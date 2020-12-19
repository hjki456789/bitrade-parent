package cn.ztuo.bitrade.config;

import org.springframework.session.data.redis.config.annotation.web.http.*;
import org.springframework.session.web.http.*;
import cn.ztuo.bitrade.ext.*;
import org.springframework.context.annotation.*;

@EnableRedisHttpSession
public class HttpSessionConfig {
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        HeaderHttpSessionIdResolver headerSession = new HeaderHttpSessionIdResolver("x-auth-token");
        final CookieHttpSessionIdResolver cookieSession = new CookieHttpSessionIdResolver();
        return new SmartHttpSessionStrategy(cookieSession, headerSession);
    }
}
