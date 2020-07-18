package cl.arellano.wallmart.wallmartbackend.api.mapper;

import cl.arellano.wallmart.wallmartbackend.api.domain.Product;
import cl.arellano.wallmart.wallmartbackend.api.dto.ProductDTO;
import cl.arellano.wallmart.wallmartbackend.api.repository.entity.Products;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO fromDomainToDto(Product product);
    Product fromDtoToDomain(ProductDTO productDTO);
    Product fromEntityToDomain(Products products);

    List<ProductDTO> fromDomainListToDtoList(List<Product> productList);

    List<Product> fromDtoListToDomainList(List<ProductDTO> productDTOList);

    List<Product> fromEntityListToDomainList(List<Products> productsList);
}
