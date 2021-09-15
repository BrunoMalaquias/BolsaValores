package br.com.bruno.bolsaValoresSpring.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bruno.bolsaValoresSpring.model.Ativo;
import br.com.bruno.bolsaValoresSpring.repository.AtivoRepository;
	
@Service
public class AtivoService {
	
	@Autowired
	AtivoRepository ativoRepository;

	public ResponseEntity<List<Ativo>> listarAtivos() {
		List<Ativo> ativosLista = ativoRepository.findAll();
		
		if (ativosLista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Ativo>>(ativosLista, HttpStatus.OK);
		}
	}

	public ResponseEntity<Ativo> listarAtivoPorId(Integer id) {
		 Optional<Ativo> ativo = ativoRepository.findById(id);
			
		 if (ativo.isPresent()) {
				return ResponseEntity.ok(ativo.get());
		 }
		 throw new RuntimeErrorException(null, "Erro ao pesquisar o ativo por ID " + id + ". ");
	}

	public ResponseEntity<Ativo> listarAtivoPorCodigo(String codigo) {
		Optional<Ativo> ativo = ativoRepository.findByCodigo(codigo);
		
		 if (ativo.isPresent()) {
				return ResponseEntity.ok(ativo.get());
		 }
		 throw new RuntimeErrorException(null, "Erro ao pesquisar pelo código do ativo " +  codigo + " ");
	}

}
