package org.harvanir.oauth2.server.oauth2.support;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;

/**
 * @author Harvan Irsyadi
 */
public class ClientIdAwareUserAuthenticationProvider extends DaoAuthenticationProvider {

    public ClientIdAwareUserAuthenticationProvider() {
        UserDetailsChecker preAuthenticationChecks = getPreAuthenticationChecks();
        setPreAuthenticationChecks(new ClientIdAwareChecker(preAuthenticationChecks));
    }

    static class ClientIdAwareChecker implements UserDetailsChecker {

        private final UserDetailsChecker delegate;

        public ClientIdAwareChecker(UserDetailsChecker delegate) {
            this.delegate = delegate;
        }

        @Override
        public void check(UserDetails userDetails) {
            String clientId = ClientIdThreadContext.get();
            ClientIdThreadContext.remove();

            if (clientId != null && userDetails instanceof DefaultUserDetailService.ClientAwareUserDetails) {
                DefaultUserDetailService.ClientAwareUserDetails details = (DefaultUserDetailService.ClientAwareUserDetails) userDetails;

                if (!clientId.equals(details.getClientId())) {
                    throw new UserNotAuthorizedForClientException(String.format("Client id '%s' not matched with user's client id '%s'", clientId, details.getClientId()));
                }
            }

            delegate.check(userDetails);
        }
    }

    static class UserNotAuthorizedForClientException extends RuntimeException {

        public UserNotAuthorizedForClientException(String message) {
            super(message);
        }
    }
}
