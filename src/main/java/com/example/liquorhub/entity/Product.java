package com.example.liquorhub.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@Entity
@Table(name = "N_PRODUCTS", uniqueConstraints = {@UniqueConstraint(columnNames = {"NAME", "BRAND_ID", "CATEGORY_ID"})})
@EqualsAndHashCode(callSuper = true)
public class Product extends Base {

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Column(name = "PRICE")
    private Double price;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brand brand;

    @Column(name = "ALCOHOL_PERCENTAGE")
    private Integer alcoholPercentage;

    @OneToMany(mappedBy = "product")
    private List<CartItem> cartItems;

}
