package com.picpay.gabriel.controller;

import com.picpay.gabriel.model.DTO.TransferenciaDTO;
import com.picpay.gabriel.model.DTO.UsuarioResponse;
import com.picpay.gabriel.service.UsuarioService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

  @Autowired
  private UsuarioService service;

  private ModelMapper modelMapper;

  public UsuarioController(UsuarioService service, ModelMapper modelMapper) {
    this.service = service;
    this.modelMapper = modelMapper;
  }

  @GetMapping("/usuario")
  public ResponseEntity<UsuarioResponse> getUsuario(
          @RequestParam String key, @RequestParam String value) {
    UsuarioResponse response = modelMapper.map(service.getUsuario(key, value), UsuarioResponse.class);
    return ResponseEntity.ok().body(response);
  }

  @PostMapping("/transferencia")
  public String realizarTransferencia(@RequestBody TransferenciaDTO dto) {
    String payer     = dto.getPayer();
    String payee     = dto.getPayee();

    try {
      service.transferencia(dto);
      return "Transferência de [" + payer + "] para [" + payee + "] realizada com sucesso.";
    } catch (Exception e) {
      return e.getMessage();
    }
  }
}
