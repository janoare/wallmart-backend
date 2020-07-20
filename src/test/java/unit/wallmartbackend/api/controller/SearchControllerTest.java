package unit.wallmartbackend.api.controller;

import cl.arellano.wallmart.wallmartbackend.api.controller.SearchController;
import cl.arellano.wallmart.wallmartbackend.api.domain.Product;
import cl.arellano.wallmart.wallmartbackend.api.dto.ProductDTO;
import cl.arellano.wallmart.wallmartbackend.api.service.SearchProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    @Mock
    SearchProductService searchProductService;

    @InjectMocks
    SearchController searchController;

    @BeforeEach
    void setUp() {

    }

    @DisplayName("Search product by id must return a response entity with a product")
    @Test
    void searchProduct_returnResponseEntity() {
        Integer productId = 1;

        when(searchProductService.searchById(any())).thenReturn(generateProduct());

        ResponseEntity<ProductDTO> response = searchController.searchProductById(productId);

        assertThat(response).isNotNull();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
    }

    private Product generateProduct() {
        return Product.builder().build();
    }
}