package com.onlineshoppingrestapi.customer.repository;

import com.onlineshoppingrestapi.customer.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,String> {
    public List<Customer> findByCustName(String custName);
    public List<Customer> findByCustCity(String custCity);
    public List<Customer> findByPincode(int pincode);
}
