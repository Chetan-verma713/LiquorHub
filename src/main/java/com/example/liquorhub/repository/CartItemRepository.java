package com.example.liquorhub.repository;

import com.example.liquorhub.dto.ProductDto;
import com.example.liquorhub.dto.UserDto;
import com.example.liquorhub.entity.CartItem;
import com.example.liquorhub.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("SELECT c FROM CartItem c WHERE c.user.id =:user")
    List<CartItem> getCartItemByUserId(Long userId);

    @Query("SELECT c FROM CartItem c WHERE c.user = :user and c.product = :product")
    CartItem getCartItemByUserAndProduct(User currentUser, ProductDto product);

    default List<CartItem> getCartItemByUser(User user) {
        return getCartItemByUserId(user.getId());
    }
}
