package cl.arellano.wallmart.wallmartbackend.api.service;

import cl.arellano.wallmart.wallmartbackend.api.domain.Product;
import cl.arellano.wallmart.wallmartbackend.api.domain.SearchCriteria;

import java.util.List;

public interface SearchProductService {
    Product searchById(Integer productId);

    List<Product> searchByBrandAndDescription(SearchCriteria searchCriteria);
}
