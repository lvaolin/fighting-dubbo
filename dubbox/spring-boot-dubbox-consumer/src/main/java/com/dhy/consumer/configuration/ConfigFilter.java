package com.dhy.consumer.configuration;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Configuration
public class ConfigFilter {
    @Bean
    public FilterRegistrationBean registFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new FilterResponseTime());
        registration.addUrlPatterns("/*");
        registration.setName("FilterResponseTime");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*");
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);

        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");

        config.addAllowedHeader(CorsConfiguration.ALL);
        config.addExposedHeader("Access-Control-Allow-Origin");
        config.addExposedHeader("Access-Control-Allow-Credentials");

        UrlBasedCorsConfigurationSource configSource = new UrlBasedCorsConfigurationSource();
        configSource.registerCorsConfiguration("/**", config);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(configSource));
        bean.setOrder(0);//利用FilterRegistrationBean，将拦截器注册靠前,避免被其它拦截器首先执行
        return bean;
    }


    static class FilterResponseTime implements Filter {

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            long start = System.currentTimeMillis();
            String uri = ((HttpServletRequest)servletRequest).getRequestURI();
            try {
                filterChain.doFilter(servletRequest,servletResponse);
            }finally {
                log.error(uri+"耗时"+(System.currentTimeMillis()-start)+"ms");
                ThreadContext.clearAll();
            }

        }

        @Override
        public void destroy() {
            log.info("--- FilterResponseTime destroy ---");
        }
    }
}
