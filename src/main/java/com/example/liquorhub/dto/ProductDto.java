package com.example.liquorhub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDto extends BaseDto {
    private String imageUrl;
    private Double price;
    private Integer quantity;
    private CategoryDto category;
    private BrandDto brand;
    private Integer alcoholPercentage;
}
