package com.revature.accountmanagementbackend.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  long accountNumber;
  double currentBalance;

  @ManyToOne(optional = false)
  @JoinColumn(name = "customer_id", nullable = false)
  @JsonIgnoreProperties("accounts")
  Customer owner;

  @OneToMany(mappedBy = "account")
  @JsonIgnore
  List<Transaction> transactions;

  public Account() {
    super();
  }

  public Account(long accountNumber, double currentBalance) {
    super();
    this.accountNumber = accountNumber;
    this.currentBalance = currentBalance;
  }

  public long getAccountNumber() {
    return accountNumber;
  }

  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  public double getCurrentBalance() {
    return currentBalance;
  }

  public void setCurrentBalance(double currentBalance) {
    this.currentBalance = currentBalance;
  }

  public Customer getOwner() {
    return owner;
  }

  public void setOwner(Customer owner) {
    this.owner = owner;
  }

  public List<Transaction> getTransactions() {
    return transactions;
  }

  public void setTransactions(List<Transaction> transactions) {
    this.transactions = transactions;
  }

  @Override
  public String toString() {
    return "Account [acn=" + accountNumber + ", balance=" + currentBalance + ", ownerPAN=" + owner.PAN + "]";
  }
}
