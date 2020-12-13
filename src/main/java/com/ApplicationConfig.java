package com;


import org.flywaydb.core.Flyway;
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

import javax.sql.DataSource;

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

    @Bean
    @Primary
    public Flyway flyway(DataSource dataSource) {
        LOGGER.info("Running database migration on {}", dbDriverUrl);
        Flyway flyway = new Flyway(Flyway.configure()
                .locations(dbMigrationLocation.split("\\s*,\\s*"))
                .outOfOrder(true)
                .dataSource(dataSource));
        // *** this deletes the contents of the table everytime the application is restarted ***
        flyway.clean();
        //
        flyway.repair();
        flyway.migrate();

        return flyway;
    }






    public String getDbDriverClassName() {
        return dbDriverClassName;
    }

    public void setDbDriverClassName(String dbDriverClassName) {
        this.dbDriverClassName = dbDriverClassName;
    }

    public String getDbDriverUrl() {
        return dbDriverUrl;
    }

    public void setDbDriverUrl(String dbDriverUrl) {
        this.dbDriverUrl = dbDriverUrl;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbMigrationLocation() {
        return dbMigrationLocation;
    }

    public void setDbMigrationLocation(String dbMigrationLocation) {
        this.dbMigrationLocation = dbMigrationLocation;
    }

    public int getDbPoolMaxWait() {
        return dbPoolMaxWait;
    }

    public void setDbPoolMaxWait(int dbPoolMaxWait) {
        this.dbPoolMaxWait = dbPoolMaxWait;
    }

    public boolean isDbPoolRemoveAbandoned() {
        return dbPoolRemoveAbandoned;
    }

    public void setDbPoolRemoveAbandoned(boolean dbPoolRemoveAbandoned) {
        this.dbPoolRemoveAbandoned = dbPoolRemoveAbandoned;
    }

    public int getDbPoolRemoveAbandonedTimeout() {
        return dbPoolRemoveAbandonedTimeout;
    }

    public void setDbPoolRemoveAbandonedTimeout(int dbPoolRemoveAbandonedTimeout) {
        this.dbPoolRemoveAbandonedTimeout = dbPoolRemoveAbandonedTimeout;
    }

    public boolean isDbPoolLogAbandoned() {
        return dbPoolLogAbandoned;
    }

    public void setDbPoolLogAbandoned(boolean dbPoolLogAbandoned) {
        this.dbPoolLogAbandoned = dbPoolLogAbandoned;
    }

    public long getDbPoolMaxAge() {
        return dbPoolMaxAge;
    }

    public void setDbPoolMaxAge(long dbPoolMaxAge) {
        this.dbPoolMaxAge = dbPoolMaxAge;
    }

    public String getSqlStatementsResourceLocation() {
        return sqlStatementsResourceLocation;
    }

    public void setSqlStatementsResourceLocation(String sqlStatementsResourceLocation) {
        this.sqlStatementsResourceLocation = sqlStatementsResourceLocation;
    }
}
