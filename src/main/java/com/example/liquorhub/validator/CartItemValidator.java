package com.example.liquorhub.validator;

import com.example.liquorhub.dto.CartItemDto;
import com.example.liquorhub.entity.CartItem;
import jakarta.persistence.EntityNotFoundException;

public class CartItemValidator {

    public static void validate(CartItemDto cartItemDto) {
        if (cartItemDto == null) {
            throw new IllegalArgumentException("Cart item not found");
        }
        if (cartItemDto.getQuantity() < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        if (cartItemDto.getProduct() == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
    }

    public static void validate(Long id, CartItem cartItem) {
        if (cartItem == null) {
            throw new EntityNotFoundException("Cart item with ID " + id + " not found");
        }
    }
}
