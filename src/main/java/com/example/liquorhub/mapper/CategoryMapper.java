package com.example.liquorhub.mapper;

import com.example.liquorhub.dto.CategoryDto;
import com.example.liquorhub.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryDto toDto(Category entity);

    @Mapping(target= "createdAt", ignore = true)
    @Mapping(target= "updatedAt", ignore = true)
    @Mapping(target= "isActive", ignore = true)
    Category toEntity(CategoryDto dto);

    List<CategoryDto> toDtoList(List<Category> categories);
}
