package cn.ztuo.bitrade.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.core.env.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.config.annotation.web.builders.*;
import org.apache.commons.lang.*;
import org.springframework.security.config.annotation.web.configurers.*;

@Configuration
@EnableWebSecurity
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    Environment env;
    
    protected void configure(final HttpSecurity http) throws Exception {
        String contextPath = this.env.getProperty("management.context-path");
        if (StringUtils.isEmpty(contextPath)) {
            contextPath = "";
        }
        http.csrf().disable();
        ((HttpSecurity)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)((ExpressionUrlAuthorizationConfigurer.AuthorizedUrl)http.authorizeRequests().antMatchers(new String[] { "/**" + contextPath + "/**" })).authenticated().anyRequest()).permitAll().and()).httpBasic();
    }
}
