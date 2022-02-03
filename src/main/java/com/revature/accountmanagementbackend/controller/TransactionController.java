package com.revature.accountmanagementbackend.controller;

import java.util.List;

import com.revature.accountmanagementbackend.entity.Transaction;
import com.revature.accountmanagementbackend.exception.InsufficientFundsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.exception.WithdrawalLimitReachedException;
import com.revature.accountmanagementbackend.service.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transaction/")
public class TransactionController {
  TransactionService transactionService;

  @Autowired
  public TransactionController(TransactionService transactionService) {
    this.transactionService = transactionService;
  }

  /**
   * Handler for post requests to transaction
   * 
   * @param transaction
   * @return
   * @throws InvalidEntityException
   */
  @PostMapping("/")
  public ResponseEntity<Transaction> create(@RequestBody Transaction transaction)
      throws InvalidEntityException, WithdrawalLimitReachedException, InsufficientFundsException {
    return new ResponseEntity<Transaction>(transactionService.create(transaction), HttpStatus.CREATED);
  }

  /**
   * Handler for get requests to transaction by reference number
   * 
   * @param referenceNumber
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/{referenceNumber}/")
  public ResponseEntity<Transaction> read(@PathVariable long referenceNumber) throws InvalidEntityException {
    return new ResponseEntity<Transaction>(transactionService.read(referenceNumber), HttpStatus.OK);
  }

  /**
   * Handler for get requests to transaction by account number
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  @GetMapping("/account/{accountNumber}/")
  public ResponseEntity<List<Transaction>> readAllOfAccount(@PathVariable long accountNumber)
      throws InvalidEntityException {
    return new ResponseEntity<List<Transaction>>(transactionService.readAllOfAccount(accountNumber), HttpStatus.OK);
  }
}
