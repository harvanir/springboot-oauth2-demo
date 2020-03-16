package org.harvanir.oauth2.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Harvan Irsyadi
 */
@SpringBootApplication
public class OAuth2JdbcAuthorizationServer {

    public static void main(String[] args) {
        SpringApplication.run(OAuth2JdbcAuthorizationServer.class, args);
    }
}
