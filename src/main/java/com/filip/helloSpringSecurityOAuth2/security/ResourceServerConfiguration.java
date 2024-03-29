package com.filip.helloSpringSecurityOAuth2.security;


import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.web.bind.annotation.RestController;

@EnableResourceServer
@RestController
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //-- define URL patterns to enable OAuth2 security
        http.
                anonymous().disable()
                .requestMatchers().antMatchers("/api/**")
                .and().authorizeRequests()
                .antMatchers("/api/**").access("hasRole('ADMIN') or hasRole('USER')")
                .and().exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

}
