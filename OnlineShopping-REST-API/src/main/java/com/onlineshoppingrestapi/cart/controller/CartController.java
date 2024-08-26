package com.onlineshoppingrestapi.cart.controller;

import com.onlineshoppingrestapi.cart.dto.Cart;
import com.onlineshoppingrestapi.cart.service.ICartService;
import com.onlineshoppingrestapi.cartlineitem.dto.LineItem;
import com.onlineshoppingrestapi.cartlineitem.service.ILineItemService;
import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.order.repository.PlaceOrderUtil;
import com.onlineshoppingrestapi.orderlineitem.dto.OrderLine;
import com.onlineshoppingrestapi.product.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shop/cart")
public class CartController {
    @Autowired
    private ICartService cartService;

    @Autowired
    private ILineItemService lineItemService;

    @Autowired
    private PlaceOrderUtil placeOrderUtil;

    @PostMapping("/addtocart")
    public String addToCartByCustomer(@RequestParam("prodId") String prodId, @RequestParam("custId") String custId) throws ProductNotFoundException, CustomerNotFoundException {
        Cart cart1 = cartService.findByCustId(custId);
        Product product = cartService.findByProductId(prodId);
        Customer customer = cartService.findCustByCustId(custId);
        if(cart1==null){
            Cart cart = new Cart();
            cart.setCustomer(customer);
            LineItem lineItem = new LineItem();
            lineItem.setItemQuantity(1);
            lineItem.setUnitPrice(product.getPrice());
            lineItem.setItemTotalPrice(lineItem.getUnitPrice()*lineItem.getItemQuantity());
            lineItem.setProduct(product);
            lineItem.setCart(cart);
            cart.getLineItems().add(lineItem);
            cartService.addToCartByCustomer(cart);
            return "Cart And LineItem Added Sucessfully ...";
        }
        else {
            LineItem lineItemExist = null;
            for(LineItem li:cart1.getLineItems()){
                if(li.getProduct().getProdId().equals(product.getProdId())){
                    lineItemExist = li;
                    break;
                }
            }
            if(lineItemExist!=null){
                lineItemExist.setItemQuantity(lineItemExist.getItemQuantity()+1);
                lineItemExist.setItemTotalPrice(lineItemExist.getUnitPrice()*lineItemExist.getItemQuantity());
                lineItemService.addLineItem(lineItemExist);
                return "LineItem Updated Sucessfully ...";
            }
            else {
                lineItemExist=new LineItem();
                lineItemExist.setItemQuantity(1);
                lineItemExist.setUnitPrice(product.getPrice());
                lineItemExist.setItemTotalPrice(lineItemExist.getUnitPrice()*lineItemExist.getItemQuantity());
                lineItemExist.setProduct(product);
                lineItemExist.setCart(cart1);
                lineItemService.addLineItem(lineItemExist);
            }
            return "LineItem Added Sucessfully ...";
        }
    }

    @DeleteMapping("/removefromcart")
    public String removeFromCartByCustomer(@RequestParam("prodId") String prodId, @RequestParam("custId") String custId) throws CustomerNotFoundException, ProductNotFoundException {
        cartService.findByProductId(prodId);
        cartService.findCustByCustId(custId);
        Cart cart1 = cartService.findByCustId(custId);
        if(cart1==null){
            return "No Cart Found For Customer !!!";
        }
        else {
                LineItem lineItemToRemove = null;
                for(LineItem li:cart1.getLineItems()){
                    if(li.getProduct().getProdId().equals(prodId)){
                        lineItemToRemove = li;
                        break;
                    }
                }
                if(lineItemToRemove !=null && lineItemToRemove.getItemQuantity()>1){
                    lineItemToRemove.setItemQuantity(lineItemToRemove.getItemQuantity()-1);
                    lineItemToRemove.setItemTotalPrice(lineItemToRemove.getUnitPrice()* lineItemToRemove.getItemQuantity());
                    lineItemService.updateLineItem(lineItemToRemove);
                    return "LineItem Quantity Reduced Sucessfully ...";
                }
                else if (lineItemToRemove !=null ){
                    cart1.getLineItems().remove(lineItemToRemove);
                    lineItemService.deleteLineItem(lineItemToRemove.getItemId());
                    return "LineItem Removed Sucessfully ...";
                }
                else {
                    return "Cart Has No LineItem";
                }
        }
    }

    @GetMapping("/viewcart")
    public List<LineItem> viewCartByCustomer(@RequestParam("custId") String custId) throws CustomerNotFoundException {

        cartService.findCustByCustId(custId);

        Cart cart1 = cartService.findByCustId(custId);
        return cart1.getLineItems();
    }

    @DeleteMapping("/removecart")
    public String removeCartByCustomer(@RequestParam("custId") String custId) throws CustomerNotFoundException {
        cartService.findCustByCustId(custId);
        Cart cart1 = cartService.findByCustId(custId);
        cartService.removeCartByCustomer(cart1.getCartId());
        return "Cart Removed Sucessfully ...";
    }

    @PostMapping("/cart/placeorder")
    public String placeOrderFromCart(@RequestParam("custId") String custId) throws ProductNotFoundException, CustomerNotFoundException {
        cartService.findCustByCustId(custId);
        Cart cart = cartService.findByCustId(custId);
        if(cart==null){
            return "No Cart Found";
        }
        List<LineItem> lineItems = cart.getLineItems();
        Order order = new Order();
        order.setCustomer(cart.getCustomer());
        order.setOrderStatus("Ordered");
        order.setOrderDate(LocalDate.now());
        OrderLine orderLine ;
        if(lineItems.size()==0){
            return "Empty Cart";
        }
        for(LineItem lineItem:lineItems){
            orderLine = new OrderLine();
            orderLine.setProdQuantity(lineItem.getItemQuantity());
            orderLine.setProduct(lineItem.getProduct());
            orderLine.setTotalPrice(lineItem.getItemTotalPrice());
            order.getOrderLines().add(orderLine);
            order.setOrderTotal(order.getOrderTotal()+orderLine.getTotalPrice());
            orderLine.setOrder(order);
        }
        return placeOrderUtil.placeCartOrder(custId,order);
    }

}
