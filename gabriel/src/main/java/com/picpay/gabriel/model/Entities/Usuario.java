package com.picpay.gabriel.model.Entities;

import com.picpay.gabriel.model.Enums.Cargo;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NotBlank
  private String nomeCompleto;

  @Column(nullable = false, unique = true)
  @NotBlank
  private String cpf;

  @Column(nullable = false, unique = true)
  @NotBlank
  private String email;

  @Column(nullable = false)
  @NotBlank
  private String senha;

  private double saldoCarteira;

  private Cargo cargo = Cargo.COMUM;

  public Usuario() {}

  public Usuario(String nomeCompleto, String cpf, String email, String senha, Cargo cargo) {
    this.nomeCompleto = nomeCompleto;
    this.cpf = cpf;
    this.email = email;
    this.senha = senha;
    this.cargo = cargo;
    this.saldoCarteira = 0.0;
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

  public void setNomeCompleto(String nome_completo) {
    this.nomeCompleto = nome_completo;
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

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public double getSaldoCarteira() {
    return saldoCarteira;
  }

  public void setSaldoCarteira(double saldoCarteira) {
    this.saldoCarteira = saldoCarteira;
  }

  public Cargo getCargo() {
    return cargo;
  }

  public void setCargo(Cargo cargo) {
    this.cargo = cargo;
  }
}
