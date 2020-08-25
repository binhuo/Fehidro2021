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
import fehidro.model.dto.ctpg.CadastroCtpgDTO;

@RestController
@RequestMapping("/usuario/ctpg")
public class CTPGController {

	@Autowired
	private CTPGRepository _ctpgRepository;

	@GetMapping("/{id}")
	public ResponseEntity<CadastroCtpgDTO> get(@PathVariable(value = "id") Long id) {
		Optional<CTPG> user = _ctpgRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(new CadastroCtpgDTO(user.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CadastroCtpgDTO> add(@RequestBody CadastroCtpgDTO user, UriComponentsBuilder uriBuilder) {
		try {
			CTPG novo = new CTPG(user);
			String senha = Password.generateRandomPassword(10);

			novo.setLogin();
			//usuario.setAtivo();
			novo.setSenha(Password.hashPassword(senha));			
			CTPG cadastrado = _ctpgRepository.save(novo);
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(new CadastroCtpgDTO(cadastrado));
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@PutMapping
	public ResponseEntity<CadastroCtpgDTO> update(@RequestBody CadastroCtpgDTO user) {
		try {
			Optional<CTPG> usuarioBase = _ctpgRepository.findById(user.getId()); 

			if (usuarioBase.isPresent()) {
				CTPG userBase = usuarioBase.get();

				if(user.getSenha() != null && !userBase.getSenha().equals(user.getSenha())) {
					user.setSenha(Password.hashPassword(user.getSenha()));
				}
			} 
			CTPG cadastrado =  _ctpgRepository.save(new CTPG(user));
			return ResponseEntity.ok(new CadastroCtpgDTO(cadastrado));
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
