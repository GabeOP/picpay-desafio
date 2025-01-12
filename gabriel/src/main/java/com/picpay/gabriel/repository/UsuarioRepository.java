package com.picpay.gabriel.repository;

import com.picpay.gabriel.model.Entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  public Optional<Usuario> findByEmail(String email);
  public Optional<Usuario> findByCpf(String cpf);
}
