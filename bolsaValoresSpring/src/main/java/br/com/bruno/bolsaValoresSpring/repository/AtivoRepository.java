package br.com.bruno.bolsaValoresSpring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.bruno.bolsaValoresSpring.model.Ativo;

@Repository
public interface AtivoRepository extends JpaRepository<Ativo, Integer>{

	Optional<Ativo> findByCodigo(String codigo);

}
