package com.onlineshoppingrestapi.cart.repository;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomCartRepositoryImpl implements CustomCartRepository {
    @PersistenceContext
    private EntityManager em;
    @Override
    public Cart findByCustId(String custId) {
        String getCartByCustId = "SELECT * FROM Cart WHERE cust_id = '" +custId+"'";
        Query query = em.createNativeQuery(getCartByCustId, Cart.class);
        List<Cart> carts = query.getResultList();
        if (carts.size()==0){
            return null;
        }
        return (Cart) carts.get(0);
    }

    @Override
    public Product findByProductId(String productId) throws ProductNotFoundException {
        Product product = em.find(Product.class, productId);
        if (product==null){
            throw new ProductNotFoundException(productId);
        }
        return product;
    }

    @Override
    public Customer findCustByCustId(String custId) throws CustomerNotFoundException {
        Customer customer = em.find(Customer.class, custId);
        if (customer==null){
            throw new CustomerNotFoundException(custId);
        }
        return customer;
    }
}
