package com.picpay.gabriel.model.DTO;

import org.springframework.http.HttpStatus;

public class ExceptionDTO {
  private String msg;
  private int codeError;

  public ExceptionDTO(){}

  public ExceptionDTO(String msg, HttpStatus codeError) {
    this.msg = msg;
    this.codeError = codeError.value();
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public int getCodeError() {
    return codeError;
  }

  public void setCodeError(int codeError) {
    this.codeError = codeError;
  }
}
