package com.product.server.api.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ShoppingCartItemSummaryDto {
    private String itemName;
    private int numOfUnits;
    private int numOfCartons;
    private double price;
}
