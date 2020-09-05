package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
import fehidro.model.dto.usuario.CadastroUsuarioDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository _usuarioRepository;
	
//	@Autowired
//	private EmailUtil emailService;
		
	@ApiOperation(value = "Retorna uma lista de usuários independente do perfil de acesso")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<CadastroUsuarioDTO>> getAll() {
		List<Usuario> list = _usuarioRepository.findAll();
		List<CadastroUsuarioDTO> resul = list.stream().map(u -> {return new CadastroUsuarioDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna um usuário encontrado por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<CadastroUsuarioDTO> get(@PathVariable(value = "id") Long id) {
		Optional<Usuario> user = _usuarioRepository.findById(id);
		if (user.isPresent()) {
			return ResponseEntity.ok(new CadastroUsuarioDTO(user.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Retorna um usuário encontrado por seu login")
	@GetMapping(value="/obterPorLogin/{login}", produces="application/json")
	public ResponseEntity<CadastroUsuarioDTO> obterPorLogin(@PathVariable(value = "login") String login) {
		Optional<Usuario> user = _usuarioRepository.findByLogin(login);
		if (user.isPresent()) {
			return ResponseEntity.ok(new CadastroUsuarioDTO(user.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Retorna um usuário encontrado por seu CPF")
	@GetMapping(value="/obterPorCPF/{cpf}", produces="application/json")
	public ResponseEntity<CadastroUsuarioDTO> obterPorCPF(@PathVariable(value = "cpf") String cpf) {
		Optional<Usuario> user = _usuarioRepository.findByCPF(cpf);
		if (user.isPresent()) {
			return ResponseEntity.ok(new CadastroUsuarioDTO(user.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Retorna uma lista de usuários de um perfil de acesso específico")
	@GetMapping(value="/obterPorPerfilAcesso/{perfilacesso}", produces="application/json")
	public ResponseEntity<List<CadastroUsuarioDTO>> obterPorPerfilAcesso(@PathVariable(value = "perfilacesso") Long perfilacesso) {
		List<Usuario> list = _usuarioRepository.findAllByPerfilAcesso(perfilacesso);
		List<CadastroUsuarioDTO> resul = list.stream().map(u -> {return new CadastroUsuarioDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Cadastra um novo usuário")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<CadastroUsuarioDTO> add(@RequestBody CadastroUsuarioDTO usuario, UriComponentsBuilder uriBuilder) {
		
		try {
			Usuario novo = new Usuario(usuario);
			String senha = Password.generateRandomPassword(10);
			
			novo.setLogin();
			novo.setAtivo();	
			novo.setSenha(Password.hashPassword(senha));
			Usuario user = _usuarioRepository.save(novo);
			CadastroUsuarioDTO cadastrado = new CadastroUsuarioDTO(user);
			//TODO: migrar para spring-boot-starter-mail
			//EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@ApiOperation(value = "Atualiza um usuário")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Usuario> update(@RequestBody Usuario usuario) {
		Usuario user = _usuarioRepository.save(usuario);	
		return ResponseEntity.ok(user);
	}
	
}
