package com.onlineshoppingrestapi.order.service;

import com.onlineshoppingrestapi.order.dto.Order;

import java.util.List;

public interface IOrderService {
    public Order createOrder(Order order);
    public List<Order> getOrdersByCustId(String custId);
}
