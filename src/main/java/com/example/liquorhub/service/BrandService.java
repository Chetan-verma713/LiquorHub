package com.example.liquorhub.service;

import com.example.liquorhub.dto.BrandDto;
import com.example.liquorhub.entity.Brand;
import com.example.liquorhub.mapper.BrandMapper;
import com.example.liquorhub.repository.BrandRepository;
import com.example.liquorhub.validator.BrandValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;
    private final BrandMapper brandMapper;

    public List<BrandDto> getAllBrands() {
        return brandMapper.toDtoList(brandRepository.findAll());
    }

    public BrandDto getBrandById(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        BrandValidator.validate(id, brand);
        return brandMapper.toDto(brand);
    }

    public BrandDto addBrand(BrandDto brandDto) {
        BrandValidator.validate(brandDto);
        Brand existingBrand = brandRepository.findByName(brandDto.getName());
        BrandValidator.validateExistingBrand(existingBrand, brandDto.getName());
        Brand brand = brandMapper.toEntity(brandDto);
        brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    public BrandDto updateBrand(Long id, BrandDto brandDto) {
        Brand brand = brandRepository.findById(id).orElse(null);
        BrandValidator.validate(id, brand);
        BrandValidator.validate(brandDto);
        brand.setName(brandDto.getName());
        brand.setDescription(brandDto.getDescription());
        brandRepository.save(brand);
        return brandMapper.toDto(brand);
    }

    public void deleteBrand(Long id) {
        Brand brand = brandRepository.findById(id).orElse(null);
        BrandValidator.validate(id, brand);
        brandRepository.delete(brand);
    }
}
