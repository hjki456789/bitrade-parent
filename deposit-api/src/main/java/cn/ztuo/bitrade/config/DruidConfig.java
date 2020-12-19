package cn.ztuo.bitrade.config;

import org.springframework.context.annotation.*;
import org.springframework.boot.web.servlet.*;
import com.alibaba.druid.support.http.*;
import javax.servlet.*;

@Configuration
public class DruidConfig
{
    @Bean
    public ServletRegistrationBean DruidStatViewServlet() {
        final ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean((Servlet)new StatViewServlet(), new String[] { "/my/druid/*" });
        servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
        servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        servletRegistrationBean.addInitParameter("loginUsername", "admin");
        servletRegistrationBean.addInitParameter("loginPassword", "654321");
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }
    
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        final FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean((Filter)new WebStatFilter(), new ServletRegistrationBean[0]);
        filterRegistrationBean.addUrlPatterns(new String[] { "/*" });
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/my/druid/*");
        return filterRegistrationBean;
    }
}
