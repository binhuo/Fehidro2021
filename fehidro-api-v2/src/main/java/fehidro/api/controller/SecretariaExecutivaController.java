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
import fehidro.api.model.Usuario;
import fehidro.api.repository.SecretariaExecutivaRepository;
import fehidro.api.util.password.Password;
import fehidro.model.dto.secretariaExecutiva.CadastroSecretariaExecutivaDTO;

@RestController
@RequestMapping("/usuario/secretaria")
public class SecretariaExecutivaController {

	@Autowired
	private SecretariaExecutivaRepository _secretariaExecRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<CadastroSecretariaExecutivaDTO> get(@PathVariable(value = "id") Long id) {
		Optional<SecretariaExecutiva> user = _secretariaExecRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(new CadastroSecretariaExecutivaDTO(user.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CadastroSecretariaExecutivaDTO> add(@RequestBody CadastroSecretariaExecutivaDTO user, UriComponentsBuilder uriBuilder) {
		try {
			SecretariaExecutiva novo = new SecretariaExecutiva(user);
			String senha = Password.generateRandomPassword(10);

			novo.setLogin();
			//novo.setAtivo();
			novo.setSenha(Password.hashPassword(senha));
			SecretariaExecutiva usuario = _secretariaExecRepository.save(novo);
			CadastroSecretariaExecutivaDTO cadastrado = new CadastroSecretariaExecutivaDTO(usuario);
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<CadastroSecretariaExecutivaDTO> update(@RequestBody CadastroSecretariaExecutivaDTO user) {
		try {
			Optional<SecretariaExecutiva> usuarioBase = _secretariaExecRepository.findById(user.getId()); 

			if (usuarioBase.isPresent()) {
				SecretariaExecutiva userBase = usuarioBase.get();

				if(user.getSenha() != null && !userBase.getSenha().equals(user.getSenha())) {
					user.setSenha(Password.hashPassword(user.getSenha()));
				}
			} 
			SecretariaExecutiva cadastrado =  _secretariaExecRepository.save(new SecretariaExecutiva(user));
			CadastroSecretariaExecutivaDTO alterado = new CadastroSecretariaExecutivaDTO(cadastrado);
			return ResponseEntity.ok(alterado);
			
		} catch(Exception e) {
			e.printStackTrace();
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
