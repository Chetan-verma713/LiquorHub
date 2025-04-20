package com.example.liquorhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "N_CART_ITEMS", uniqueConstraints = {@UniqueConstraint(columnNames = {"USER_ID", "PRODUCT_ID"})})
@EqualsAndHashCode(callSuper = true)
public class CartItem extends Base {
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "QUANTITY")
    private Integer quantity;

}
