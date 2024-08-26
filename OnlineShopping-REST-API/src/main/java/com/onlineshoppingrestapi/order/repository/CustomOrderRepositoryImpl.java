package com.onlineshoppingrestapi.order.repository;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.product.dto.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomOrderRepositoryImpl implements CustomOrderRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public List<Order> findOrdersByCustId(String custId) {
        String getOrderByCustId = "SELECT * FROM order_tb WHERE cust_id = '" +custId+"'";
        Query query = em.createNativeQuery(getOrderByCustId, Order.class);
        List<Order> orders  = query.getResultList();
        if (orders.size()==0){
            return null;
        }
        return orders;
    }
}
