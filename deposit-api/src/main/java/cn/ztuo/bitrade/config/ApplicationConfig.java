package cn.ztuo.bitrade.config;

import org.springframework.web.filter.*;
import org.springframework.web.cors.*;
import org.springframework.boot.web.servlet.*;
import javax.servlet.*;
import org.springframework.context.annotation.*;
import org.springframework.context.support.*;
import org.springframework.validation.*;
import org.springframework.validation.beanvalidation.*;
import org.springframework.context.*;
import org.springframework.format.*;
import cn.ztuo.bitrade.ext.*;
import org.springframework.core.convert.converter.*;
import org.springframework.web.servlet.config.annotation.*;
import cn.ztuo.bitrade.interceptor.*;
import org.springframework.web.servlet.*;

@Configuration
public class ApplicationConfig extends WebMvcConfigurerAdapter
{
    @Bean
    public FilterRegistrationBean corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(Boolean.valueOf(true));
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        final FilterRegistrationBean bean = new FilterRegistrationBean((Filter)new CorsFilter((CorsConfigurationSource)source), new ServletRegistrationBean[0]);
        bean.setOrder(0);
        return bean;
    }
    
    @Bean(name = { "messageSource" })
    public ResourceBundleMessageSource getMessageSource() {
        final ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setCacheSeconds(3600);
        resourceBundleMessageSource.setBasenames(new String[] { "i18n/messages", "i18n/ValidationMessages" });
        return resourceBundleMessageSource;
    }
    
    public Validator getValidator() {
        final LocalValidatorFactoryBean validator = new LocalValidatorFactoryBean();
        validator.setValidationMessageSource((MessageSource)this.getMessageSource());
        return (Validator)validator;
    }
    
    public void addResourceHandlers(final ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[] { "/asset/**" }).addResourceLocations(new String[] { "classpath:/asset/" });
        super.addResourceHandlers(registry);
    }
    
    public void addFormatters(final FormatterRegistry registry) {
        registry.addConverterFactory((ConverterFactory)new OrdinalToEnumConverterFactory());
        super.addFormatters(registry);
    }
    
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor)new MemberInterceptor()).addPathPatterns(new String[] { "/**" }).excludePathPatterns(new String[] { "/invest/getDepositTypes", "/advertise/excellent", "/advertise/page", "/advertise/page-by-unit", "/order/pre", "/advertise/newest" });
        super.addInterceptors(registry);
    }
}
