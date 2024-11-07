package com.example.demo.dao;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Entity
@Builder
@ToString
@Table(name = "shopping_card_items")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCardItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_card_item_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "shopping_card_header_id")
    private ShoppingCardHeader shoppingCardHeader;

    private Double price;

    private Integer quantity;

}
