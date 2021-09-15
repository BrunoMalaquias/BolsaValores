package br.com.bruno.bolsaValoresSpring.controller;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.bolsaValoresSpring.form.OperacaoForm;
import br.com.bruno.bolsaValoresSpring.model.Operacao;
import br.com.bruno.bolsaValoresSpring.model.dto.OperacaoDto;
import br.com.bruno.bolsaValoresSpring.service.OperacaoService;
import io.swagger.annotations.Api;

@RestController
@RequestMapping(value = "/operacao")
@Api(value="API REST das Operações")
@CrossOrigin(origins = "*")
public class OperacaoController {
	
	@Autowired
	OperacaoService operacaoService;
	
	@PostMapping(value = "/comprar")
	@Transactional
	public ResponseEntity<Operacao> comprar(@RequestBody OperacaoForm form){
		return operacaoService.comprar(form);
	}
	
	@PostMapping(value = "/vender")
	@Transactional
	public ResponseEntity<Operacao> vender(@RequestBody OperacaoForm form){
		return operacaoService.vender(form);
	}
		
	@GetMapping(value = "/listarPorCodigo")
	public ResponseEntity<List<OperacaoDto>> listarOperacoesPorCodigo() {
		return operacaoService.listarOperacoesPorCodigo();
	}
	
	@GetMapping(value = "/listarTodas")
	public List<Operacao> listarTodas() {
		return operacaoService.listarTodas();
	}
	
//	@GetMapping(value = "/listarOperacoes")
//	public ResponseEntity<List<OperacaoDto>> listarOperacoes() {
//		return operacaoService.listarOperacoes();
//	}
//	
}
