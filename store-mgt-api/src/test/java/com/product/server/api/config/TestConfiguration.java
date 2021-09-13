package com.product.server.api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@EnableConfigurationProperties
public class TestConfiguration {
    @Bean
    @ConfigurationProperties(prefix = "price-config")
    public PriceDiscountConfig settings() {
        return new PriceDiscountConfig(10, 3, 30);
    }
}
