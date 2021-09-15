package br.com.bruno.bolsaValoresSpring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.bruno.bolsaValoresSpring.model.Usuario;
import br.com.bruno.bolsaValoresSpring.repository.UsuarioRepository;
import br.com.bruno.bolsaValoresSpring.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	UsuarioService usuarioService;
	
	@GetMapping()
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		return  usuarioService.listarUsuarios();
	}
		
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> listarUsuariosPorId(@PathVariable Integer id) {
		return usuarioService.listarUsuariosPorId(id);
	}

	@PostMapping
	public ResponseEntity<Usuario> cadastrarUsuario(@RequestBody Usuario usuario){
		return usuarioService.cadastrarUsuario(usuario);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Usuario> deletarUsuario(@PathVariable Integer id){
		return	new ResponseEntity<Usuario>(usuarioService.deletarUsuario(id));
	}

}
