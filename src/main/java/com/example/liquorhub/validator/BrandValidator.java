package com.example.liquorhub.validator;

import com.example.liquorhub.dto.BrandDto;
import com.example.liquorhub.entity.Brand;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

public class BrandValidator {
    public static void validate(Long id, Brand brand) {
        if (brand == null) {
            throw new EntityNotFoundException("Brand with ID " + id + " not found");
        }
    }

    public static void validate(BrandDto brandDto) {
        if (brandDto == null) {
            throw new IllegalArgumentException("Brand not found");
        }
        if (brandDto.getId() != null) {
            throw new IllegalArgumentException("Brand ID should not be provided");
        }
        if (brandDto.getName() == null || brandDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Brand name cannot be null or empty");
        }
        if (brandDto.getCountryName() == null || brandDto.getCountryName().isEmpty()) {
            throw new IllegalArgumentException("Brand country name cannot be null or empty");
        }
    }

    public static void validateExistingBrand(Brand existingBrand, String name) {
        if (existingBrand != null) {
            throw new DataIntegrityViolationException("Brand with name " + name + " already exists");
        }
    }
}
