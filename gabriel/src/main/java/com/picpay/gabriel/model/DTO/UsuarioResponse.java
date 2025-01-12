package com.picpay.gabriel.model.DTO;

import jakarta.persistence.Column;

public class UsuarioResponse {
  private Long id;
  @Column(name = "nome")
  private String nomeCompleto;
  private String cpf;
  private String email;
  @Column(name = "saldo")
  private double saldoCarteira;

  UsuarioResponse() {}

  public UsuarioResponse(Long id, String nomeCompleto, String cpf, String email, double saldoCarteira) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.email = email;
    this.saldoCarteira = saldoCarteira;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public void setNomeCompleto(String nomeCompleto) {
    this.nomeCompleto = nomeCompleto;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public double getSaldoCarteira() {
    return saldoCarteira;
  }

  public void setSaldoCarteira(double saldoCarteira) {
    this.saldoCarteira = saldoCarteira;
  }
}
