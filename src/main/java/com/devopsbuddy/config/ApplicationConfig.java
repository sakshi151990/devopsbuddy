package com.devopsbuddy.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.devopsbuddy.persistence")
@EntityScan(basePackages = "com.devopsbuddy.persistence")
@EnableTransactionManagement
@ComponentScan(basePackages = "com.devopsbuddy.config, com.devopsbuddy.web, com.devopsbuddy.persistence,com.devopsbuddy.service")
public class ApplicationConfig {

}
