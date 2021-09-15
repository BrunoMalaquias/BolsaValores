package br.com.bruno.bolsaValoresSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.bolsaValoresSpring.model.Ativo;
import br.com.bruno.bolsaValoresSpring.service.AtivoService;

@RestController
@RequestMapping(value="ativos")
public class AtivoController {
	
	@Autowired
	AtivoService ativoService;
	
	@GetMapping
	public ResponseEntity<List<Ativo>> listarAtivos() {
		return ativoService.listarAtivos();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ativo> listarAtivoPorId(@PathVariable Integer id) {
		return ativoService.listarAtivoPorId(id);
	}
	
	@GetMapping("codigo/{codigo}")
	public ResponseEntity<Ativo> listarAtivoPorCodigo(@PathVariable String codigo) {
		return ativoService.listarAtivoPorCodigo(codigo);
	}
}