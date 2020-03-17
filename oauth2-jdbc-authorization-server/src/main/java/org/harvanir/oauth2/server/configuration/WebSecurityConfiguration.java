package org.harvanir.oauth2.server.configuration;

import org.harvanir.oauth2.server.oauth2.support.DefaultAuthenticationProvider;
import org.harvanir.oauth2.server.oauth2.support.DefaultUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    private final ApplicationProperties applicationProperties;

    public WebSecurityConfiguration(DataSource dataSource, ApplicationProperties applicationProperties) {
        this.dataSource = dataSource;
        this.applicationProperties = applicationProperties;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        return new DefaultUserDetailService(dataSource);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DefaultAuthenticationProvider provider = new DefaultAuthenticationProvider(applicationProperties);
        provider.setUserDetailsService(userDetailsService());

        auth.authenticationProvider(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
