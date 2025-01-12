package com.picpay.gabriel.exception;

public class UserNotFoundException extends RuntimeException {
  public String value;

  public UserNotFoundException(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
