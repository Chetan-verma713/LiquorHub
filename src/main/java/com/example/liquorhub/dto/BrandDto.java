package com.example.liquorhub.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BrandDto extends BaseDto {
    private String countryName;
}
