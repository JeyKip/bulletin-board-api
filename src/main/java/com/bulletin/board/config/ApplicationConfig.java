package com.bulletin.board.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({MongoDbConfig.class})
public class ApplicationConfig {
}
