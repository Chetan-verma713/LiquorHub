package com.example.liquorhub.controller;

import com.example.liquorhub.dto.BrandDto;
import com.example.liquorhub.service.BrandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@Tag(name = "Brand Controller", description = "Manage product brands")
public class BrandController {

    private final BrandService brandService;

    @GetMapping
    @Operation(summary = "Get all brands")
    @ApiResponse(responseCode = "200", description = "List of brands fetched successfully")
    public ResponseEntity<List<BrandDto>> getAllBrands() {
        return ResponseEntity.ok(brandService.getAllBrands());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a brand by ID")
    @ApiResponse(responseCode = "200", description = "Brand found")
    @ApiResponse(responseCode = "404", description = "Brand not found")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Long id) {
        return ResponseEntity.ok(brandService.getBrandById(id));
    }

    @PostMapping
    @Operation(summary = "Add a new brand")
    @ApiResponse(responseCode = "201", description = "Brand created")
    public ResponseEntity<BrandDto> addBrand(@RequestBody BrandDto brandDto) {
        return ResponseEntity.status(201).body(brandService.addBrand(brandDto));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a brand")
    @ApiResponse(responseCode = "200", description = "Brand updated")
    public ResponseEntity<BrandDto> updateBrand(@PathVariable Long id, @RequestBody BrandDto brandDto) {
        return ResponseEntity.ok(brandService.updateBrand(id, brandDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a brand")
    @ApiResponse(responseCode = "200", description = "Brand deleted")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return ResponseEntity.ok("Brand with id " + id + " deleted successfully");
    }
}