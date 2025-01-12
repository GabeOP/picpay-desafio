package com.picpay.gabriel.controller;

import com.picpay.gabriel.model.DTO.TransferenciaDTO;
import com.picpay.gabriel.model.DTO.UsuarioResponse;
import com.picpay.gabriel.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  public UsuarioController() {}

  public UsuarioController(UsuarioService service) {
    this.service = service;
  }

  @GetMapping("/usuario")
  public ResponseEntity<UsuarioResponse> getUsuario(
          @RequestParam String key, @RequestParam String value) {
    return ResponseEntity.ok().body(service.getUsuario(key, value));
  }

  @PostMapping("/transferencia")
  public String realizarTransferencia(@RequestBody TransferenciaDTO dto) {
    String tipoPayer = dto.getTipoPayer();
    String payer     = dto.getPayer();

    String tipoPayee = dto.getTipoPayee();
    String payee     = dto.getPayee();

    double valor     = dto.getValor();

    try {
      service.transferencia(tipoPayer, payer, tipoPayee, payee, valor);
      return "Transferência de [" + payer + "] para [" + payee + "] realizada com sucesso.";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
