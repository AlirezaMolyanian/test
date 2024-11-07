package com.example.demo.controller.dto;

import lombok.*;

@Data
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestDTO {

    private Long shoppingCardHeaderId;
    private Long productId;
    private Double price;
    private Integer quantity;

}
