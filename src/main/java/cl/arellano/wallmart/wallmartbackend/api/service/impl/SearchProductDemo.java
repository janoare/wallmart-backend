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

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static cl.arellano.wallmart.wallmartbackend.api.exception.ErrorCodes.NO_RESULTS;

@Service
@AllArgsConstructor
public class SearchProductDemo implements SearchProductService {

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

        return ProductMapper.INSTANCE.fromEntityListToDomainList(productsByBrandAndDEscription);
    }
}
