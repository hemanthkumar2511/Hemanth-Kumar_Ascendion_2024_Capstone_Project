package com.onlineshoppingrestapi.inventory.dto;

import com.onlineshoppingrestapi.product.dto.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;
    private String dealerName;
    private int stock;
    private String status;
    @OneToOne(fetch = FetchType.EAGER)
    private Product product;
}
