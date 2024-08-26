package com.onlineshoppingrestapi.orderlineitem.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.product.dto.Product;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderLine {
    @Id
    @GeneratedValue
    private int orderLineId;
    private int prodQuantity;
    @Column(name = "order_item_total")
    private int totalPrice;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "orderId")
    @JsonIgnore
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "prodId")
    private Product product;

}
