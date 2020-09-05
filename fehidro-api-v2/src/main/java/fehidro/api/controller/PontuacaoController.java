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

import fehidro.api.model.Pontuacao;
import fehidro.api.repository.PontuacaoRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pontuacao")
public class PontuacaoController {

	@Autowired
	private PontuacaoRepository _pontuacaoRepository;
	
	@ApiOperation(value = "Retorna uma lista de pontuações dos critérios de avaliação")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Pontuacao>> getAll() {		
		return ResponseEntity.ok(_pontuacaoRepository.findAll());
	}

	@ApiOperation(value = "Retorna uma pontuação encontrada por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<Pontuacao> get(@PathVariable(value = "id") Long id) {
		Optional<Pontuacao> pontuacao = _pontuacaoRepository.findById(id);
		if(pontuacao.isPresent()) {
			return ResponseEntity.ok(pontuacao.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra uma nova pontuação")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Pontuacao> add(@RequestBody Pontuacao pontuacao, UriComponentsBuilder uriBuilder) {
		Pontuacao cadastrado = _pontuacaoRepository.save(pontuacao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza uma pontuação")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Pontuacao> update(@RequestBody Pontuacao pontuacao) {
		Pontuacao cadastrado =  _pontuacaoRepository.save(pontuacao);
		return ResponseEntity.ok(cadastrado);
	}

	@ApiOperation(value = "Deleta uma pontuação pelo seu id")
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<Pontuacao> pontuacao = _pontuacaoRepository.findById(id);
		if (pontuacao.isPresent()) {
			_pontuacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
