package com.product.server.api.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "price-config")
public class PriceDiscountConfig {
    private double cartonDiscount;
    private int cartonDiscountItemCount;
    private double unitIncrementPercentage;
}
