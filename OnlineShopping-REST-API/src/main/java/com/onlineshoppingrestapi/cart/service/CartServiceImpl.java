package com.onlineshoppingrestapi.cart.service;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.cart.repository.ICartRepository;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements ICartService{
    @Autowired
    private ICartRepository cartRepository;

    @Override
    public Cart findByCustId(String custId) {
        return cartRepository.findByCustId(custId);
    }

    @Override
    public String addToCartByCustomer(Cart cart) {
        cartRepository.save(cart);
        return "Item Added To Cart Sucessfully ...";
    }

    @Override
    public String removeCartByCustomer(int cartId) {
        cartRepository.deleteById(cartId);
        return "All Items From Cart Removed Sucessfully ...";
    }

    @Override
    public Product findByProductId(String productId) throws ProductNotFoundException {
        return cartRepository.findByProductId(productId);
    }

    @Override
    public Customer findCustByCustId(String custId) throws CustomerNotFoundException {
        return cartRepository.findCustByCustId(custId);
    }


}
