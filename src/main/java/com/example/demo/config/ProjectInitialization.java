package com.example.demo.config;

import com.example.demo.dao.Customer;
import com.example.demo.dao.Product;
import com.example.demo.dao.ShoppingCardHeader;
import com.example.demo.dao.ShoppingCardItem;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCardHeaderRepository;
import com.example.demo.repository.ShoppingCardItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Slf4j
@Configuration
@NoArgsConstructor
@AllArgsConstructor
public class ProjectInitialization {

    @Autowired
    private ShoppingCardHeaderRepository shoppingCardHeaderRepository;
    @Autowired
    private ShoppingCardItemRepository shoppingCardItemRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostConstruct
    public void init(){
        Customer customer = Customer.builder()
                .customerFullName("Alireza Molyanian")
                .build();
        Customer savedCustomer = customerRepository.save(customer);
        log.info("New Customer : {}" , savedCustomer.toString());

        Product product = Product.builder()
                .productName("Product 1")
                .build();
        Product savedProduct = productRepository.save(product);
        log.info("New Product : {}" , savedProduct.toString());

        Product product2 = Product.builder()
                .productName("Product 2")
                .build();
        Product savedProduct2 = productRepository.save(product2);
        log.info("New Product : {}" , savedProduct2.toString());

        ShoppingCardHeader shoppingCardHeader = ShoppingCardHeader.builder()
                .customer(customer)
                .date(new Date())
                .build();
        ShoppingCardHeader saveHeader = shoppingCardHeaderRepository.save(shoppingCardHeader);
        log.info("New Shopping Card Header : {}" , saveHeader.toString());

        ShoppingCardItem shoppingCardItem = ShoppingCardItem.builder()
                .product(product)
                .shoppingCardHeader(shoppingCardHeader)
                .price(70.0)
                .quantity(100)
                .build();
        ShoppingCardItem savedItem = shoppingCardItemRepository.save(shoppingCardItem);
        log.info("New Shopping Card Item: {}" , savedItem.toString());


    }

}
