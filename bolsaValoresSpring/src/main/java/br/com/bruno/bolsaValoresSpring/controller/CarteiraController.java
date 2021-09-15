package br.com.bruno.bolsaValoresSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.bolsaValoresSpring.model.dto.CarteiraDto;
import br.com.bruno.bolsaValoresSpring.service.CarteirasService;

@RestController
@RequestMapping(value = "/carteira")
public class CarteiraController {
	
	@Autowired
	CarteirasService carteiraService;
	
	@GetMapping
	public ResponseEntity<List<CarteiraDto>> listarCarteira() {
		return carteiraService.listarCarteira();
	}

}
