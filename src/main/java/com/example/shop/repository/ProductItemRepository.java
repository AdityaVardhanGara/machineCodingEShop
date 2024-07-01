package com.example.shop.repository;

import com.example.shop.model.Product;
import com.example.shop.model.ProductItem;
import com.example.shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductItemRepository extends JpaRepository<ProductItem,Long> {
    Optional<ProductItem> findByUserAndProduct(User user,Product product);
}
