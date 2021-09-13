package com.product.server.api.service;

import com.product.server.api.dto.ProductDto;
import reactor.core.publisher.Flux;

public interface ProductService {

    /**
     * Find All the Products
     *
     * @return
     */
    Flux<ProductDto> findAllProducts();
}
