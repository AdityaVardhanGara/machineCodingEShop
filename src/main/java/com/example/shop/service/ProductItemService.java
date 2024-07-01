package com.example.shop.service;

import com.example.shop.exception.OutOfStockException;
import com.example.shop.model.Product;
import com.example.shop.model.ProductItem;
import com.example.shop.model.User;
import com.example.shop.repository.ProductItemRepository;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.UserRepository;
import com.example.shop.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductItemService {
    @Autowired
    private ProductItemRepository productItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    public ProductItem createProductItem(ProductItem productItem) {
        return productItemRepository.save(productItem);
    }

    public OrderStatus buyProduct(Long userId, Long productId) throws OutOfStockException{
        Optional<User> user=userRepository.findById(userId);
        Optional<Product> product=productRepository.findById(productId);
        if(user.isPresent()&&product.isPresent()){
            Product p=product.get();
            if(p.getExpiryTime().isBefore(LocalDateTime.now())){
                return OrderStatus.PRODUCT_EXPIRED;
            }
            if(p.getQuantity()<=0){
                throw new OutOfStockException("The product is out of stock");
            }
            Optional<ProductItem> existingPurchase=productItemRepository.findByUserAndProduct(user.get(),p);
            if(existingPurchase.isPresent()){
                return OrderStatus.USER_ALREADY_BOUGHT;
            }
            ProductItem productItem=new ProductItem();
            productItem.setUser(user.get());
            productItem.setProduct(p);
            productItemRepository.save(productItem);
            p.setQuantity(p.getQuantity()-1);
            productRepository.save(p);
            return OrderStatus.SUCCESS;
        }
        return OrderStatus.FAIL;
    }
}
