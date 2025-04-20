package com.example.liquorhub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(callSuper = true)
public class CartItemDto extends BaseDto {
    private UserDto user;
    private ProductDto product;
    private Integer quantity;
}
