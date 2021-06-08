/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms;

import com.milford.churchcms.filters.LoginFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author clarke
 */

@Configuration
public class FilterConfig {
     
    Logger logger = LoggerFactory.getLogger(FilterConfig.class);
    
    @Bean
    public FilterRegistrationBean<LoginFilter> loggingFilter() {
        logger.debug("~~~~ FilterRegistrationBean loggingFilter ");
        FilterRegistrationBean<LoginFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new LoginFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
