package com.onlineshoppingrestapi.inventory.service;

import com.onlineshoppingrestapi.inventory.dto.Inventory;
import com.onlineshoppingrestapi.inventory.repository.IInventoryRepository;
import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryServiceImpl implements IInventoryService{
    @Autowired
    private IInventoryRepository inventoryRepository;

    @Override
    public String addInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Inventory Added Successfully ...";
    }

    @Override
    public String updateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
        return "Inventory Updated Successfully ...";
    }

    @Override
    public String deleteInventory(int inventoryId) {
        inventoryRepository.deleteById(inventoryId);
        return "Inventory Deleted Successfully ...";
    }

    @Override
    public List<Inventory> findAllInventory() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory findById(int inventoryId) {
        return inventoryRepository.findById(inventoryId).get();
    }

    @Override
    public List<Inventory> findByStatus(String status) {
        return inventoryRepository.findByStatus(status);
    }

    @Override
    public Inventory findByProduct(Product product) {
        return inventoryRepository.findByProduct(product);
    }
}
