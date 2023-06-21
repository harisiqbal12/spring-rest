package com.rest.rest.controllers;

public class StudentDisableException extends RuntimeException {

  public StudentDisableException(String message) {
    super(message);
  }

  public StudentDisableException(String message, Throwable cause) {
    super(message, cause);
  }

  public StudentDisableException(Throwable cause) {
    super(cause);
  }

}
