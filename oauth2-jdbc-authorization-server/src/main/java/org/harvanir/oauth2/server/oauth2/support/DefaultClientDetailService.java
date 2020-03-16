package org.harvanir.oauth2.server.oauth2.support;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;

import javax.sql.DataSource;

/**
 * @author Harvan Irsyadi
 */
public class DefaultClientDetailService extends JdbcClientDetailsService {

    public DefaultClientDetailService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        ClientDetails clientDetails = super.loadClientByClientId(clientId);
        if (clientDetails != null) {
            ClientIdThreadContext.set(clientDetails.getClientId());
        }

        return clientDetails;
    }
}
