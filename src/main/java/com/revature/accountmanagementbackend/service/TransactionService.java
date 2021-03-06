package com.revature.accountmanagementbackend.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.revature.accountmanagementbackend.entity.Account;
import com.revature.accountmanagementbackend.entity.Transaction;
import com.revature.accountmanagementbackend.entity.TransactionType;
import com.revature.accountmanagementbackend.exception.InsufficientFundsException;
import com.revature.accountmanagementbackend.exception.InvalidEntityException;
import com.revature.accountmanagementbackend.exception.WithdrawalLimitReachedException;
import com.revature.accountmanagementbackend.repository.TransactionRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  TransactionRepo transactionRepo;
  AccountService accountService;
  EmailService emailService;

  @Autowired
  public TransactionService(TransactionRepo transactionRepo, AccountService accountService, EmailService emailService) {
    this.transactionRepo = transactionRepo;
    this.accountService = accountService;
    this.emailService = emailService;
  }

  /**
   * Save a transaction
   * 
   * @param transaction
   * @return
   * @throws InvalidEntityException
   */
  public Transaction create(Transaction transaction)
      throws InvalidEntityException, WithdrawalLimitReachedException, InsufficientFundsException {
    // Get initial account info
    Account account = accountService.read(transaction.getAccount().getAccountNumber());

    // Throw exception if insufficient funds
    if (transaction.getType() == TransactionType.WITHDRAWAL && account.getCurrentBalance() < transaction.getAmount())
      throw new InsufficientFundsException();

    // Assemble dates
    long currentTime = new Date().getTime();
    Date start = new Date(currentTime - currentTime % (24 * 60 * 60 * 1000));
    Date end = new Date(start.getTime() + 24 * 60 * 60 * 1000);

    // Get info on today's transactions
    List<Transaction> todayTransactions = transactionRepo.findAllByDateRange(account.getAccountNumber(), start, end);
    double total = transaction.getAmount();
    for (Transaction t : todayTransactions)
      if (t.getType() == TransactionType.WITHDRAWAL)
        total += t.getAmount();

    // Throw exception if we're over cap
    if (transaction.getType() == TransactionType.WITHDRAWAL && total > 10000)
      throw new WithdrawalLimitReachedException(total - 10000);

    // Assemble transaction info and commit
    transaction.setDateTime(new Date());
    if (transaction.getType() == TransactionType.DEPOSIT) {
      transaction.setNewBalance(account.getCurrentBalance() + transaction.getAmount());
      accountService.updateBalance(transaction.getAccount().getAccountNumber(), transaction.getAmount());
    } else {
      transaction.setNewBalance(account.getCurrentBalance() - transaction.getAmount());
      accountService.updateBalance(transaction.getAccount().getAccountNumber(), -transaction.getAmount());
    }
    Transaction savedTransaction = transactionRepo.save(transaction);

    // Send email
    emailService.sendSimpleMessage(account.getOwner().getEmail(), "Transaction complete",
        String.format("A " + transaction.getType().name().toLowerCase()
            + " has been posted to your account ending in %04d for the amount of $%.2f.",
            account.getAccountNumber() % 10000, transaction.getAmount()));

    // Finally return
    return savedTransaction;
  }

  /**
   * Read a transaction by id
   * 
   * @param referenceNumber
   * @return
   * @throws InvalidEntityException
   */
  public Transaction read(long referenceNumber) throws InvalidEntityException {
    Optional<Transaction> optionalTransaction = transactionRepo.findById(referenceNumber);
    if (optionalTransaction.isEmpty())
      throw new InvalidEntityException("Transaction", referenceNumber);
    return optionalTransaction.get();
  }

  /**
   * Read all transactions by account number
   * 
   * @param accountNumber
   * @return
   * @throws InvalidEntityException
   */
  public List<Transaction> readAllOfAccount(long accountNumber) throws InvalidEntityException {
    accountService.read(accountNumber);
    return transactionRepo.findAllByAccount(accountNumber);
  }
}
