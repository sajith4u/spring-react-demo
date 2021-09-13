package com.product.server.api.resource;

import com.product.server.api.dto.ProductDto;
import com.product.server.api.service.ProductService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin
@RequestMapping(path = "/product")
public class ProductsResource {

    private final ProductService productService;

    public ProductsResource(ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "*")
    @GetMapping("/")
    public Flux<ProductDto> getAllProducts() {
        return productService.findAllProducts();
    }
}
