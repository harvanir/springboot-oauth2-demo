package org.harvanir.oauth2.server.support;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
@Slf4j
public class DefaultTokenStore extends JdbcTokenStore {

    public DefaultTokenStore(DataSource dataSource) {
        super(dataSource);
        setAuthenticationKeyGenerator(Shared.AUTHENTICATION_KEY_GENERATOR);
    }
}
