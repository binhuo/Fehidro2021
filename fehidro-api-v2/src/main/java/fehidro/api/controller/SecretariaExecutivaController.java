package fehidro.api.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.SecretariaExecutiva;
import fehidro.api.repository.SecretariaExecutivaRepository;
import fehidro.api.util.password.Password;

@RestController
@RequestMapping("/usuario/secretaria")
public class SecretariaExecutivaController {

	@Autowired
	private SecretariaExecutivaRepository _secretariaExecRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<SecretariaExecutiva> get(@PathVariable(value = "id") Long id) {
		Optional<SecretariaExecutiva> user = _secretariaExecRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<SecretariaExecutiva> add(@RequestBody SecretariaExecutiva user, UriComponentsBuilder uriBuilder) {
		try {
			user.setLogin();
			//usuario.setAtivo();
			String senha = Password.generateRandomPassword(10);
			user.setSenha(Password.hashPassword(senha));
			
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);

			SecretariaExecutiva cadastrado = _secretariaExecRepository.save(user);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<SecretariaExecutiva> update(@RequestBody SecretariaExecutiva user) {
		try {
			Optional<SecretariaExecutiva> usuarioBase = _secretariaExecRepository.findById(user.getId()); 

			if (usuarioBase.isPresent()) {
				SecretariaExecutiva userBase = usuarioBase.get();

				if(user.getSenha() != null && !userBase.getSenha().equals(user.getSenha())) {
					user.setSenha(Password.hashPassword(user.getSenha()));
				}
			} 
			SecretariaExecutiva cadastrado =  _secretariaExecRepository.save(user);
			return ResponseEntity.ok(cadastrado);
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<SecretariaExecutiva> criterio = _secretariaExecRepository.findById(id);
		if (criterio.isPresent()) {
			_secretariaExecRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
