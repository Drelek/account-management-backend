package com.revature.accountmanagementbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

  /**
   * Handle all InvalidEntityExceptions with a standard response
   * 
   * @param invalidEntityException
   * @return
   */
  @ExceptionHandler(value = InvalidEntityException.class)
  public ResponseEntity<ErrorResponse> invalidEntityHandler(InvalidEntityException invalidEntityException) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse(HttpStatus.NOT_FOUND, invalidEntityException), HttpStatus.NOT_FOUND);
  }

  /**
   * Handle all EntityAlreadyExistsExceptions with a standard response
   * 
   * @param exception
   * @return
   */
  @ExceptionHandler(value = EntityAlreadyExistsException.class)
  public ResponseEntity<ErrorResponse> alreadyExistsHandler(EntityAlreadyExistsException exception) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle the AuthenticationFailedException with a standard response
   * 
   * @param exception
   * @return
   */
  @ExceptionHandler(value = AuthenticationFailedException.class)
  public ResponseEntity<ErrorResponse> authFailedHandler(AuthenticationFailedException exception) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle the WithdrawalLimitReachedException with a standard response
   * 
   * @param exception
   * @return
   */
  @ExceptionHandler(value = WithdrawalLimitReachedException.class)
  public ResponseEntity<ErrorResponse> withdrawalFailedHandler(WithdrawalLimitReachedException exception) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle the InsufficientFundsException with a standard response
   * 
   * @param exception
   * @return
   */
  @ExceptionHandler(value = InsufficientFundsException.class)
  public ResponseEntity<ErrorResponse> insufficientFundsHandler(InsufficientFundsException exception) {
    return new ResponseEntity<ErrorResponse>(new ErrorResponse(HttpStatus.BAD_REQUEST, exception),
        HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle all exceptions not already handled with a standard response
   * 
   * @param exception
   * @return
   */
  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<ErrorResponse> genericExceptionHandler(Exception exception) {
    return new ResponseEntity<ErrorResponse>(
        new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, exception), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
