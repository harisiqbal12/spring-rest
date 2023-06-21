package com.rest.rest.controllers;

public class StudentNotFoundException extends RuntimeException {

  StudentNotFoundException(String message) {
    super(message);
  }

  StudentNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  StudentNotFoundException(Throwable cause) {
    super(cause);
  }

  

}
