package com.product.server.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Builder
@Document(value = "product")
public class Product {
    @Id
    private String id;

    private String name;
    private int unitesPerCarton;
    private double cartonPrice;
}
