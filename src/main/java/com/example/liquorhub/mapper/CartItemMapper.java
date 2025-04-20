package com.example.liquorhub.mapper;

import com.example.liquorhub.dto.*;
import com.example.liquorhub.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    CartItemDto toDto(CartItem entity);

    @Mapping(target= "createdAt", ignore = true)
    @Mapping(target= "updatedAt", ignore = true)
    @Mapping(target= "isActive", ignore = true)
    CartItem toEntity(CartItemDto dto);

    List<CartItemDto> toDtoList(List<CartItem> cartItems);

    List<CartItem> toEntityList(List<CartItemDto> cartItemDtos);

}
