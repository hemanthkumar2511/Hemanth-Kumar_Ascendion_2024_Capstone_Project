package com.onlineshoppingrestapi.order.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.orderlineitem.dto.OrderLine;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Table(name = "order_tb")
public class Order {
    @Id
    @GeneratedValue
    private int orderId;
    private String orderStatus;
    private LocalDate orderDate;
    private int orderTotal;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "custId")
    private Customer customer;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "order")
    private List<OrderLine> orderLines = new ArrayList<>();

}
