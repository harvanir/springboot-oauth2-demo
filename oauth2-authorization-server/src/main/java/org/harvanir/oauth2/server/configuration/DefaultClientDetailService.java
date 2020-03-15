package org.harvanir.oauth2.server.configuration;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Harvan Irsyadi
 */
public class DefaultClientDetailService implements ClientDetailsService {

    private final ClientDetailsDao clientDetailsDao;

    public DefaultClientDetailService() {
        this.clientDetailsDao = new ClientDetailsDao();
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) {
        ClientDetails details = clientDetailsDao.get(clientId);

        if (details == null) {
            throw new ClientRegistrationException("Client id or secret not found");
        }

        return details;
    }

    private static class ClientDetailsDao {

        private final Map<String, ClientDetails> detailsMap = new HashMap<>();

        ClientDetailsDao() {
            BaseClientDetails clientDetails = new BaseClientDetails();
            clientDetails.setClientId("clientId");
            clientDetails.setClientSecret("{noop}secret");
            clientDetails.setAuthorizedGrantTypes(Arrays.asList("password", "authorization_code", "refresh_token"));
            clientDetails.setScope(Arrays.asList("read", "write", "trust"));
            clientDetails.setAccessTokenValiditySeconds(-1); // valid forever
            clientDetails.setRefreshTokenValiditySeconds(-1); //valid forever

            detailsMap.put(clientDetails.getClientId(), clientDetails);
        }

        ClientDetails get(String clientId) {
            return detailsMap.get(clientId);
        }
    }
}
