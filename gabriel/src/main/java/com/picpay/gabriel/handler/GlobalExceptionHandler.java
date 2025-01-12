package com.picpay.gabriel.handler;

import com.picpay.gabriel.exception.InvalidTypeException;
import com.picpay.gabriel.exception.UserNotFoundException;
import com.picpay.gabriel.model.DTO.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ResponseBody
  @ExceptionHandler(UserNotFoundException.class)
  public ExceptionDTO userNotFoundException(UserNotFoundException ex) {
    return new ExceptionDTO("Usuário " + ex.getValue() + " não encontrado.", HttpStatus.NOT_FOUND);
  }

  @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
  @ResponseBody
  @ExceptionHandler(InvalidTypeException.class)
  public ExceptionDTO invalidTypeException(InvalidTypeException ex) {
    return new ExceptionDTO("Tipo " + ex.getKey() + " inválido. Use 'CPF' ou 'e-mail'.", HttpStatus.UNPROCESSABLE_ENTITY);
  }
}
