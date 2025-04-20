package com.example.liquorhub.mapper;

import com.example.liquorhub.dto.BrandDto;
import com.example.liquorhub.entity.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    BrandDto toDto(Brand entity);

    @Mapping(target= "createdAt", ignore = true)
    @Mapping(target= "updatedAt", ignore = true)
    @Mapping(target= "isActive", ignore = true)
    Brand toEntity(BrandDto dto);

    List<BrandDto> toDtoList(List<Brand> all);
}
