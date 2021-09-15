package br.com.bruno.bolsaValoresSpring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.bruno.bolsaValoresSpring.model.Usuario;
import br.com.bruno.bolsaValoresSpring.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;

	public ResponseEntity<Usuario> listarUsuariosPorId(Integer id) {
		 Optional<Usuario> usuario = usuarioRepository.findById(id);
			
		 if (usuario.isPresent()) {
				return ResponseEntity.ok(usuario.get());
		 }else {
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		 }
	}

	public ResponseEntity<List<Usuario>> listarUsuarios() {
		
		List<Usuario> usuariosLista = usuarioRepository.findAll();
		
		if (usuariosLista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<List<Usuario>>(usuariosLista, HttpStatus.OK);
		}
	}

	public ResponseEntity<Usuario> cadastrarUsuario(Usuario usuario) {
		return ResponseEntity.ok(usuarioRepository.save(usuario));
	}

	public HttpStatus deletarUsuario(Integer id) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(id);
		
		if(usuario.isPresent()) {
			usuarioRepository.deleteById(id);
			return  HttpStatus.OK;
		}
		
		return HttpStatus.NOT_FOUND;
	}
}
