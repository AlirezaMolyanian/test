package com.example.demo.service;

import com.example.demo.controller.dto.ProductRequestDTO;
import com.example.demo.dao.Product;
import com.example.demo.dao.ShoppingCardHeader;
import com.example.demo.dao.ShoppingCardItem;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCardHeaderRepository;
import com.example.demo.repository.ShoppingCardItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    @Autowired
    private final ShoppingCardItemRepository shoppingCardItemRepository;
    @Autowired
    private final ProductRepository productRepository;
    @Autowired
    private final CustomerRepository customerRepository;
    @Autowired
    private final ShoppingCardHeaderRepository shoppingCardHeaderRepository;

    public Long addProduct(ProductRequestDTO productRequestDTO){

        Product foundProduct = productRepository.findById(productRequestDTO.getProductId())
                .orElseThrow(() -> {
                    return new RuntimeException("Product not found.");
                });

        ShoppingCardHeader foundShoppingCardHeader = shoppingCardHeaderRepository
                .findById(productRequestDTO.getShoppingCardHeaderId())
                .orElseThrow(() -> {
                    return new RuntimeException("Shopping card not found.");
                });

        ShoppingCardItem newCardItem = ShoppingCardItem.builder()
                .product(foundProduct)
                .shoppingCardHeader(foundShoppingCardHeader)
                .price(productRequestDTO.getPrice())
                .quantity(productRequestDTO.getQuantity())
                .build();

        ShoppingCardItem saved = shoppingCardItemRepository.save(newCardItem);
        log.info("New item added: {}" , saved.getId());
        return saved.getId();
    }

    public List<ShoppingCardItem> gatAllItems(Long shoppingCardHeaderId){

        ShoppingCardHeader foundShoppingCardHeader = shoppingCardHeaderRepository
                .findById(shoppingCardHeaderId)
                .orElseThrow(() -> {
                    return new RuntimeException("Shopping card not found.");
                });

        List<ShoppingCardItem> items = foundShoppingCardHeader.getShoppingCardItemList();
        log.info("Number of retrieve items: {}" , items.size());
        return items;
    }

    public void remoteItem(Long shoppingCardItemId){
        ShoppingCardItem foundItem = shoppingCardItemRepository.findById(shoppingCardItemId)
                .orElseThrow(() -> {
                    return  new RuntimeException("Shopping Card Item not found.");
                });

        shoppingCardItemRepository.delete(foundItem);
        log.info("Shopping card deleted: {} " , foundItem.getId());
    }

    public ShoppingCardItem editProduct(Long shoppingCardItemId , Integer newQty){
        ShoppingCardItem foundItem = shoppingCardItemRepository.findById(shoppingCardItemId)
                .orElseThrow(
                        () -> {
                            return new RuntimeException("Shopping card item not found.");
                        }
                );

        foundItem.setQuantity(newQty);
        log.info("Shopping card quantity updated: {}" , foundItem.getId());
        return foundItem;
    }

}
