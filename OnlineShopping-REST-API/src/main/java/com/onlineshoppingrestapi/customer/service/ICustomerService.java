package com.onlineshoppingrestapi.customer.service;

import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {
    public String addCustomer(Customer customer);
    public String updateCustomer(Customer customer);
    public String deleteCustomer(String custId);
    public Optional<Customer> findById(String custId) throws CustomerNotFoundException;
    public List<Customer> findByCustName(String custName);
    public List<Customer> findByCustCity(String custCity);
    public List<Customer> findByPincode(int pincode);
    public List<Customer> findAllCustomer();
}
