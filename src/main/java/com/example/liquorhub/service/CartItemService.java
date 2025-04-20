package com.example.liquorhub.service;

import com.example.liquorhub.dto.CartItemDto;
import com.example.liquorhub.dto.UserDto;
import com.example.liquorhub.entity.CartItem;
import com.example.liquorhub.entity.User;
import com.example.liquorhub.mapper.CartItemMapper;
import com.example.liquorhub.repository.CartItemRepository;
import com.example.liquorhub.security.WebUtils;
import com.example.liquorhub.validator.CartItemValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;
    private final User currentUser;

    public CartItemService(CartItemRepository cartItemRepository, CartItemMapper cartItemMapper) {
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
        this.currentUser = WebUtils.getCurrentUser();
    }

    public List<CartItemDto> getAllCartItems() {
        List<CartItem> cartItems = cartItemRepository.getCartItemByUser(currentUser);
        return cartItemMapper.toDtoList(cartItems);
    }

    public List<CartItemDto> addCartItem(CartItemDto cartItemDto) {
        CartItem existingCartItem = cartItemRepository.getCartItemByUserAndProduct(currentUser, cartItemDto.getProduct());
        if (existingCartItem != null) {
            existingCartItem.setQuantity(existingCartItem.getQuantity() + cartItemDto.getQuantity());
            if (existingCartItem.getQuantity() == 0) {
                existingCartItem.setIsActive(Boolean.FALSE);
            } else {
                existingCartItem.setIsActive(Boolean.TRUE);
            }
            cartItemRepository.save(existingCartItem);
        } else {
            CartItemValidator.validate(cartItemDto);
            CartItem cartItem = cartItemMapper.toEntity(cartItemDto);
            cartItemRepository.save(cartItem);
        }
        return cartItemMapper.toDtoList(cartItemRepository.getCartItemByUser(currentUser));
    }

    public List<CartItemDto> updateCartItem(Long id, CartItemDto cartItemDto) {
        CartItem cartItem = cartItemRepository.findById(id).orElse(null);
        CartItemValidator.validate(id, cartItem);
        cartItem.setQuantity(cartItemDto.getQuantity());
        if (cartItemDto.getQuantity() == 0) {
            cartItem.setIsActive(Boolean.FALSE);
        } else {
            cartItem.setIsActive(Boolean.TRUE);
        }
        cartItemRepository.save(cartItem);
        return cartItemMapper.toDtoList(cartItemRepository.getCartItemByUser(currentUser));
    }

    public void clearCartByUser(User user) {
        List<CartItem> cartItems = cartItemRepository.getCartItemByUser(user);
        for (CartItem cartItem : cartItems) {
            cartItem.setQuantity(0);
            cartItem.setIsActive(Boolean.FALSE);
        }
        cartItemRepository.saveAll(cartItems);
    }

}
