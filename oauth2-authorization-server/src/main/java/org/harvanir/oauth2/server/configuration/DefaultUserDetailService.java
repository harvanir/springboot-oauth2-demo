package org.harvanir.oauth2.server.configuration;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

/**
 * @author Harvan Irsyadi
 */
public class DefaultUserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) {
        String userName = "user@email.com";
        // See org.springframework.security.crypto.password.NoOpPasswordEncoder
        String userPass = "{noop}password";

        if (!userName.equals(username)) {
            throw new UsernameNotFoundException("Invalid username or password");
        }

        return new User(userName, userPass, Collections.emptyList());
    }
}
