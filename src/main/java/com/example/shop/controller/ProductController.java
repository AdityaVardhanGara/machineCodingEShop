package com.example.shop.controller;

import com.example.shop.model.Product;
import com.example.shop.model.User;
import com.example.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        if(product.getExpiryTime()==null) product.setExpiryTime(LocalDateTime.now().plusHours(2));
        return ResponseEntity.ok(productService.createProduct(product));
    }
}
