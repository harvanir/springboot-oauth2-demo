package org.harvanir.oauth2.server.configuration;

import org.harvanir.oauth2.server.oauth2.support.DefaultClientDetailService;
import org.harvanir.oauth2.server.oauth2.support.DefaultTokenStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
@EnableAuthorizationServer
@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    private final AuthenticationManager authenticationManager;

    private final DataSource dataSource;

    private final PasswordEncoder passwordEncoder;

    private final ApplicationProperties applicationProperties;

    private final UserDetailsService userDetailsService;

    public AuthorizationServerConfiguration(AuthenticationManager authenticationManager, DataSource dataSource,
                                            PasswordEncoder passwordEncoder, ApplicationProperties applicationProperties,
                                            UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.dataSource = dataSource;
        this.passwordEncoder = passwordEncoder;
        this.applicationProperties = applicationProperties;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) {
        security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clientDetailsServiceConfigurer) throws Exception {
        clientDetailsServiceConfigurer.withClientDetails(clientDetailsService());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer authorizationServerEndpointsConfigurer) {
        authorizationServerEndpointsConfigurer
                .tokenStore(tokenStore())
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService)
                .authorizationCodeServices(new JdbcAuthorizationCodeServices(dataSource));
    }

    private ClientDetailsService clientDetailsService() {
        DefaultClientDetailService clientDetailService = new DefaultClientDetailService(dataSource, applicationProperties.getCache().getClient());
        clientDetailService.setPasswordEncoder(passwordEncoder);

        return clientDetailService;
    }

    @Bean
    public TokenStore tokenStore() {
        return new DefaultTokenStore(dataSource);
    }
}
