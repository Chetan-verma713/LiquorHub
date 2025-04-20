package com.example.liquorhub.repository;

import com.example.liquorhub.entity.Brand;
import com.example.liquorhub.entity.Category;
import com.example.liquorhub.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name and p.isActive = true ")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.category = :category and p.isActive = true ")
    List<Product> findByCategory(String category);

    @Query("SELECT p FROM Product p WHERE p.brand.id = :brandId and p.category.id = :categoryId and p.name = :name and p.isActive = true ")
    Product findByBrandIdAndCategoryIdAndName(Long brandId, Long categoryId, String name);

    @Query("SELECT p FROM Product p WHERE p.isActive = true ")
    List<Product> findAllActiveProducts();
}
