package com.picpay.gabriel.model.DTO;

public class TransferenciaDTO {
  private String tipoPayee;
  private String payee;
  private String tipoPayer;
  private String payer;
  private double valor; 

  public TransferenciaDTO() {
  }

  public TransferenciaDTO(String tipoPayee, String payee, String tipoPayer, String payer, double valor) {
    this.tipoPayee = tipoPayee;
    this.payee = payee;
    this.tipoPayer = tipoPayer;
    this.payer = payer;
    this.valor = valor;
  }

  public String getTipoPayee() {
    return tipoPayee;
  }

  public void setTipoPayee(String tipoPayee) {
    this.tipoPayee = tipoPayee;
  }

  public String getPayee() {
    return payee;
  }

  public void setPayee(String payee) {
    this.payee = payee;
  }

  public String getTipoPayer() {
    return tipoPayer;
  }

  public void setTipoPayer(String tipoPayer) {
    this.tipoPayer = tipoPayer;
  }

  public String getPayer() {
    return payer;
  }

  public void setPayer(String payer) {
    this.payer = payer;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }
}

