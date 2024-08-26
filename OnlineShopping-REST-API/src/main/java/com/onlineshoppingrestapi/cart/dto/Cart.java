package com.onlineshoppingrestapi.cart.dto;

import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Cart {
    @Id
    @GeneratedValue
    private int cartId;
    @ManyToOne( fetch = FetchType.EAGER)
    @JoinColumn(name = "custId")
    private Customer customer = new Customer();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "cartId")
    private List<LineItem> lineItems = new ArrayList<>();
}