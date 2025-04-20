package com.example.liquorhub.controller;

import com.example.liquorhub.dto.CartItemDto;
import com.example.liquorhub.service.CartItemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart-items")
@RequiredArgsConstructor
@Tag(name = "CartItem", description = "Operations related to Cart Items")
public class CartItemController {

    private final CartItemService cartItemService;

    @Operation(summary = "Get all cart items")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved cart items",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class))))
    })
    @GetMapping
    public ResponseEntity<List<CartItemDto>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.getAllCartItems());
    }

    @Operation(summary = "Add an item to the cart")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Item successfully added to the cart",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<List<CartItemDto>> addCartItem(@RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.status(201).body(cartItemService.addCartItem(cartItemDto));
    }

    @Operation(summary = "Update an existing cart item")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cart item updated successfully",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = CartItemDto.class)))),
            @ApiResponse(responseCode = "404", description = "Cart item not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<List<CartItemDto>> updateCartItem(@PathVariable Long id, @RequestBody CartItemDto cartItemDto) {
        return ResponseEntity.ok(cartItemService.updateCartItem(id, cartItemDto));
    }
}