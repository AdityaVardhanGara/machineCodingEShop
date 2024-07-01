package com.example.shop.service;

import com.example.shop.model.Product;
import com.example.shop.model.User;
import com.example.shop.repository.ProductItemRepository;
import com.example.shop.util.OrderStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
public class ProductItemServiceTest {
    @Mock
    private ProductItemRepository productItemRepository;
    @Mock
    private ProductItemService productItemService;
    private User user;
    private Product product;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        this.user=new User();
        user.setId(1L);
        user.setUsername("name");
        user.setEmail("name");
        this.product=new Product();
        product.setId(1L);
        product.setExpiryTime(LocalDateTime.now());
    }
    @Test
    void testPurchase_success(){
        Mockito.when(productItemRepository.findByUserAndProduct(user,product)).thenReturn(Optional.empty());
        OrderStatus result=productItemService.buyProduct(user.getId(),product.getId());
        Assertions.assertNull(result);
    }

}
