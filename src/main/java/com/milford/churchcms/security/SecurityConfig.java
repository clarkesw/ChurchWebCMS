/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.milford.churchcms.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
			throws Exception {
    //        User.withDefaultPasswordEncoder().username("clarke").password("t").roles("USER").build();
		auth.inMemoryAuthentication().withUser("clarke").password("t")
				.roles("USER", "ADMIN");
	}
    
        @SuppressWarnings("depercation")
        @Bean
        public static NoOpPasswordEncoder passwordEncoder(){
            return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
        }
        
        @Override
        protected void configure(HttpSecurity http) throws Exception {
//                http
//                    .authorizeRequests().antMatchers("/","/WebResponse*/**").permitAll()
//                    .anyRequest().authenticated()
//                   .antMatchers("/*todo*/**", "/*event*/**", "/*cms*/**").access("hasRole('USER')").and()
//               .formLogin();

		http.authorizeRequests().antMatchers("/login","/index*","/h2/**").permitAll()
                                    .antMatchers("/*todo*/**").access("hasRole('USER')");  // "/*",
//                    .logout()
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))            
//                        .logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)        // set invalidation state when logout
//                        .deleteCookies("JSESSIONID")    
                http.csrf().disable();
                http.headers().frameOptions().disable();
        }
}
