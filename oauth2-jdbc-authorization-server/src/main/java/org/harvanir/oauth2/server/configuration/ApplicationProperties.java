package org.harvanir.oauth2.server.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Harvan Irsyadi
 */
@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationProperties {

    private Cache cache = new Cache();

    @Getter
    @Setter
    public static class Cache {

        private Client client = new Client();

        private User user = new User();

        private Token token = new Token();
    }

    @Getter
    @Setter
    static class BaseCache {

        private int maximumSize = 10;

        private int expiredAfterSecond = 120;
    }

    @Getter
    @Setter
    public static class Client extends BaseCache {

    }

    @Getter
    @Setter
    public static class User extends BaseCache {

    }

    @Getter
    @Setter
    public static class Token extends BaseCache {

    }
}
