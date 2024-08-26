package com.onlineshoppingrestapi.customer.service;

import com.onlineshoppingrestapi.customer.dto.Customer;
import com.onlineshoppingrestapi.customer.repository.ICustomerRepository;
import com.onlineshoppingrestapi.exception.dto.CustomerNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements ICustomerService{
    @Autowired
    private ICustomerRepository customerRepository;
    @Override
    public String addCustomer(Customer customer) {
        customerRepository.save(customer);
        return "Customer Added Sucessfully ...";
    }

    @Override
    public String updateCustomer(Customer customer) {
        customerRepository.save(customer);
        return "Customer Updated Sucessfully ...";
    }

    @Override
    public String deleteCustomer(String custId) {
        customerRepository.deleteById(custId);
        return "Customer Deleted Sucessfully ...";
    }

    @Override
    public Optional<Customer> findById(String custId) throws CustomerNotFoundException {
        Optional<Customer> customer = customerRepository.findById(custId);
        if(customer.isEmpty()){
            throw new CustomerNotFoundException(custId);
        }
        return customer;
    }

    @Override
    public List<Customer> findByCustName(String custName) {
        return customerRepository.findByCustName(custName);
    }

    @Override
    public List<Customer> findByCustCity(String custCity) {
        return customerRepository.findByCustCity(custCity);
    }

    @Override
    public List<Customer> findByPincode(int pincode) {
        return customerRepository.findByPincode(pincode);
    }

    @Override
    public List<Customer> findAllCustomer() {
        return customerRepository.findAll();
    }
}
