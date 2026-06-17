package com.adhub.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

import com.adhub.filter.LoggerFilter;

import jakarta.servlet.ServletContext;



public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer
{
    // No code needed, just enables Spring Security
    @Override
    protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
        // Register your custom filter here so it triggers first
        insertFilters(servletContext, new LoggerFilter());
    }
}