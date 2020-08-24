package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.Usuario;
import fehidro.api.repository.UsuarioRepository;
import fehidro.api.util.password.Password;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository _usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<Usuario>> getAll() {		
		return ResponseEntity.ok(_usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Usuario> get(@PathVariable(value = "id") Long id) {
		Optional<Usuario> user = _usuarioRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/obterPorLogin/{login}")
	public ResponseEntity<Usuario> obterPorLogin(@PathVariable(value = "login") String login) {
		Optional<Usuario> user = _usuarioRepository.findByLogin(login);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/obterPorCPF/{cpf}")
	public ResponseEntity<Usuario> obterPorCPF(@PathVariable(value = "cpf") String cpf) {
		Optional<Usuario> user = _usuarioRepository.findByCPF(cpf);
		if (user.isPresent()) {
			return ResponseEntity.ok(user.get());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/obterPorPerfilAcesso/{perfilacesso}")
	public ResponseEntity<List<Usuario>> obterPorPerfilAcesso(@PathVariable(value = "perfilacesso") Long perfilacesso) {
		return ResponseEntity.ok(_usuarioRepository.findAllByPerfilAcesso(perfilacesso));
	}
	
	@PostMapping
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario, UriComponentsBuilder uriBuilder) {
		
		try {
			usuario.setLogin();
			usuario.setAtivo();	
			String senha = Password.generateRandomPassword(10);
			usuario.setSenha(Password.hashPassword(senha));
			Usuario user = _usuarioRepository.save(usuario);
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(user.getId()).toUri();
			return ResponseEntity.created(uri).body(user);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@PutMapping
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		Usuario user = _usuarioRepository.save(usuario);	
		return ResponseEntity.ok(user);
	}
	
}
