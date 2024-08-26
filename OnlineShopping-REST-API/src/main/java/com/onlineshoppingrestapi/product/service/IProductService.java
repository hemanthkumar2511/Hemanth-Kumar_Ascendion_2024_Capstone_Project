package com.onlineshoppingrestapi.product.service;

import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    public String addProduct(Product product);
    public String updateProduct(Product product) ;
    public String deleteProduct(String prodId);
    public Optional<Product> findById(String prodId) throws ProductNotFoundException;
    public List<Product> findByProdName(String prodName);
    public List<Product> findByCategory(String category);
    public List<Product> findByPriceGreaterThan(int minPrice);
    public List<Product> findByPriceLessThan(int maxPrice);
    public List<Product> findAllProduct();
}
