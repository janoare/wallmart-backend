package cl.arellano.wallmart.wallmartbackend.api.service.impl;

import cl.arellano.wallmart.wallmartbackend.api.domain.Product;
import cl.arellano.wallmart.wallmartbackend.api.domain.SearchCriteria;
import cl.arellano.wallmart.wallmartbackend.api.exception.SearchException;
import cl.arellano.wallmart.wallmartbackend.api.mapper.ProductMapper;
import cl.arellano.wallmart.wallmartbackend.api.repository.ProductRepository;
import cl.arellano.wallmart.wallmartbackend.api.repository.entity.Products;
import cl.arellano.wallmart.wallmartbackend.api.service.SearchProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cl.arellano.wallmart.wallmartbackend.api.exception.ErrorCodes.NO_RESULTS;

@Service
@AllArgsConstructor
public class SearchProductDemo implements SearchProductService {

    private static final float PALINDROME_DISCOUNT = 0.5f;
    private ProductRepository productRepository;

    @Override
    public Product searchById(Integer productId) {
        Optional<Products> productEntityOptional =
                productRepository.findByProductId(productId);

        if (productEntityOptional.isPresent()) {
            return ProductMapper.INSTANCE.fromEntityToDomain(productEntityOptional.get());
        } else {
            throw new SearchException(NO_RESULTS);
        }
    }

    @Override
    public List<Product> searchByBrandAndDescription(SearchCriteria searchCriteria) {
        List<Products> productsByBrand = productRepository.findByBrand(searchCriteria.getCriteria());
        List<Products> productsByDescription = productRepository.findByDescription(searchCriteria.getCriteria());

        List<Products> productsByBrandAndDEscription = Stream.concat(productsByBrand.stream(), productsByDescription.stream())
                .sorted(Comparator.comparingInt(Products::getPrice))
                .collect(Collectors.toList());

        List<Product> productList = ProductMapper.INSTANCE.fromEntityListToDomainList(productsByBrandAndDEscription);

        if(validatePalindrome(searchCriteria.getCriteria())) {
            productList.stream().forEach(product -> {
                product.setDiscountPrice(calculateDiscount(product.getPrice(), PALINDROME_DISCOUNT));
            });
        }

        return productList;
    }

    public boolean validatePalindrome(String searchString) {
        return searchString.equals(new StringBuilder(searchString).reverse().toString());
    }

    public Integer calculateDiscount( Integer price, Float discount) {
        return Math.round(price * discount);
    }
}
