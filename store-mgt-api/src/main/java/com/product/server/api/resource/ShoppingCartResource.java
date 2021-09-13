package com.product.server.api.resource;

import com.product.server.api.dto.CartItemsDto;
import com.product.server.api.dto.CartSummaryDto;
import com.product.server.api.service.ShoppingCartService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@CrossOrigin
@RequestMapping(path = "/cart")
public class ShoppingCartResource {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/summary")
    public Mono<CartSummaryDto> calculateCartSummary(@RequestBody CartItemsDto productItems) {
        return shoppingCartService.cartSummary(productItems);
    }
}
