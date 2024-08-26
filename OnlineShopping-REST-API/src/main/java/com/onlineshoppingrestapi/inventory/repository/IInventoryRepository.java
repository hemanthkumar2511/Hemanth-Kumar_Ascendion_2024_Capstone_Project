package com.onlineshoppingrestapi.inventory.repository;

import com.onlineshoppingrestapi.inventory.dto.Inventory;
import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IInventoryRepository extends JpaRepository<Inventory,Integer> {
    public List<Inventory> findByStatus(String status);
    public Inventory findByProduct(Product product);
}
