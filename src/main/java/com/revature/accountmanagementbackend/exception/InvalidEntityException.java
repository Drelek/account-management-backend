package com.revature.accountmanagementbackend.exception;

/**
 * Generic exception for any entity that could not be found at any endpoint
 */
public class InvalidEntityException extends Exception {
  String entityType;

  public InvalidEntityException(String entityType, long id) {
    this(entityType, "" + id);
  }

  public InvalidEntityException(String entityType, int id) {
    this(entityType, "" + id);
  }

  public InvalidEntityException(String entityType, String id) {
    super(entityType + " with id " + id + " not found");
    this.entityType = entityType;
  }

  public InvalidEntityException(Throwable cause, String entityType) {
    super(cause);
    this.entityType = entityType;
  }

  public InvalidEntityException(String message, Throwable cause, String entityType) {
    super(message, cause);
    this.entityType = entityType;
  }

  public InvalidEntityException() {
    super();
  }
}
