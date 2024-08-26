package com.onlineshoppingrestapi.product.controller;

import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.product.dto.Product;
import com.onlineshoppingrestapi.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @PostMapping("/addproduct")
    public String addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/updateproduct")
    public String updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    @DeleteMapping("/deleteproduct/{prodId}")
    public String deleteProduct(@PathVariable("prodId") String prodId){
        return productService.deleteProduct(prodId);
    }

    @GetMapping("/findbyid/{prodId}")
    public Optional<Product> findById(@PathVariable("prodId") String prodId) throws ProductNotFoundException {
        return productService.findById(prodId);
    }

    @GetMapping("/findbyname/{prodName}")
    public List<Product> findByProdName(@PathVariable("prodName") String prodName){
        return productService.findByProdName(prodName);
    }

    @GetMapping("/findbycategory/{category}")
    public List<Product> findByCategory(@PathVariable("category") String category){
        return productService.findByCategory(category);
    }

    @GetMapping("/findBypricegreaterthan/{minPrice}")
    public List<Product> findByPriceGreaterThan(@PathVariable("minPrice") int minPrice){
        return productService.findByPriceGreaterThan(minPrice);
    }

    @GetMapping("/findBypricelessthan/{maxPrice}")
    public List<Product> findByPriceLessThan(@PathVariable("maxPrice") int maxPrice){
        return productService.findByPriceLessThan(maxPrice);
    }

    @GetMapping("/findallproduct")
    public List<Product> findAllProduct(){
        return productService.findAllProduct();
    }
}
