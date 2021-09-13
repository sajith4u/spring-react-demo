package com.product.server.api.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private String id;
    private String name;
    private double unitPrice;
    private double cartonPrice;
    private int numOfUnitsInCarton;
}
