package com.picpay.gabriel.service;


import com.picpay.gabriel.model.Enums.Cargo;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class AuthorizationService {

  public void userPosition(Cargo cargo) {
    if(cargo == Cargo.LOJISTA) {
      throw new RuntimeException("Lojistas não podem realizar transferencias.");
    }
  }

  public void userHasBalance(BigDecimal saldoCarteira, BigDecimal valorTransferencia) {

    if(saldoCarteira.compareTo(BigDecimal.ZERO) <= 0 || saldoCarteira.compareTo(valorTransferencia) < 0) {
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
      return statusCode == 200;

    } catch(Exception e) {
      System.out.println(e);
      return false;
    }
  }
}
