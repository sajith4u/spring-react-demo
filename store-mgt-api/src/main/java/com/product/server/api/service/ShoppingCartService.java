package com.product.server.api.service;

import com.product.server.api.dto.CartItemsDto;
import com.product.server.api.dto.CartSummaryDto;
import reactor.core.publisher.Mono;

public interface ShoppingCartService {

    /**
     * Get Cart Summary
     *
     * @param productItems
     * @return
     */
    Mono<CartSummaryDto> cartSummary(CartItemsDto productItems);
}
