package org.camunda.bpm.config;

import org.camunda.bpm.auth.OktaAuthenticationProvider;
import org.camunda.bpm.webapp.impl.security.auth.ContainerBasedAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collections;


@EnableWebSecurity
public class WebAppSecurityConfig {

  private final Logger logger = LoggerFactory.getLogger(WebAppSecurityConfig.class.getName());

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

    http
        .csrf().ignoringAntMatchers("/engine-rest/**", "/camunda/api/**")
        .and()
        .authorizeRequests()
        .antMatchers("/logout/callback/**","/swaggerui/**", "/engine-rest/**", "/error/**")
        .permitAll()
        .and()
        .antMatcher("/**")
        .authorizeRequests()
        .antMatchers("/camunda/**")
        .authenticated()
        .anyRequest()
        .permitAll()
        .and()
        .oauth2Login();

    return http.build();
  }


  @Bean
  @Order(SecurityProperties.BASIC_AUTH_ORDER - 15)
  public FilterRegistrationBean containerBasedAuthenticationFilter() {

    logger.info("Enable Okta Authentication Provider");
    FilterRegistrationBean filterRegistration
        = new FilterRegistrationBean();
    filterRegistration.setFilter(new ContainerBasedAuthenticationFilter());
    filterRegistration.setInitParameters(Collections.singletonMap("authentication-provider", OktaAuthenticationProvider.class.getName()));
    filterRegistration.setOrder(101); // make sure the filter is registered after the Spring Security Filter Chain
    filterRegistration.addUrlPatterns("/camunda/app/*");
    return filterRegistration;

  }


}
