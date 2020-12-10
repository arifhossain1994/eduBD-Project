package com;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@Configuration
@ConfigurationProperties(prefix = "service")
@EnableSwagger2
@ComponentScan
@EnableAutoConfiguration
public class ApplicationConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    protected String dbDriverClassName;
    protected String dbDriverUrl;
    protected String dbUsername;
    protected String dbPassword;
    protected String dbMigrationLocation;
    protected int dbPoolMaxWait;
    protected boolean dbPoolRemoveAbandoned;
    protected int dbPoolRemoveAbandonedTimeout;
    protected boolean dbPoolLogAbandoned;
    protected long dbPoolMaxAge;
    protected String sqlStatementsResourceLocation;

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
    


}