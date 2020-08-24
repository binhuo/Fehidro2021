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

import fehidro.api.model.Instituicao;
import fehidro.api.repository.InstituicaoRepository;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

	@Autowired
	private InstituicaoRepository _instituicaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Instituicao>> getAll() {		
		return ResponseEntity.ok(_instituicaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Instituicao> get(@PathVariable(value = "id") Long id) {
		Optional<Instituicao> instituicao = _instituicaoRepository.findById(id);
		if(instituicao.isPresent()) {
			return ResponseEntity.ok(instituicao.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Instituicao> add(@RequestBody Instituicao instituicao, UriComponentsBuilder uriBuilder) {
		Instituicao cadastrado = _instituicaoRepository.save(instituicao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@PutMapping
	public ResponseEntity<Instituicao> update(@RequestBody Instituicao instituicao) {
		Instituicao cadastrado =  _instituicaoRepository.save(instituicao);
		return ResponseEntity.ok(cadastrado);
	}
}
