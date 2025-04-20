package com.example.liquorhub.validator;

import com.example.liquorhub.dto.BrandDto;
import com.example.liquorhub.dto.CategoryDto;
import com.example.liquorhub.dto.ProductDto;
import com.example.liquorhub.entity.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;

public class ProductValidator {

    public static void validateProductObject(ProductDto productDTO) {
        if (productDTO == null) {
            throw new IllegalArgumentException("Product DTO is null");
        }
    }

    public static void validateProduct(ProductDto productDTO) {
        validateProductObject(productDTO);
        if (productDTO.getName() == null || productDTO.getName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (productDTO.getPrice() <= 0) {
            throw new IllegalArgumentException("Product price must be greater than zero");
        }
        validateCategory(productDTO.getCategory());
        validateBrand(productDTO.getBrand());
        validateQuantity(productDTO.getQuantity());
        if (productDTO.getAlcoholPercentage() < 0 || productDTO.getAlcoholPercentage() > 100) {
            throw new IllegalArgumentException("Alcohol percentage must be between 0 and 100");
        }
    }

    public static void validateQuantity(Integer quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Product quantity cannot be negative");
        }
    }

    public static void validateUpdateProduct(ProductDto productDTO) {
        validateProductObject(productDTO);
        if (productDTO.getId() != null) {
            throw new IllegalArgumentException("Product ID should not be provided");
        }
        validateProduct(productDTO);
    }

    private static void validateBrand(BrandDto brand) {
        if (brand == null) {
            throw new IllegalArgumentException("Brand cannot be null");
        }
        if (brand.getId() == null) {
            throw new IllegalArgumentException("Brand ID cannot be null");
        }
    }

    public static void validateProduct(Long id, Product product) {
        if (product == null) {
            throw new EntityNotFoundException("Product not found with id: " + id);
        }
    }

    private static void validateCategory(CategoryDto category) {
        if (category == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        if (category.getId() == null) {
            throw new IllegalArgumentException("Category ID cannot be null");
        }
    }

    public static void validateExistingProduct(Product existingProduct, BrandDto brand, CategoryDto category, String name) {
        if (existingProduct != null) {
            throw new DataIntegrityViolationException("Product with name " + name + " already exists for brand " + brand.getName() + " and category " + category.getName());
        }
    }
}
