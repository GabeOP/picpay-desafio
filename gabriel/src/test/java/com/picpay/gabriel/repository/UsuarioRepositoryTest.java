package com.picpay.gabriel.repository;

import com.picpay.gabriel.model.Entities.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static com.picpay.gabriel.UsuarioCommon.USUARIO;
import static com.picpay.gabriel.UsuarioCommon.USUARIO_INVALIDO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class UsuarioRepositoryTest {

  @Autowired
  private UsuarioRepository repository;

  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  public void createUsuario_withValidData_ReturnsUsuario() {
    Usuario usuario = repository.save(USUARIO);

    Usuario sut = testEntityManager.find(Usuario.class, usuario.getId());

    assertThat(sut).isNotNull();
    assertThat(sut.getCpf()).isEqualTo(USUARIO.getCpf());
  }

  @Test
  public void createUsuario_withInvalidData_ThrowsException() {
    assertThatThrownBy(() -> repository.save(USUARIO_INVALIDO)).isInstanceOf(RuntimeException.class);
  }
}
