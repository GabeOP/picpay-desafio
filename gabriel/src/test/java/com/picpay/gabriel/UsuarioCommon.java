package com.picpay.gabriel;

import com.picpay.gabriel.model.Entities.Usuario;
import com.picpay.gabriel.model.Enums.Cargo;

public class UsuarioCommon {

  public static final Usuario USUARIO = new Usuario(
          "Usuario de Teste",
          "11122233345",
          "usuteste@hotmail.com",
          "123",
          Cargo.LOJISTA);

  public static final Usuario USUARIO_INVALIDO = new Usuario("", "", "", "", Cargo.COMUM);
}
