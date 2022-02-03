package com.revature.accountmanagementbackend.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class Transaction {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  long referenceNumber;

  @JsonProperty(access = Access.READ_ONLY)
  Date dateTime;
  TransactionType type;
  TransactionMedium medium;
  double amount;

  @JsonProperty(access = Access.READ_ONLY)
  double newBalance;

  @ManyToOne(optional = false)
  @JoinColumn(name = "account_id", nullable = false)
  @JsonIgnoreProperties("transactions")
  Account account;

  public Transaction() {
    super();
  }

  public Transaction(TransactionType type, TransactionMedium medium, double amount) {
    this.type = type;
    this.medium = medium;
    this.amount = amount;
  }

  public Transaction(long referenceNumber, Date dateTime, TransactionType type, TransactionMedium medium, double amount,
      double newBalance) {
    super();
    this.referenceNumber = referenceNumber;
    this.dateTime = dateTime;
    this.type = type;
    this.medium = medium;
    this.amount = amount;
    this.newBalance = newBalance;
  }

  public long getReferenceNumber() {
    return referenceNumber;
  }

  public void setReferenceNumber(long referenceNumber) {
    this.referenceNumber = referenceNumber;
  }

  public Date getDateTime() {
    return dateTime;
  }

  public void setDateTime(Date dateTime) {
    this.dateTime = dateTime;
  }

  public TransactionType getType() {
    return type;
  }

  public void setType(TransactionType type) {
    this.type = type;
  }

  public TransactionMedium getMedium() {
    return medium;
  }

  public void setMedium(TransactionMedium medium) {
    this.medium = medium;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public double getNewBalance() {
    return newBalance;
  }

  public void setNewBalance(double newBalance) {
    this.newBalance = newBalance;
  }

  public Account getAccount() {
    return account;
  }

  public void setAccount(Account account) {
    this.account = account;
  }

  @Override
  public String toString() {
    return "Transaction [account=" + account + ", amount=" + amount + ", dateTime=" + dateTime + ", medium=" + medium
        + ", newBalance=" + newBalance + ", referenceNumber=" + referenceNumber + ", type=" + type + "]";
  }
}
