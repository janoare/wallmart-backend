package cl.arellano.wallmart.wallmartbackend.api.controller;

import cl.arellano.wallmart.wallmartbackend.api.domain.SearchCriteria;
import cl.arellano.wallmart.wallmartbackend.api.dto.SearchCriteriaDTO;
import cl.arellano.wallmart.wallmartbackend.api.mapper.ProductMapper;
import cl.arellano.wallmart.wallmartbackend.api.mapper.SearchCriteriaMapper;
import cl.arellano.wallmart.wallmartbackend.api.service.SearchProductService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/products/search")
@RestController
@AllArgsConstructor
public class SearchController {

    @Autowired
    private SearchProductService searchProductService;

    @GetMapping("/by-id/{id}")
    public ResponseEntity searchProductById(@PathVariable(name = "id") Integer productId) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.
                fromDomainToDto(searchProductService.searchById(productId)));
    }

    @GetMapping
    public ResponseEntity searchByBrandAndDescription(@RequestBody SearchCriteriaDTO searchCriteriaDTO) {
        return ResponseEntity.ok(ProductMapper.INSTANCE.fromDomainListToDtoList(
                searchProductService.searchByBrandAndDescription(
                        SearchCriteriaMapper.INSTANCE.fromDtoToDomain(searchCriteriaDTO))));
    }
}
