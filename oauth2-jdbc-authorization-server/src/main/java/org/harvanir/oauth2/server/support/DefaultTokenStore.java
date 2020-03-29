package org.harvanir.oauth2.server.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.harvanir.oauth2.server.configuration.ApplicationProperties;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Harvan Irsyadi
 */
@Slf4j
public class DefaultTokenStore extends JdbcTokenStore {

    private final Cache<String, OAuth2AccessToken> cacheAccessToken;

    private final Cache<String, OAuth2Authentication> cacheAuthentication;

    public DefaultTokenStore(DataSource dataSource, ApplicationProperties.Token token) {
        super(dataSource);
        setAuthenticationKeyGenerator(Shared.AUTHENTICATION_KEY_GENERATOR);

        cacheAccessToken = Caffeine.newBuilder()
                .expireAfterWrite(Duration.of(token.getExpiredAfterSecond(), ChronoUnit.SECONDS))
                .maximumSize(token.getMaximumSize())
                .build();
        cacheAuthentication = Caffeine.newBuilder()
                .expireAfterWrite(Duration.of(token.getExpiredAfterSecond(), ChronoUnit.SECONDS))
                .maximumSize(token.getMaximumSize())
                .build();
    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = cacheAccessToken.get(tokenValue, super::readAccessToken);

        if (accessToken != null && accessToken.isExpired()) {
            cacheAccessToken.invalidate(tokenValue);
            cacheAuthentication.invalidate(tokenValue);
            return null;
        }

        return accessToken;
    }

    @Override
    public void removeAccessToken(String tokenValue) {
        cacheAccessToken.invalidate(tokenValue);
        cacheAuthentication.invalidate(tokenValue);
        super.removeAccessToken(tokenValue);
    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        cacheAccessToken.put(token.getValue(), token);
        cacheAuthentication.put(token.getValue(), authentication);
        super.storeAccessToken(token, authentication);
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        return cacheAuthentication.get(token, super::readAuthentication);
    }
}
