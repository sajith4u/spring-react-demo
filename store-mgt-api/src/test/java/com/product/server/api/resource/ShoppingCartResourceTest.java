package com.product.server.api.resource;

import com.product.server.api.config.TestConfiguration;
import com.product.server.api.dto.CartItemsDto;
import com.product.server.api.dto.ItemRequest;
import com.product.server.api.model.Product;
import com.product.server.api.repository.ProductRepository;
import com.product.server.api.service.impl.ShoppingCartServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = ShoppingCartResource.class)
@Import({ShoppingCartServiceImpl.class})
@ContextConfiguration(classes = TestConfiguration.class)
@ComponentScan(basePackages = "com.product")
class ShoppingCartResourceTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    ProductRepository productRepository;

    @Test
    @DisplayName("Cart Summary Integration Test")
    void testCartSummary() {

        String productId1 = "001";
        String productId2 = "002";

        Product product1 = Product.builder().id("001").cartonPrice(175).name("Penguin-ears").unitesPerCarton(20).build();

        Product product2 = Product.builder().id("002").cartonPrice(825).name("Horseshoe").unitesPerCarton(5).build();

        Mockito.when(productRepository.findById(productId1)).thenReturn(Mono.just(product1));
        Mockito.when(productRepository.findById(productId2)).thenReturn(Mono.just(product2));


        CartItemsDto cartItemsDto = new CartItemsDto();
        List<ItemRequest> items = new ArrayList<>();
        ItemRequest item1 = new ItemRequest(productId1, 20);
        ItemRequest item2 = new ItemRequest(productId2, 5);
        items.add(item1);
        items.add(item2);
        cartItemsDto.setItems(items);


        webClient.post()
                .uri("/cart/summary")
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(cartItemsDto), CartItemsDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("totalPrice").isEqualTo(1000);

    }

}