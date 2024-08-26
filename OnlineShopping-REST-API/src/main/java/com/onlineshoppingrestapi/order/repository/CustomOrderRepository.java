package com.onlineshoppingrestapi.order.repository;
import com.onlineshoppingrestapi.order.dto.Order;

import java.util.List;


public interface CustomOrderRepository {
    public List<Order> findOrdersByCustId(String custId);
}
