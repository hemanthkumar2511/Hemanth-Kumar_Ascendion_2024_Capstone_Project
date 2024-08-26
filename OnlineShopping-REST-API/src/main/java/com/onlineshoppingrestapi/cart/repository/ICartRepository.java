package com.onlineshoppingrestapi.cart.repository;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICartRepository extends JpaRepository<Cart,Integer>, CustomCartRepository {
    public Cart findByCustId(String custId);
    public Product findByProductId(String productId) throws ProductNotFoundException;
    public Customer findCustByCustId(String custId) throws CustomerNotFoundException;
}
