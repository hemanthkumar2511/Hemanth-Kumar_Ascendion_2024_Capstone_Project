package com.onlineshoppingrestapi.inventory.service;

import com.onlineshoppingrestapi.inventory.dto.Inventory;
import com.onlineshoppingrestapi.product.dto.Product;

import java.util.List;

public interface IInventoryService {
    public String addInventory(Inventory inventory);
    public String updateInventory(Inventory inventory);
    public String deleteInventory(int inventoryId);
    public List<Inventory> findAllInventory();
    public Inventory findById(int inventoryId);
    public List<Inventory> findByStatus(String status);
    public Inventory findByProduct(Product product);
}
