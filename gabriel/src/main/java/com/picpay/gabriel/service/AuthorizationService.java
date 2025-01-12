package com.picpay.gabriel.service;


import com.picpay.gabriel.model.Enums.Cargo;
import com.picpay.gabriel.model.Entities.Usuario;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@Service
public class AuthorizationService {

  public void userExists(Optional<Usuario> payer, Optional<Usuario> payee) {

    if (!payer.isPresent() || !payee.isPresent()) {
      throw new RuntimeException("Usuário não encontrado.");
    }
  }

  public void userPosition(Optional<Usuario> payer) {
    if(payer.get().getCargo() == Cargo.LOJISTA) {
      throw new RuntimeException("Lojistas não podem realizar transferencias.");
    }
  }

  public void userHasBalance(Optional<Usuario> payer, double valor) {

    if(payer.get().getSaldoCarteira() <= 0 || valor > payer.get().getSaldoCarteira()) {
      throw new RuntimeException("Saldo insuficiente");
    }
  }

  public boolean auth() {
    String host = "https://util.devi.tools/api/v2/authorize";

    HttpClient client = HttpClient.newHttpClient();

    HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(host))
            .build();
    try {
      HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

      int statusCode = response.statusCode();
      System.out.println(statusCode);
      return statusCode == 200 ? true : false;

    } catch(Exception e) {
      System.out.println(e);
      return false;
    }
  }
}
