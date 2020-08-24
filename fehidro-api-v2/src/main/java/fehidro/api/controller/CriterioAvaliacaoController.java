package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

import fehidro.api.model.CriterioAvaliacao;
import fehidro.api.repository.CriterioAvaliacaoRepository;

@RestController
@RequestMapping("/criterioAvaliacao")
public class CriterioAvaliacaoController {

	@Autowired
	private CriterioAvaliacaoRepository _criterioAvaliacaoRepository; 

	@GetMapping
	public ResponseEntity<List<CriterioAvaliacao>> getAll() {		
		return ResponseEntity.ok(_criterioAvaliacaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CriterioAvaliacao> get(@PathVariable(value = "id") Long id) {
		Optional<CriterioAvaliacao> criterio = _criterioAvaliacaoRepository.findById(id);
		if(criterio.isPresent()) {
			return ResponseEntity.ok(criterio.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<CriterioAvaliacao> add(@RequestBody CriterioAvaliacao criterio, UriComponentsBuilder uriBuilder) {
		CriterioAvaliacao cadastrado = _criterioAvaliacaoRepository.save(criterio);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@PutMapping
	public ResponseEntity<CriterioAvaliacao> update(@RequestBody CriterioAvaliacao criterio) {
		CriterioAvaliacao cadastrado =  _criterioAvaliacaoRepository.save(criterio);
		return ResponseEntity.ok(cadastrado);
	}

	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<CriterioAvaliacao> criterio = _criterioAvaliacaoRepository.findById(id);
		if (criterio.isPresent()) {
			_criterioAvaliacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
