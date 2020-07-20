package unit.wallmartbackend.api.service.impl;

import cl.arellano.wallmart.wallmartbackend.api.domain.Product;
import cl.arellano.wallmart.wallmartbackend.api.repository.ProductRepository;
import cl.arellano.wallmart.wallmartbackend.api.repository.entity.Products;
import cl.arellano.wallmart.wallmartbackend.api.service.impl.SearchProductDemo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchProductDemoTest {

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    SearchProductDemo searchProductDemo;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Search by id must receive a product id and return a product")
    @Test
    void searchById() {
        Integer productId = 1;

        when(productRepository.findByProductId(anyInt())).thenReturn(generateProduct());

        Product product = searchProductDemo.searchById(productId);

        assertThat(product).isNotNull();

    }

    private Optional<Products> generateProduct() {
        return Optional.of(Products.builder().build());
    }
}