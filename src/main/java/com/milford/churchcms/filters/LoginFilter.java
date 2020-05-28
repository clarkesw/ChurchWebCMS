package com.milford.churchcms.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author clarke
 */

@WebFilter(urlPatterns = "/login")
public class LoginFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {
        String inputString = servletRequest.getParameter("loggedInUser");

        logger.debug("/login   doFilter : {} ", inputString);
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {
    }

}