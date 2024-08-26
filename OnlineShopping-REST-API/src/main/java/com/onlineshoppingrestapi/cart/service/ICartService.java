package com.onlineshoppingrestapi.cart.service;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;

public interface ICartService {
    public Cart findByCustId(String custId);
    public String addToCartByCustomer(Cart cart);
    public String removeCartByCustomer(int cartId);
    public Product findByProductId(String productId) throws ProductNotFoundException;
    public Customer findCustByCustId(String custId) throws CustomerNotFoundException;
}
