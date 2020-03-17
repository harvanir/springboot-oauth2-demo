package org.harvanir.oauth2.server.oauth2.support;

import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;

/**
 * @author Harvan Irsyadi
 */
public class Shared {

    static final AuthenticationKeyGenerator AUTHENTICATION_KEY_GENERATOR = new DefaultAuthenticationKeyGenerator();

    private Shared() {
    }
}
