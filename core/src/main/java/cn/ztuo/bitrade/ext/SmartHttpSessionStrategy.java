package cn.ztuo.bitrade.ext;

import org.springframework.session.web.http.*;
import org.apache.commons.lang.*;
import org.springframework.session.*;
import javax.servlet.http.*;
import java.util.List;

public class SmartHttpSessionStrategy implements HttpSessionIdResolver
{
    private CookieHttpSessionIdResolver browser;
    private HeaderHttpSessionIdResolver api;
    private String tokenName;

    public SmartHttpSessionStrategy(final CookieHttpSessionIdResolver browser, final HeaderHttpSessionIdResolver api) {
        this.tokenName = "x-auth-token";
        this.browser = browser;
        this.api = api;
    }
    private HttpSessionIdResolver getStrategy(HttpServletRequest request) {
        final String authType = request.getHeader("x-auth-token");
        if (authType == null) {
            return this.browser;
        }
        return this.api;
    }

    @Override
    public List<String> resolveSessionIds(HttpServletRequest httpServletRequest) {
        return this.getStrategy(httpServletRequest).resolveSessionIds(httpServletRequest);
    }

    @Override
    public void setSessionId(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, String s) {

    }

    @Override
    public void expireSession(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }
}
