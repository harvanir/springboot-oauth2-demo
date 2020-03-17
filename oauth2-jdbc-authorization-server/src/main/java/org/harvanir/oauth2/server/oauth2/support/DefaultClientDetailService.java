package org.harvanir.oauth2.server.oauth2.support;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.harvanir.oauth2.server.configuration.ApplicationProperties;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

/**
 * @author Harvan Irsyadi
 */
public class DefaultClientDetailService extends JdbcClientDetailsService {
    private final Cache<String, ClientDetails> cache;

    public DefaultClientDetailService(DataSource dataSource, ApplicationProperties.Client client) {
        super(dataSource);
        cache = Caffeine.newBuilder()
                .expireAfterWrite(Duration.of(client.getExpiredAfterSecond(), ChronoUnit.SECONDS))
                .maximumSize(client.getMaximumSize())
                .build();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        return cache.get(clientId, s -> super.loadClientByClientId(clientId));
    }
}
