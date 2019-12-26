package com.alexkaralanian.FullstackSpringboot.datasource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // Configures the DataSource. Speific Declaration for injection into other Components.
public class Datasource {
    @Bean
    @ConfigurationProperties("app.datasource")
    // dependency injection of db login credentials
    // see application.yml file
    public HikariDataSource hikariDataSource(){
        return DataSourceBuilder
                .create()
                .type(HikariDataSource.class)
                .build();
    }

}
