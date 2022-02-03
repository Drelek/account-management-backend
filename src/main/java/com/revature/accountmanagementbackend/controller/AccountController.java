package com.revature.accountmanagementbackend.controller;

import java.util.List;

import com.revature.accountmanagementbackend.entity.Account;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account/")
public class AccountController {
  AccountService accountService;

  @Autowired
  public AccountController(AccountService accountService) {
    this.accountService = accountService;
  }

  /**
   * Handler for post requests to account
   * 
   * @param account
   * @return
   */
  @PostMapping("/")
  public ResponseEntity<Account> create(@RequestBody Account account) {
    return new ResponseEntity<Account>(accountService.create(account), HttpStatus.CREATED);
  }

  /**
   * Handler for get requests to account by account number
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/{accountNumber}/")
  public ResponseEntity<Account> read(@PathVariable long accountNumber) throws InvalidEntityException {
    return new ResponseEntity<Account>(accountService.read(accountNumber), HttpStatus.OK);
  }

  /**
   * Handler for get requests to account by user PAN
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/customer/{PAN}")
  public ResponseEntity<List<Account>> readAllOfUser(@PathVariable long PAN) throws InvalidEntityException {
    return new ResponseEntity<List<Account>>(accountService.readAllOfCustomer(PAN), HttpStatus.OK);
  }

  /**
   * Handler for delete requests to account
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  @DeleteMapping("/{accountNumber}/")
  public ResponseEntity<Account> delete(@PathVariable long accountNumber) throws InvalidEntityException {
    return new ResponseEntity<Account>(accountService.delete(accountNumber), HttpStatus.OK);
  }
}
