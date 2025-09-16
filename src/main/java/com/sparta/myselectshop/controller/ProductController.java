package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.entity.Product;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    @PostMapping("/products")
    public ProductResponseDto CreateProduct(@RequestBody ProductResponseDto productResponseDto) {
        return productService.createProduct(productResponseDto);
    }
}
