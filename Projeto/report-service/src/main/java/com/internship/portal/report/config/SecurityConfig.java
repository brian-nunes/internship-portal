package com.internship.portal.report.config;

import com.internship.portal.report.filter.SessionDataFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(sessionDataFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public SessionDataFilter sessionDataFilter() {
        return new SessionDataFilter();
    }
}
