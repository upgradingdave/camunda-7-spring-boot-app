package org.camunda.bpm.config;

import org.camunda.bpm.engine.rest.security.auth.ProcessEngineAuthenticationFilter;
import org.camunda.bpm.engine.rest.security.auth.impl.HttpBasicAuthenticationProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RestSecurityConfig
{

  @Bean
  public FilterRegistrationBean<ProcessEngineAuthenticationFilter> processEngineAuthenticationFilter()
  {
    FilterRegistrationBean<ProcessEngineAuthenticationFilter> registration = new FilterRegistrationBean<>();
    registration.setName("camunda-auth");
    registration.setFilter(this.getProcessEngineAuthenticationFilter());
    registration
        .addInitParameter("authentication-provider", HttpBasicAuthenticationProvider.class.getName());
    registration.addUrlPatterns("/engine-rest/*");
    return registration;
  }

  /*
   * ProcessEngineAuthenticationFilter will authenticate REST Calls against DB Users Basic Authentication
   * */
  @Bean
  public ProcessEngineAuthenticationFilter getProcessEngineAuthenticationFilter()
  {
    return new ProcessEngineAuthenticationFilter();
  }
}


