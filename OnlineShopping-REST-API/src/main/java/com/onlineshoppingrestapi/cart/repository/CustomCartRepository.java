package com.onlineshoppingrestapi.cart.repository;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;

public interface CustomCartRepository {
    public Cart findByCustId(String custId);
    public Product findByProductId(String productId) throws ProductNotFoundException;
    public Customer findCustByCustId(String custId) throws CustomerNotFoundException;
}
