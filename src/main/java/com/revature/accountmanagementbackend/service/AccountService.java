package com.revature.accountmanagementbackend.service;

import java.util.List;
import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Account;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.repository.AccountRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {
  AccountRepo accountRepo;
  CustomerService customerService;

  @Autowired
  public AccountService(AccountRepo accountRepo, CustomerService customerService) {
    this.accountRepo = accountRepo;
    this.customerService = customerService;
  }

  /**
   * Create an account
   * 
   * @param account
   * @return
   */
  public Account create(Account account) {
    return accountRepo.save(account);
  }

  /**
   * Read an account by account number
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  public Account read(long accountNumber) throws InvalidEntityException {
    Optional<Account> optionalAccount = accountRepo.findById(accountNumber);
    if (optionalAccount.isEmpty())
      throw new InvalidEntityException("Account", accountNumber);
    return optionalAccount.get();
  }

  /**
   * Read all accounts by user PAN
   * 
   * @param PAN
   * @return
   * @throws InvalidEntityException
   */
  public List<Account> readAllOfCustomer(long PAN) throws InvalidEntityException {
    customerService.read(PAN);
    return accountRepo.findAllByCustomer(PAN);
  }

  /**
   * Update the balance of an account
   * 
   * @param accountNumber
   * @param amount
   * @return
   * @throws InvalidEntityException
   */
  @Transactional
  public Account updateBalance(long accountNumber, double amount) throws InvalidEntityException {
    read(accountNumber);
    accountRepo.updateBalanceById(accountNumber, amount);
    return read(accountNumber);
  }

  /**
   * Delete an account
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  public Account delete(long accountNumber) throws InvalidEntityException {
    Optional<Account> optionalAccount = accountRepo.findById(accountNumber);
    if (optionalAccount.isEmpty())
      throw new InvalidEntityException("Account", accountNumber);
    Account account = optionalAccount.get();
    accountRepo.deleteById(accountNumber);
    return account;
  }
}
