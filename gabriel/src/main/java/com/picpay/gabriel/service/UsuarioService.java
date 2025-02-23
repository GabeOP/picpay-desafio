package com.picpay.gabriel.service;

import com.picpay.gabriel.exception.InvalidTypeException;
import com.picpay.gabriel.exception.UserNotFoundException;
import com.picpay.gabriel.model.DTO.TransferenciaDTO;
import com.picpay.gabriel.model.Entities.Usuario;
import com.picpay.gabriel.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

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

  public Usuario getUsuario(String key, String value) {
    return switch (key.toLowerCase()) {
      case "email" -> repository.findByEmail(value)
              .orElseThrow(() -> new UserNotFoundException(value));
      case "cpf" -> repository.findByCpf(value)
              .orElseThrow(() -> new UserNotFoundException(value));
      default -> throw new InvalidTypeException(key);
    };
  }

  @Transactional
  public void transferencia(TransferenciaDTO transferenciaDTO) {

    Usuario payer = getUsuario(transferenciaDTO.getTipoPayer(), transferenciaDTO.getPayer());
    Usuario payee = getUsuario(transferenciaDTO.getTipoPayee(), transferenciaDTO.getPayee());

    authorizationService.userPosition(payer.getCargo());
    authorizationService.userHasBalance(payer.getSaldoCarteira(), transferenciaDTO.getValor());

    boolean auth = authorizationService.auth();
    if(!auth) throw new RuntimeException("Não autorizado.");

    BigDecimal saldoPayer = payer.getSaldoCarteira();
    BigDecimal saldoPayee = payee.getSaldoCarteira();

    payer.setSaldoCarteira(saldoPayer.subtract(transferenciaDTO.getValor()));
    payee.setSaldoCarteira(saldoPayee.add(transferenciaDTO.getValor()));

    repository.save(modelMapper.map(payer, Usuario.class));
    repository.save(modelMapper.map(payee, Usuario.class));
  }
}
