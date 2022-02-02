package com.revature.accountmanagementbackend.exception;

public class EntityAlreadyExistsException extends Exception {

  public EntityAlreadyExistsException(String entityType, String entityKeyName) {
    super(entityType + " with that " + entityKeyName + " already exists");
  }

}
