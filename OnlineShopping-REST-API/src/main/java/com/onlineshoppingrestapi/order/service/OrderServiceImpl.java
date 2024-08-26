package com.onlineshoppingrestapi.order.service;

import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrdersByCustId(String custId) {
        return orderRepository.findOrdersByCustId(custId);
    }
}
