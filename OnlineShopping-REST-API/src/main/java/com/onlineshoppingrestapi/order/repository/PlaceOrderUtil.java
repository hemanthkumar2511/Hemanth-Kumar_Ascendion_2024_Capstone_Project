package com.onlineshoppingrestapi.order.repository;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.cart.service.ICartService;
import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.inventory.dto.Inventory;
import com.onlineshoppingrestapi.inventory.service.IInventoryService;
import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.order.service.IOrderService;
import com.onlineshoppingrestapi.orderlineitem.dto.OrderLine;
import com.onlineshoppingrestapi.product.dto.Product;
import com.onlineshoppingrestapi.product.service.IProductService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PlaceOrderUtil {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IInventoryService inventoryService;

    @Autowired
    private ICartService cartService;

    public String placeOrder(String prodId,int prodQuantity, Order order) throws ProductNotFoundException {
        boolean successful = false;

        Inventory inventory = inventoryService.findByProduct(productService.findById(prodId).get());

        if (inventory == null) {
            return "Product not found in inventory.";
        }

        if(inventory.getStock()>=prodQuantity){
            orderService.createOrder(order);
            successful=true;
        }
        if (successful){
            inventory.setStock(inventory.getStock()-prodQuantity);
            inventoryService.updateInventory(inventory);

            return "Order Placed Sucessfully ...";
        }
        return "Order Failed -> Product Out Of Stock !!!";
    }

    public String placeCartOrder(String custId,Order order) throws ProductNotFoundException {
        Cart  cart = cartService.findByCustId(custId);
        List<LineItem> lineItems = cart.getLineItems();
        boolean sucessful = false;
        List<Inventory> inventoryList = new ArrayList<>();
        Inventory inventory;
        for(LineItem lineItem:lineItems){
            sucessful = false;
            inventory = inventoryService.findByProduct(productService.findById(lineItem.getProduct().getProdId()).get());
            if(inventory.getStock()>=lineItem.getItemQuantity()){
                orderService.createOrder(order);
                sucessful=true;
            }

            if (sucessful){
                inventory.setStock(inventory.getStock()-lineItem.getItemQuantity());
                inventoryList.add(inventory);
            }else{
                sucessful=false;
            }
        }
        if(sucessful){
            for(Inventory inventory1: inventoryList){
                inventoryService.updateInventory(inventory1);
            }
            cartService.removeCartByCustomer(cart.getCartId());
            return "Order Placed Sucessfully ...";
        }
        return "Order Failed -> Product Out Of Stock !!!";
    }

    public boolean cancelOrder(Order order) {
        if (order == null) {
            return false;
        }
        order.setOrderStatus("Cancelled");
        for (OrderLine orderLine : order.getOrderLines()) {
            Product product = orderLine.getProduct();
            Inventory inventory = inventoryService.findByProduct(product);

            if (inventory != null) {
                inventory.setStock(inventory.getStock() + orderLine.getProdQuantity());
                inventoryService.updateInventory(inventory);
            }
        }
        return true;
    }
}
