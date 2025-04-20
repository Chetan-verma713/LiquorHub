package com.example.liquorhub.service;

import com.example.liquorhub.dto.ProductDto;
import com.example.liquorhub.entity.Product;
import com.example.liquorhub.mapper.ProductMapper;
import com.example.liquorhub.repository.ProductRepository;
import com.example.liquorhub.validator.ProductValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAllActiveProducts();
        return productMapper.toDtoList(products);
    }

    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        ProductValidator.validateProduct(id, product);
        return productMapper.toDto(product);
    }

    public ProductDto addProduct(ProductDto productDTO) {
        ProductValidator.validateProduct(productDTO);
        Product existingProduct = productRepository.findByBrandIdAndCategoryIdAndName(productDTO.getBrand().getId(), productDTO.getCategory().getId(), productDTO.getName());
        ProductValidator.validateExistingProduct(existingProduct, productDTO.getBrand(), productDTO.getCategory(), productDTO.getName());
        Product product = productMapper.toEntity(productDTO);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    public ProductDto updateProduct(Long id, ProductDto productDTO) {
        ProductValidator.validateUpdateProduct(productDTO);
        Product product = productRepository.findById(id).orElse(null);
        ProductValidator.validateProduct(id, product);
        Product updatedProduct = productMapper.toEntity(productDTO);
        updatedProduct.setId(id);
        updatedProduct.setIsActive(Boolean.TRUE);
        productRepository.save(product);
        return productMapper.toDto(updatedProduct);
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        ProductValidator.validateProduct(id, product);
        product.setIsActive(Boolean.FALSE);
        productRepository.save(product);
    }

    private Product getValidatedProduct(Long id, ProductDto productDTO) {
        ProductValidator.validateProductObject(productDTO);
        Product product = productRepository.findById(id).orElse(null);
        ProductValidator.validateProduct(id, product);
        return product;
    }

}
