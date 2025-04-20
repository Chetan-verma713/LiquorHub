package com.example.liquorhub.mapper;

import com.example.liquorhub.dto.ProductDto;
import com.example.liquorhub.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto toDto(Product entity);

    @Mapping(target= "createdAt", ignore = true)
    @Mapping(target= "updatedAt", ignore = true)
    @Mapping(target= "isActive", ignore = true)
    Product toEntity(ProductDto dto);

    List<ProductDto> toDtoList(List<Product> products);

}
