package com.bulletin.board.config;

import com.bulletin.board.config.models.MongoDbConfigReader;
import com.bulletin.board.config.models.MongoDbConfigReaderImpl;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.annotation.Validated;

public class MongoDbConfig {
    @Bean
    @Validated
    @ConfigurationProperties(prefix = "spring.data.mongodb")
    public MongoDbConfigReader mongoDbConfig() {
        return new MongoDbConfigReaderImpl();
    }
}
