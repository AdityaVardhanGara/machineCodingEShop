package com.example.shop.controller;

import com.example.shop.exception.OutOfStockException;
import com.example.shop.model.Product;
import com.example.shop.model.ProductItem;
import com.example.shop.service.ProductItemService;
import com.example.shop.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/product-item")
public class ProductItemController {
    @Autowired
    private ProductItemService productItemService;
    @PostMapping("/buy")
    public ResponseEntity<OrderStatus> buyProduct(@RequestParam Long userId, @RequestParam Long productId) {
        try {
            return ResponseEntity.ok(productItemService.buyProduct(userId, productId));
        }catch (OutOfStockException e){
            return ResponseEntity.status(500).body(OrderStatus.QUANTITY_ZERO);
        }
    }

}
