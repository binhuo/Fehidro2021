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

@RestController
@RequestMapping("/deliberacao")
public class DeliberacaoController {
	
	@Autowired
	private DeliberacaoRepository _deliberacaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Deliberacao>> getAll() {		
		return ResponseEntity.ok(_deliberacaoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Deliberacao> get(@PathVariable(value = "id") Long id) {
		Optional<Deliberacao> deliberacao = _deliberacaoRepository.findById(id);
		if(deliberacao.isPresent()) {
			return ResponseEntity.ok(deliberacao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<Deliberacao> add(@RequestBody Deliberacao deliberacao, UriComponentsBuilder uriBuilder) {
		Deliberacao cadastrado = _deliberacaoRepository.save(deliberacao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}
	
	@PutMapping
	public ResponseEntity<Deliberacao> update(@RequestBody Deliberacao deliberacao) {
		Deliberacao cadastrado =  _deliberacaoRepository.save(deliberacao);
		return ResponseEntity.ok(cadastrado);
	}
}
