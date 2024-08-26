package com.onlineshoppingrestapi.cartlineitem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.product.dto.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class LineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;
    private int itemQuantity;
    private int unitPrice;
    private int itemTotalPrice;
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private Cart cart = new Cart();
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    private Product product = new Product();

}
