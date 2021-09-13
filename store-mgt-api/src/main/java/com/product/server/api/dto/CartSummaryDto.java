package com.product.server.api.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartSummaryDto {
    private List<ShoppingCartItemSummaryDto> cartSummary;
    private double totalPrice;
}
