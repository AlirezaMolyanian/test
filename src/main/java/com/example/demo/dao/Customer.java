package com.example.demo.dao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "costomer")
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cutomer_id")
    private Long id;

    private String customerFullName;

    @OneToOne(mappedBy = "customer")
    @JsonIgnore
    private ShoppingCardHeader shoppingCardHeader;

}
