package com.picpay.gabriel.service;

import com.picpay.gabriel.exception.InvalidTypeException;
import com.picpay.gabriel.exception.UserNotFoundException;
import com.picpay.gabriel.model.DTO.UsuarioResponse;
import com.picpay.gabriel.model.Entities.Usuario;
import com.picpay.gabriel.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private ModelMapper modelMapper;

  @Autowired
  private AuthorizationService authorizationService;

  public UsuarioService() {}

  public UsuarioService(UsuarioRepository repository, AuthorizationService authorizationService, ModelMapper modelMapper) {
    this.repository = repository;
    this.authorizationService = authorizationService;
    this.modelMapper = modelMapper;
  }

  public UsuarioResponse getUsuario(String key, String value) {
    switch(key.toLowerCase()) {
      case "email":
        Usuario usuario1 = repository.findByEmail(value)
                .orElseThrow(() -> new UserNotFoundException(value));
        return modelMapper.map(usuario1, UsuarioResponse.class);

      case "cpf":
        Usuario usuario2 = repository.findByCpf(value)
                .orElseThrow(() -> new UserNotFoundException(value));
        return modelMapper.map(usuario2, UsuarioResponse.class);

      default:
        throw new InvalidTypeException(key);
    }
  }

  public void transferencia(String tipoPayer, String keyPayer, String tipoPayee, String keyPayee, double valor) {
    Optional<Usuario> payer = null;
    Optional<Usuario> payee = null;

    if(tipoPayer.equals("email")) {
      payer = repository.findByEmail(keyPayer);
    } else if(tipoPayer.equals("cpf")) {
      payer = repository.findByCpf(keyPayer);
    } else {
      throw new InvalidTypeException(null);
    }

    if(tipoPayee.equals("email")) {
      payee = repository.findByEmail(keyPayee);
    } else if(tipoPayee.equals("cpf")) {
      payee = repository.findByCpf(keyPayee);
    } else {
      throw new InvalidTypeException(null);
    }

    authorizationService.userExists(payer, payee);
    authorizationService.userPosition(payer);
    authorizationService.userHasBalance(payer, valor);
    boolean auth =authorizationService.auth();
    if(!auth) throw new RuntimeException("Não autorizado.");

    double saldoPayer = payer.get().getSaldoCarteira();
    double saldoPayee = payee.get().getSaldoCarteira();

    payer.get().setSaldoCarteira(saldoPayer - valor);
    payee.get().setSaldoCarteira(saldoPayee + valor);

    repository.save(payer.get());
    repository.save(payee.get());
  }
}
