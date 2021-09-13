package com.product.server.api.service.impl;

import com.product.server.api.config.PriceDiscountConfig;
import com.product.server.api.dto.CartItemsDto;
import com.product.server.api.dto.CartSummaryDto;
import com.product.server.api.dto.ItemRequest;
import com.product.server.api.dto.ShoppingCartItemSummaryDto;
import com.product.server.api.exception.ApiError;
import com.product.server.api.exception.EmptyCartItemsException;
import com.product.server.api.exception.ProductNotFoundException;
import com.product.server.api.model.Product;
import com.product.server.api.repository.ProductRepository;
import com.product.server.api.service.ShoppingCartService;
import com.product.server.api.util.PriceCalculator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ProductRepository productRepository;
    private final PriceDiscountConfig priceDiscountConfig;

    public ShoppingCartServiceImpl(ProductRepository productRepository,
                                   PriceDiscountConfig priceDiscountConfig) {
        this.productRepository = productRepository;
        this.priceDiscountConfig = priceDiscountConfig;
    }

    @Override
    public Mono<CartSummaryDto> cartSummary(CartItemsDto productItems) {
        if (productItems.getItems() == null || productItems.getItems().isEmpty()) {
            return Mono.error(new EmptyCartItemsException(ApiError.EMPTY_CART_ITEMS));
        }
        return mapToCartons(productItems.getItems());
    }

    private Mono<CartSummaryDto> mapToCartons(List<ItemRequest> items) {

        List<Mono<ShoppingCartItemSummaryDto>> collect = items.stream().map(item -> {
            Mono<Product> byId = productRepository.findById(item.getItemId());

            return byId.switchIfEmpty(Mono.error(new ProductNotFoundException(ApiError.PRODUCT_NOT_FOUND)))
                    .map(x -> {

                        int cartonSize = item.getUnitCounts() / x.getUnitesPerCarton();
                        int units = item.getUnitCounts() % x.getUnitesPerCarton();

                        double unitsPrice = PriceCalculator.calculateUnitPrices(x.getCartonPrice(), x.getUnitesPerCarton(),
                                units, priceDiscountConfig.getUnitIncrementPercentage());

                        double cartonPrice = PriceCalculator.calculateCartonPrices(x.getCartonPrice(), cartonSize,
                                priceDiscountConfig.getCartonDiscount(), priceDiscountConfig.getCartonDiscountItemCount());

                        double totalPrice = unitsPrice + cartonPrice;

                        return ShoppingCartItemSummaryDto.builder().itemName(x.getName())
                                .numOfCartons(cartonSize)
                                .numOfUnits(units)
                                .price(totalPrice)
                                .build();
                    });
        }).collect(Collectors.toList());

        return Flux.concat(collect).collectList().map(c -> {
            double finalPrice = c.stream().mapToDouble(ShoppingCartItemSummaryDto::getPrice).sum();
            return CartSummaryDto.builder().cartSummary(c).totalPrice(finalPrice).build();
        });
    }


}
