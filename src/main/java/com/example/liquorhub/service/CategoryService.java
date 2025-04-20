package com.example.liquorhub.service;

import com.example.liquorhub.dto.CategoryDto;
import com.example.liquorhub.entity.Category;
import com.example.liquorhub.mapper.CategoryMapper;
import com.example.liquorhub.repository.CategoryRepository;
import com.example.liquorhub.validator.CategoryValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categoryMapper.toDtoList(categories);
    }

    public CategoryDto getCategoryById(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        CategoryValidator.validate(id, category);
        return categoryMapper.toDto(category);
    }

    public CategoryDto addCategory(CategoryDto categoryDto) {
        CategoryValidator.validate(categoryDto);
        Category existingCategory = categoryRepository.findByName(categoryDto.getName());
        CategoryValidator.validateExistingCategory(existingCategory, categoryDto.getName());
        Category category = categoryMapper.toEntity(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toDto(savedCategory);
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        Category category = categoryRepository.findById(id).orElse(null);
        CategoryValidator.validate(id, category);
        CategoryValidator.validate(categoryDto);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
        return categoryMapper.toDto(category);
    }

    public void deleteCategory(Long id) {
        Category category = categoryRepository.findById(id).orElse(null);
        CategoryValidator.validate(id, category);
        categoryRepository.delete(category);
    }

}
