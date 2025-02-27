package com.example.es280225.mapper;

import com.example.es280225.object.dto.ProductDTO;
import com.example.es280225.object.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDTO toProductDTO(Product product);
    Product toProduct(ProductDTO productDTO);
}