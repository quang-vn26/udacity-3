package com.udacity.jdnd.course3.critter.services;

import com.udacity.jdnd.course3.critter.model.Customer;

import java.util.List;

public interface CustomerService {

    Customer saveCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Long id);
}
