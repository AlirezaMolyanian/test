package com.example.demo.controller;

import com.example.demo.controller.dto.ProductRequestDTO;
import com.example.demo.dao.ShoppingCardItem;
import com.example.demo.service.CardService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/card")
@NoArgsConstructor
@AllArgsConstructor
public class cardController {

    @Autowired
    private CardService cardService;

    @PostMapping("/product/add")
    public Long addNewProduct(@RequestBody ProductRequestDTO productRequestDTO){
        return cardService.addProduct(productRequestDTO);
    }

    @GetMapping("/product/all/{shopping_card_header_id}")
    public List<ShoppingCardItem> retrieveAllTheItemFromTheCard(
            @PathVariable("shopping_card_header_id") Long shoppingCardHeaderId){
        return cardService.gatAllItems(shoppingCardHeaderId);
    }

    @DeleteMapping("/product/delete/{shopping_card_item_id}")
    public void removeItemFromTheCard(@PathVariable("shopping_card_item_id") Long shoppingCardItemId){
        cardService.remoteItem(shoppingCardItemId);
    }

    @PutMapping("/product/edit/{shopping_card_item_id}/{new_qty}")
    public ShoppingCardItem adjustQuantityOfProductOnTheCard(@PathVariable("shopping_card_item_id") Long shoppingCardItemId
            , @PathVariable("new_qty") Integer newQty){
        return cardService.editProduct(shoppingCardItemId , newQty);
    }

}
