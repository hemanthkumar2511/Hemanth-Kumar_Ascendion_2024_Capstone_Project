package com.onlineshoppingrestapi.customer.controller;

import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.customer.service.ICustomerService;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/shop/customers")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @PostMapping("/addcustomer")
    public String addCustomer(@RequestBody Customer customer){
        return customerService.addCustomer(customer);
    }

    @PutMapping("/updatecustomer")
    public String updateCustomer(@RequestBody Customer customer){
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping("/deletecustomer/{custId}")
    public String deleteCustomer(@PathVariable("custId") String custId){
        return customerService.deleteCustomer(custId);
    }

    @GetMapping("/findbyid/{custId}")
    public Optional<Customer> findById(@PathVariable("custId") String custId) throws CustomerNotFoundException {
        return customerService.findById(custId);
    }

    @GetMapping("/findbyname/{custName}")
    public List<Customer> findByCustName(@PathVariable("custName") String custName){
        return customerService.findByCustName(custName);
    }

    @GetMapping("/findbycity/{custCity}")
    public List<Customer> findByCustCity(@PathVariable("custCity") String custCity){
        return customerService.findByCustCity(custCity);
    }

    @GetMapping("/findbypincode/{pincode}")
    public List<Customer> findByPincode(@PathVariable("pincode") int pincode){
        return customerService.findByPincode(pincode);
    }

    @GetMapping("/findallcustomer")
    public List<Customer> findAllCustomer(){
        return customerService.findAllCustomer();
    }

}
