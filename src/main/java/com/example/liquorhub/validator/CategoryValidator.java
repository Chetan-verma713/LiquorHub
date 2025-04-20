package com.example.liquorhub.validator;

import com.example.liquorhub.dto.CategoryDto;
import com.example.liquorhub.entity.Category;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

public class CategoryValidator {

    public static void validate(Long id, Category category) {
        if (category == null) {
            throw new EntityNotFoundException("Category with ID " + id + " not found");
        }
    }

    public static void validate(CategoryDto categoryDto) {
        if (categoryDto == null) {
            throw new IllegalArgumentException("Category not found");
        }
        if (categoryDto.getId() != null) {
            throw new IllegalArgumentException("Category ID should not be provided");
        }
        if (categoryDto.getName() == null || categoryDto.getName().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be null or empty");
        }
    }

    public static void validateExistingCategory(Category existingCategory, String name) {
        if (existingCategory != null) {
            throw new DataIntegrityViolationException("Category with name " + name + " already exists");
        }
    }
}
