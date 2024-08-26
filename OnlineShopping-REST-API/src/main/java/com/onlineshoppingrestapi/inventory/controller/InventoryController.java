package com.onlineshoppingrestapi.inventory.controller;

import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.inventory.dto.Inventory;
import com.onlineshoppingrestapi.inventory.service.IInventoryService;
import com.onlineshoppingrestapi.product.dto.Product;
import com.onlineshoppingrestapi.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shop/inventory")
public class InventoryController {
    @Autowired
    private IInventoryService inventoryService;
    @Autowired
    private IProductService productService;

    @PostMapping("/addinventory/{prodId}")
    public String addInventory(@RequestBody Inventory inventory, @PathVariable("prodId") String prodId){
        Product product = new Product();
        product.setProdId(prodId);
        inventory.setProduct(product);
        return inventoryService.addInventory(inventory);
    }

    @PutMapping("/updateinventory/{prodId}")
    public String updateInventory(@RequestBody Inventory inventory, @PathVariable("prodId") String prodId) throws ProductNotFoundException {
        Product product = productService.findById(prodId).get();
        inventory.setProduct(product);
        return inventoryService.updateInventory(inventory);
    }

    @DeleteMapping("/deleteinventory/{inventoryId}")
    public String deleteInventory(@PathVariable("inventoryId") int inventoryId, @PathVariable("prodId") String prodId){
        return inventoryService.deleteInventory(inventoryId);
    }

    @GetMapping("/findallinventory")
    public List<Inventory> findAllInventory(){
        return inventoryService.findAllInventory();
    }

    @GetMapping("/findbyid/{inventoryId}")
    public Inventory findById(@PathVariable("inventoryId") int inventoryId){
        return inventoryService.findById(inventoryId);
    }

    @GetMapping("/findbystatus/{status}")
    public List<Inventory> findByStatus(@PathVariable("status") String status){
        return inventoryService.findByStatus(status);
    }
}
