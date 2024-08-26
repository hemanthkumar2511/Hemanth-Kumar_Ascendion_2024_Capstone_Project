package com.onlineshoppingrestapi.product.service;

import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;
import com.onlineshoppingrestapi.product.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductRepository productRepository;

    @Override
    public String addProduct(Product product) {
        productRepository.save(product);
        return "Product Added Sucessfully ...";
    }

    @Override
    public String updateProduct(Product product) {
        productRepository.save(product);
        return "Product Updated Sucessfully ...";
    }

    @Override
    public String deleteProduct(String prodId){

        productRepository.deleteById(prodId);
        return "Product Deleted Sucessfully ...";
    }

    @Override
    public Optional<Product> findById(String prodId) throws ProductNotFoundException{
        Optional<Product> product = productRepository.findById(prodId);
        if(product.isEmpty()){
            throw new ProductNotFoundException(prodId);
        }
        return product;
    }

    @Override
    public List<Product> findByProdName(String prodName){
        return productRepository.findByProdName(prodName);
    }

    @Override
    public List<Product> findByCategory(String category){
        return productRepository.findByCategory(category);
    }

    @Override
    public List<Product> findByPriceGreaterThan(int minPrice){
        return productRepository.findByPriceGreaterThan(minPrice);
    }

    @Override
    public List<Product> findByPriceLessThan(int maxPrice){
        return productRepository.findByPriceLessThan(maxPrice);
    }

    @Override
    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }
}
