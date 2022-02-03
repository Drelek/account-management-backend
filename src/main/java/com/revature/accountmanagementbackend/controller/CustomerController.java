package com.revature.accountmanagementbackend.controller;

import com.revature.accountmanagementbackend.entity.Customer;
import com.revature.accountmanagementbackend.exception.EntityAlreadyExistsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer/")
public class CustomerController {
  CustomerService customerService;

  @Autowired
  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  /**
   * Handler for post request to customer
   * 
   * @param customer
   * @return
   * @throws EntityAlreadyExistsException
   */
  @PostMapping("/")
  public ResponseEntity<Customer> create(@RequestBody Customer customer) throws EntityAlreadyExistsException {
    return new ResponseEntity<Customer>(customerService.create(customer), HttpStatus.OK);
  }

  /**
   * Handler for get request to customer by PAN
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/{PAN}/")
  public ResponseEntity<Customer> read(@PathVariable long PAN) throws InvalidEntityException {
    return new ResponseEntity<Customer>(customerService.read(PAN), HttpStatus.OK);
  }

  /**
   * Handler for get request to customer by user
   * 
   * @param id
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/user/{id}/")
  public ResponseEntity<Customer> readOfUser(@PathVariable int id) throws InvalidEntityException {
    return new ResponseEntity<Customer>(customerService.readOfUser(id), HttpStatus.OK);
  }

  /**
   * Handler for put request to customer
   * 
   * @param customer
   * @return
   * @throws InvalidEntityException
   */
  @PutMapping("/")
  public ResponseEntity<Customer> update(@RequestBody Customer customer) throws InvalidEntityException {
    return new ResponseEntity<Customer>(customerService.update(customer), HttpStatus.OK);
  }

  /**
   * Handler for delete request to customer
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  @DeleteMapping("/{PAN}")
  public ResponseEntity<Customer> delete(@PathVariable long PAN) throws InvalidEntityException {
    return new ResponseEntity<Customer>(customerService.delete(PAN), HttpStatus.OK);
  }
}
