package com.onlineshoppingrestapi.product.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Product {
    @Id
    private String prodId;
    private String prodName;
    private String category;
    private int price;

    @PrePersist
    public void genrateId(){
        prodId = prodName.substring(0,2).toUpperCase()+
                category.substring(0,2).toUpperCase();
    }
}
