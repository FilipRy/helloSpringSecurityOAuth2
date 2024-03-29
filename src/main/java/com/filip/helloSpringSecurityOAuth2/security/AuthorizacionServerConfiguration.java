package com.filip.helloSpringSecurityOAuth2.security;

import com.filip.helloSpringSecurityOAuth2.service.MUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.TokenStoreUserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer // tells Spring to activate the authorization server
public class AuthorizacionServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private static final int THIRTY_DAYS = 60 * 60 * 24 * 30;
    public static final String REALM="FILIP_REALM";


    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;

    // configures where the identifiers that our authentication server is supplying will be stored
    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenStoreUserApprovalHandler tokenStoreUserApprovalHandler;

    @Autowired
    private MUserDetailsService userDetailsService;

    @Override
    public void configure (ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource).withClient ("client")
                .secret (passwordEncoder. encode ("password"))
                .authorizedGrantTypes ("password", "authorization_code", "refresh_token", "implicit")
                .authorities ("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT", "USER")
                .scopes ("read", "write")
                .accessTokenValiditySeconds(300)
                .refreshTokenValiditySeconds(THIRTY_DAYS);

    }

    @Override
    public void configure (AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager (authenticationManager)
                .userApprovalHandler(tokenStoreUserApprovalHandler)
                .tokenStore (tokenStore)
                .userDetailsService(userDetailsService)
        ;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM);
    }


}

