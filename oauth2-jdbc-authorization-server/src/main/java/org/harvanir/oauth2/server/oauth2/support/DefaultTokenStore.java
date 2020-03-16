package org.harvanir.oauth2.server.oauth2.support;

import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
public class DefaultTokenStore extends JdbcTokenStore {

    public DefaultTokenStore(DataSource dataSource) {
        super(dataSource);
    }
}
