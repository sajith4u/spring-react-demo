package com.product.server.api.resource;

import com.product.server.api.dto.ProductDto;
import com.product.server.api.model.Product;
import com.product.server.api.repository.ProductRepository;
import com.product.server.api.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@WebFluxTest(controllers = ProductsResource.class)
@Import({ProductServiceImpl.class})
class ProductsResourceTest {

    @Autowired
    private WebTestClient webClient;


    @MockBean
    ProductRepository productRepository;

    @Test
    @DisplayName("Product List Integration Test")
    void testGetProductList() {

        List<Product> allProducts = new ArrayList<>();
        allProducts.add(Product.builder().id("001").cartonPrice(175).name("Penguin-ears").unitesPerCarton(20).build());
        allProducts.add(Product.builder().id("002").cartonPrice(825).name("Horseshoe").unitesPerCarton(5).build());

        Mockito.when(productRepository.findAll()).thenReturn(Flux.fromIterable(allProducts));

        webClient.get().uri("/product/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBodyList(ProductDto.class)
                .hasSize(allProducts.size());

    }
}