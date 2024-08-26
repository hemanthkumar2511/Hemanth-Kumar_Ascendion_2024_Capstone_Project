package com.onlineshoppingrestapi.product.repository;

import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,String> {
    public List<Product> findByProdName(String prodName);
    public List<Product> findByCategory(String category);
    public List<Product> findByPriceGreaterThan(int minPrice);
    public List<Product> findByPriceLessThan(int maxPrice);
}
