package integration;

import cl.arellano.wallmart.wallmartbackend.WallmartBackendApplication;
import cl.arellano.wallmart.wallmartbackend.api.dto.SearchCriteriaDTO;
import cl.arellano.wallmart.wallmartbackend.api.repository.ProductRepository;
import cl.arellano.wallmart.wallmartbackend.api.repository.entity.Products;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.mockito.BDDMockito.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = WallmartBackendApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SearchControllerTest {

    private final static String BASE_PATH = "/v1/products/search";

    @MockBean
    ProductRepository productRepository;

    @LocalServerPort
    private int port;
    //TestRestTemplate restTemplate = new TestRestTemplate();
    RestTemplate restTemplate = new RestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    void searchProductById() throws Exception{

        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        StringBuilder searchByIdPath = new StringBuilder(BASE_PATH);
        searchByIdPath.append("/by-id/1");

        Products product = Products.builder()
                .productId(1)
                .brand("asd")
                .description("asd")
                .price(1000)
                .image("asd")
                .build();

        String expected = "{\"productId\":1,\"brand\":\"asd\",\"description\":\"asd\",\"image\":\"asd\"," +
                "\"price\":1000}";
        given(productRepository.findByProductId(1)).willReturn(Optional.of(product));

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(searchByIdPath.toString()), HttpMethod.GET, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    @Test
    void searchByBrandAndDescription() throws Exception {

        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        String requestJson = "{\"criteria\":\"we\"}";

        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        StringBuilder searchByIdPath = new StringBuilder(BASE_PATH);

        List<Products> productsListBrand = new ArrayList<>();
        productsListBrand.add(Products.builder()
                .productId(1)
                .brand("qwe")
                .description("asd")
                .price(1000)
                .image("asd")
                .build());

        List<Products> productsListDescription = new ArrayList<>();
        productsListDescription.add(Products.builder()
                .productId(2)
                .brand("asd")
                .description("qwe")
                .price(2000)
                .image("asd")
                .build());

        String expected = "[{\"productId\":1,\"brand\":\"qwe\",\"description\":\"asd\",\"image\":\"asd\"," +
                "\"price\":1000},{\"productId\":2,\"brand\":\"asd\",\"description\":\"qwe\",\"image\":\"asd\"," +
                "\"price\":2000}]";

        given(productRepository.findByBrand("we")).willReturn(productsListBrand);
        given(productRepository.findByDescription("we")).willReturn(productsListDescription);

        ResponseEntity<String> response = restTemplate.exchange(
                createURLWithPort(searchByIdPath.toString()), HttpMethod.POST, entity, String.class);

        JSONAssert.assertEquals(expected, response.getBody(), true);

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}