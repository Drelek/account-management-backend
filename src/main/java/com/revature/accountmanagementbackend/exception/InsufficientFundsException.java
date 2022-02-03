package com.revature.accountmanagementbackend.exception;

public class InsufficientFundsException extends Exception {
  public InsufficientFundsException() {
    super("You have insufficient funds for this operation");
  }
}
