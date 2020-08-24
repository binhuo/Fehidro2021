package fehidro.api.controller;

import java.net.URI;
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

import fehidro.api.model.CTPG;
import fehidro.api.repository.CTPGRepository;
import fehidro.api.util.password.Password;

@RestController
@RequestMapping("/usuario/ctpg")
public class CTPGController {

	@Autowired
	private CTPGRepository _ctpgRepository;

	@GetMapping("/{id}")
	public ResponseEntity<CTPG> get(@PathVariable(value = "id") Long id) {
		Optional<CTPG> user = _ctpgRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(user.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CTPG> add(@RequestBody CTPG user, UriComponentsBuilder uriBuilder) {
		try {
			user.setLogin();
			//usuario.setAtivo();
			String senha = Password.generateRandomPassword(10);
			user.setSenha(Password.hashPassword(senha));
			
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);

			CTPG cadastrado = _ctpgRepository.save(user);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<CTPG> update(@RequestBody CTPG user) {
		try {
			Optional<CTPG> usuarioBase = _ctpgRepository.findById(user.getId()); 

			if (usuarioBase.isPresent()) {
				CTPG userBase = usuarioBase.get();

				if(user.getSenha() != null && !userBase.getSenha().equals(user.getSenha())) {
					user.setSenha(Password.hashPassword(user.getSenha()));
				}
			} 
			CTPG cadastrado =  _ctpgRepository.save(user);
			return ResponseEntity.ok(cadastrado);
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
