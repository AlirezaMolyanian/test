package com.example.demo.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "shopping_card_header")
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCardHeader {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "shopping_card_header_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private Date date;

    @OneToMany(mappedBy = "shoppingCardHeader", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ShoppingCardItem> shoppingCardItemList;


}
