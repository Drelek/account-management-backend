package com.revature.accountmanagementbackend.service;

import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Customer;
import com.revature.accountmanagementbackend.exception.EntityAlreadyExistsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.repository.CustomerRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
  CustomerRepo customerRepo;

  @Autowired
  public CustomerService(CustomerRepo customerRepo) {
    this.customerRepo = customerRepo;
  }

  /**
   * Create a customer
   * 
   * @param customer
   * @return
   * @throws EntityAlreadyExistsException
   */
  public Customer create(Customer customer) throws EntityAlreadyExistsException {
    Customer existingCustomer = null;
    try {
      existingCustomer = read(customer.getPAN());
    } catch (InvalidEntityException e) {

    }
    if (existingCustomer != null)
      throw new EntityAlreadyExistsException("Customer", "PAN");
    return customerRepo.save(customer);
  }

  /**
   * Read a single customer
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  public Customer read(long PAN) throws InvalidEntityException {
    Optional<Customer> optionalCustomer = customerRepo.findById(PAN);
    if (optionalCustomer.isEmpty())
      throw new InvalidEntityException("Customer", PAN);
    return optionalCustomer.get();
  }

  /**
   * Read a specific user's customer
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  public Customer readOfUser(int id) throws InvalidEntityException {
    Optional<Customer> optionalCustomer = customerRepo.findByUser(id);
    if (optionalCustomer.isEmpty())
      throw new InvalidEntityException("Customer", "User.id: " + id);
    return optionalCustomer.get();
  }

  /**
   * Update a customer
   * 
   * @param customer
   * @return
   * @throws InvalidEntityException
   */
  public Customer update(Customer customer) throws InvalidEntityException {
    Optional<Customer> optionalCustomer = customerRepo.findById(customer.getPAN());
    if (optionalCustomer.isEmpty())
      throw new InvalidEntityException("Customer", customer.getPAN());
    return customerRepo.save(customer);
  }

  /**
   * Delete a customer
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  public Customer delete(long PAN) throws InvalidEntityException {
    Optional<Customer> optionalCustomer = customerRepo.findById(PAN);
    if (optionalCustomer.isEmpty())
      throw new InvalidEntityException("Customer", PAN);
    Customer customer = optionalCustomer.get();
    customerRepo.deleteById(PAN);
    return customer;
  }
}
