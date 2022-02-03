package com.revature.accountmanagementbackend.exception;

public class AuthenticationFailedException extends Exception {

  public AuthenticationFailedException() {
    super("Authentication failed");
  }

}
