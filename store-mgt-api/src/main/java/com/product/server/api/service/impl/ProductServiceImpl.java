package com.product.server.api.service.impl;

import com.product.server.api.dto.ProductDto;
import com.product.server.api.model.Product;
import com.product.server.api.repository.ProductRepository;
import com.product.server.api.service.ProductService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Flux<ProductDto> findAllProducts() {
        Flux<Product> allItems = productRepository.findAll();

        return allItems.map(item -> ProductDto.builder().id(item.getId())
                .name(item.getName())
                .numOfUnitsInCarton(item.getUnitesPerCarton())
                .unitPrice((item.getCartonPrice() / item.getUnitesPerCarton()))
                .cartonPrice(item.getCartonPrice())
                .build());
    }
}
