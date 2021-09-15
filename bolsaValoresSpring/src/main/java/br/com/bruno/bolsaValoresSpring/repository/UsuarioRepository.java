package br.com.bruno.bolsaValoresSpring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.bruno.bolsaValoresSpring.model.Usuario;

public interface UsuarioRepository   extends JpaRepository<Usuario, Integer>{

}
