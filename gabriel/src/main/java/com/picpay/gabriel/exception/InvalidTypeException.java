package com.picpay.gabriel.exception;

public class InvalidTypeException extends RuntimeException{

  private String key;

  public InvalidTypeException(String key) {
    this.key = key;
  }

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
  }
}
