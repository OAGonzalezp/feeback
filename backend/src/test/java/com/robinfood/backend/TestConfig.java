package com.robinfood.backend;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = { "com.robinfood.models.feedback" })
@EnableTransactionManagement
@PropertySource(value = { "classpath:application.properties" })
public class TestConfig {
}
