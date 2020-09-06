package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import fehidro.api.model.Avaliacao;
import fehidro.api.model.Pontuacao;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;
import fehidro.api.repository.PontuacaoRepository;
import fehidro.model.dto.avaliacao.CadastroAvaliacaoDTO;

@RestController
@RequestMapping("/pontuacao")
public class PontuacaoController {

	@Autowired
	private PontuacaoRepository _pontuacaoRepository;
	
	@GetMapping
	public ResponseEntity<List<Pontuacao>> getAll() {		
		return ResponseEntity.ok(_pontuacaoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pontuacao> get(@PathVariable(value = "id") Long id) {
		Optional<Pontuacao> pontuacao = _pontuacaoRepository.findById(id);
		if(pontuacao.isPresent()) {
			return ResponseEntity.ok(pontuacao.get());
		}

		return ResponseEntity.notFound().build();
	}
	
	@GetMapping("/subcriterio/{subcriterio}")
	public ResponseEntity<List<Pontuacao>> findAllBySubcriterio(@PathVariable(value = "subcriterio") SubcriterioAvaliacao subcriterio) {
		List<Pontuacao> list = _pontuacaoRepository.findAllBySubcriterio(subcriterio);
		return ResponseEntity.ok().body(list);
	}

	@PostMapping
	public ResponseEntity<Pontuacao> add(@RequestBody Pontuacao pontuacao, UriComponentsBuilder uriBuilder) {
		Pontuacao cadastrado = _pontuacaoRepository.save(pontuacao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@PutMapping
	public ResponseEntity<Pontuacao> update(@RequestBody Pontuacao pontuacao) {
		Pontuacao cadastrado =  _pontuacaoRepository.save(pontuacao);
		return ResponseEntity.ok(cadastrado);
	}

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
