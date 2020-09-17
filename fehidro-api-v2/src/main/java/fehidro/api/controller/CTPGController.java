package fehidro.api.controller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
import fehidro.api.util.email.EmailService;
import fehidro.api.util.password.Password;
import fehidro.model.dto.ctpg.CadastroCtpgDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario/ctpg")
public class CTPGController {

	@Autowired
	private CTPGRepository _ctpgRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired 
	private EmailService _emailService;

	@ApiOperation(value = "Retorna um usuário CT-PG encontrado por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<CadastroCtpgDTO> get(@PathVariable(value = "id") Long id) {
		Optional<CTPG> user = _ctpgRepository.findById(id);
		if(user.isPresent()) {
			return ResponseEntity.ok(new CadastroCtpgDTO(user.get()));
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra um novo usuário CT-PG")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<CadastroCtpgDTO> add(@RequestBody CadastroCtpgDTO user, UriComponentsBuilder uriBuilder) {
		try {
			CTPG novo = new CTPG(user);
			String senha = Password.generateRandomPassword(10);

			novo.setLogin();
			novo.setSenha(passwordEncoder.encode(senha));
			CTPG usuario = _ctpgRepository.save(novo);
			CadastroCtpgDTO cadastrado = new CadastroCtpgDTO(usuario);			
			_emailService.sendMailUserSignUp(cadastrado, senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(usuario.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}

	@ApiOperation(value = "Atualiza um usuário CT-PG")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<CadastroCtpgDTO> update(@RequestBody CadastroCtpgDTO user) {
		try {
			Optional<CTPG> usuarioBase = _ctpgRepository.findById(user.getId()); 

			if (usuarioBase.isPresent()) {
				CTPG userBase = usuarioBase.get();

				if(user.getSenha() != null && !userBase.getSenha().equals(user.getSenha())) {
					user.setSenha(passwordEncoder.encode(user.getSenha()));
				}
			} 
			CTPG cadastrado =  _ctpgRepository.save(new CTPG(user));
			return ResponseEntity.ok(new CadastroCtpgDTO(cadastrado));
			
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
}
