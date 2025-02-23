package com.picpay.gabriel.model.DTO;

import com.picpay.gabriel.model.Enums.Cargo;
import jakarta.persistence.Column;

import java.math.BigDecimal;

public class UsuarioResponse {
  private Long id;
  @Column(name = "nome")
  private String nomeCompleto;
  private String cpf;
  private String email;
  @Column(name = "saldo")
  private BigDecimal saldoCarteira;
  private Cargo cargo;

  UsuarioResponse() {}

  public UsuarioResponse(Long id, String nomeCompleto, String cpf, String email, Cargo cargo, BigDecimal saldoCarteira) {
    this.id = id;
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.email = email;
    this.cargo = cargo;
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

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }

  public BigDecimal getSaldoCarteira() {
    return saldoCarteira;
  }

  public void setSaldoCarteira(BigDecimal saldoCarteira) {
    this.saldoCarteira = saldoCarteira;
  }
}
