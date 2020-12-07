package cn.ztuo.bitrade.config;

import org.springframework.web.filter.*;
import org.springframework.web.cors.*;
import org.springframework.boot.web.servlet.*;

import javax.servlet.*;

import org.springframework.context.annotation.*;
import org.springframework.format.*;
import cn.ztuo.bitrade.ext.*;
import org.springframework.core.convert.converter.*;
import org.springframework.web.servlet.config.annotation.*;
import cn.ztuo.bitrade.interceptor.*;
import org.springframework.web.servlet.*;
import org.springframework.context.support.*;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter {
    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(Boolean.valueOf(true));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        final FilterRegistrationBean bean = new FilterRegistrationBean((Filter) new CorsFilter((CorsConfigurationSource) source), new ServletRegistrationBean[0]);
        bean.setOrder(0);
        return bean;
    }

    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverterFactory((ConverterFactory) new OrdinalToEnumConverterFactory());
        super.addFormatters(registry);
    }

    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) new MemberInterceptor()).addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/coin/**", "/config/**"});
        super.addInterceptors(registry);
    }

    @Bean(name = {"messageSource"})
    public ResourceBundleMessageSource getMessageSource() {
        final ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasenames(new String[]{"i18n/messages"});
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setCacheSeconds(3600);
        return resourceBundleMessageSource;
    }
}
