package org.harvanir.oauth2.server.oauth2.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.harvanir.oauth2.server.configuration.ApplicationProperties;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserCache;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * @author Harvan Irsyadi
 */
public class DefaultAuthenticationProvider extends DaoAuthenticationProvider {

    public DefaultAuthenticationProvider(ApplicationProperties properties) {
        setUserCache(new DefaultUserCache(properties.getCache().getUser()));
    }

    static class DefaultUserCache implements UserCache {

        private final Cache<String, UserDetails> cache;

        public DefaultUserCache(ApplicationProperties.User user) {
            cache = Caffeine.newBuilder()
                    .expireAfterWrite(Duration.of(user.getExpiredAfterSecond(), ChronoUnit.SECONDS))
                    .maximumSize(user.getMaximumSize())
                    .build();
        }

        @Override
        public UserDetails getUserFromCache(String username) {
            return Optional.ofNullable(cache.getIfPresent(username))
                    .map(this::build)
                    .orElse(null);
        }

        @Override
        public void putUserInCache(UserDetails user) {
            cache.put(user.getUsername(), build(user));
        }

        @Override
        public void removeUserFromCache(String username) {
            cache.invalidate(username);
        }

        private UserDetails build(UserDetails userDetails) {
            return new User(userDetails.getUsername(), userDetails.getPassword(), userDetails.isEnabled(),
                    userDetails.isAccountNonExpired(), userDetails.isCredentialsNonExpired(),
                    userDetails.isAccountNonLocked(), userDetails.getAuthorities());
        }
    }
}
