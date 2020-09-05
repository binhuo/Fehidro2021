package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.Deliberacao;
import fehidro.api.repository.DeliberacaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/deliberacao")
public class DeliberacaoController {
	
	@Autowired
	private DeliberacaoRepository _deliberacaoRepository;
	
	@ApiOperation(value = "Retorna uma lista de deliberações")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Deliberacao>> getAll() {		
		return ResponseEntity.ok(_deliberacaoRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna uma deliberação encontrada por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<Deliberacao> get(@PathVariable(value = "id") Long id) {
		Optional<Deliberacao> deliberacao = _deliberacaoRepository.findById(id);
		if(deliberacao.isPresent()) {
			return ResponseEntity.ok(deliberacao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@ApiOperation(value = "Cadastra uma nova deliberação")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Deliberacao> add(@RequestBody Deliberacao deliberacao, UriComponentsBuilder uriBuilder) {
		Deliberacao cadastrado = _deliberacaoRepository.save(deliberacao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}
	
	@ApiOperation(value = "Atualiza uma deliberação")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Deliberacao> update(@RequestBody Deliberacao deliberacao) {
		Deliberacao cadastrado =  _deliberacaoRepository.save(deliberacao);
		return ResponseEntity.ok(cadastrado);
	}
}
