package com.nokia.teachersupport.configuration;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.nokia.teachersupport"}) //wybieramy folder w ktorym beda szukane encje
@EnableJpaRepositories(basePackages = {"com.nokia.teachersupport.repositories"})
@EnableTransactionManagement
public class RepoConfig {
}
