package com.onlineshoppingrestapi.order.repository;

import com.onlineshoppingrestapi.order.dto.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>,CustomOrderRepository {
    public List<Order> findOrdersByCustId(String custId);
}
