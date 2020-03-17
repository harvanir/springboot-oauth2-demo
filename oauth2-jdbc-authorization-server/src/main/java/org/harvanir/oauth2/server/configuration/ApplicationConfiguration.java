package org.harvanir.oauth2.server.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harvan Irsyadi
 */
@EnableConfigurationProperties(ApplicationProperties.class)
@Configuration
public class ApplicationConfiguration {
}
