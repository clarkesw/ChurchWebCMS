package com.milford.churchcms.filters;

import com.milford.churchcms.AppConstants;
import com.milford.churchcms.util.JWTUtil;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author clarke
 */

public class LoginFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("   LoginFilter ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {
        
        filterChain.doFilter(servletRequest, servletResponse);
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        
        String JWT_Token = (String)httpServletRequest.getSession().getAttribute(AppConstants.Security.JWT);
        JWTUtil.checkJWT(JWT_Token);
    }

    @Override
    public void destroy() {}

}