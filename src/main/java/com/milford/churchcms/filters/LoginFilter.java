package com.milford.churchcms.filters;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 *
 * @author clarke
 */

@Component
public class LoginFilter implements Filter {
        
    Logger logger = LoggerFactory.getLogger(LoginFilter.class);
    

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.debug("~~~   LoginFilter ");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) 
            throws IOException, ServletException {           
//get session from ServletRequest
// delete the info after reading.

//        HttpServletResponse res = (HttpServletResponse)servletResponse;
//        Collection<String> resheaderNames = res.getHeaderNames();
//        for(String key : resheaderNames)
//          logger.debug("  doFilter Response Header: {}", key);      
        
        HttpServletRequest req = (HttpServletRequest)servletRequest;
//        Enumeration<String> headerNames = req.getHeaderNames();      
//        for(String key : Collections.list(headerNames))
//            logger.debug("  doFilter Request Header: {}", key);
        
        HttpSession session = req.getSession();
        logger.debug("~~~~  session: " +session.isNew());
        logger.debug("      attr: " +session.getAttribute("Secret"));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {}
    
    private void clearSession(HttpSession session){
        //session.
    }

}