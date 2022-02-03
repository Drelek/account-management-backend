package com.revature.accountmanagementbackend.exception;

public class WithdrawalLimitReachedException extends Exception {
  public WithdrawalLimitReachedException(double amountOver) {
    super("Daily withdrawal limit exceeded by $" + amountOver);
  }
}
