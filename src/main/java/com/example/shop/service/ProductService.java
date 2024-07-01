package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.model.User;
import com.example.shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }
}
