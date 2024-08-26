package com.onlineshoppingrestapi.order.controller;

import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.customer.service.ICustomerService;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import com.onlineshoppingrestapi.exception.dto.ProductNotFoundException;
import com.onlineshoppingrestapi.order.dto.Order;
import com.onlineshoppingrestapi.order.repository.PlaceOrderUtil;
import com.onlineshoppingrestapi.order.service.IOrderService;
import com.onlineshoppingrestapi.orderlineitem.dto.OrderLine;
import com.onlineshoppingrestapi.product.dto.Product;
import com.onlineshoppingrestapi.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/order")
public class OrderController {
    @Autowired
    private PlaceOrderUtil placeOrderUtil;

    @Autowired
    private IProductService productService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private IOrderService orderService;

    @PostMapping("/placeorder")
    public String placeOrder(@RequestParam("custId") String custId, @RequestParam("prodId") String prodId, @RequestParam("prodQuantity") int prodQuantity) throws ProductNotFoundException, CustomerNotFoundException {
        Optional<Customer> customer = customerService.findById(custId);
        if (customer == null) {
            return "Customer not found!";
        }
        Order order = new Order();
        order.setCustomer(customer.get());
        order.setOrderStatus("Ordered");
        order.setOrderDate(LocalDate.now());
        order.setOrderLines(new ArrayList<>());


        Optional<Product> product = productService.findById(prodId);

        if (product == null) {
            return "Product with ID " + prodId + " not found!";
        }

        OrderLine orderLine = new OrderLine();
        orderLine.setTotalPrice(product.get().getPrice()*prodQuantity);
        orderLine.setProdQuantity(prodQuantity);
        orderLine.setOrder(order);
        orderLine.setProduct(product.get());

        order.setOrderTotal(orderLine.getTotalPrice());
        order.getOrderLines().add(orderLine);


        return placeOrderUtil.placeOrder(prodId,prodQuantity,order);
    }

    @GetMapping("/api/shop/vieworder")
    public List<Order> viewOrder(@RequestParam("custId") String custId) throws CustomerNotFoundException {

        customerService.findById(custId);

        List<Order> orders = orderService.getOrdersByCustId(custId);
//        List<OrderLine> allOrderLines = new ArrayList<>();
//        for (Order order : orders) {
//            if (order != null) {
//                allOrderLines.addAll(order.getOrderLines());
//            }
//        }
        return orders;
    }

    @DeleteMapping("/cancelorder")
    public String cancelOrder(@RequestParam("custId") String custId, @RequestParam("orderId") Long orderId) throws CustomerNotFoundException {

        customerService.findById(custId);

        List<Order> orders = orderService.getOrdersByCustId(custId);

        Order orderToCancel = orders.stream()
                .filter(order -> order.getOrderId()==(orderId))
                .findFirst()
                .orElse(null);

        if (orderToCancel == null) {
            return "Order with ID " + orderId + " not found for customer ID " + custId+" !!!";
        }

        boolean success = placeOrderUtil.cancelOrder(orderToCancel);

        if (success) {
            return "Order With ID " + orderId + " Cancelled Successfully ...";
        } else {
            return "Order Cancellation Failed !!!";
        }
    }
}
