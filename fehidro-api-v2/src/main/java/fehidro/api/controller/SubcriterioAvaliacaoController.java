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

import fehidro.api.model.Proposta;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;
import fehidro.api.repository.SubcriterioAvaliacaoRepository;
import fehidro.model.dto.subcriterio.SubcriterioExibicaoDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/subcriterioAvaliacao")
public class SubcriterioAvaliacaoController {
	@Autowired
	private SubcriterioAvaliacaoRepository _subcriterioAvaliacaoRepository; 

	@ApiOperation(value = "Retorna uma lista de subcritérios de avaliação")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<SubcriterioAvaliacao>> getAll() {		
		return ResponseEntity.ok(_subcriterioAvaliacaoRepository.findAll());
	}
	
	@ApiOperation(value = "Retorna a lista de subcritérios disponiveis para avaliacao pelo usuario especificado")
	@GetMapping(value="/emAberto/{usuario}/{proposta}", produces="application/json")
	public ResponseEntity<List<SubcriterioAvaliacao>> findEmAberto(@PathVariable(value = "usuario") Usuario usuario, @PathVariable(value = "proposta") Proposta proposta) {
		return ResponseEntity.ok(_subcriterioAvaliacaoRepository.findEmAberto(usuario, proposta));
	}
	
	//TODO: Remover se nao estiver sendo usado
	@GetMapping("/dtoExibicao/")
	public ResponseEntity<List<SubcriterioExibicaoDTO>> obterDtosExibicao() {
		List<SubcriterioAvaliacao> list = _subcriterioAvaliacaoRepository.findAll();
		List<SubcriterioExibicaoDTO> resul = list.stream().map(u -> {return new SubcriterioExibicaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	//TODO: Remover se nao estiver sendo usado
	@GetMapping("/dtoExibicao/{id}")
	public ResponseEntity<SubcriterioExibicaoDTO> obterPorDtoExibicao(@PathVariable(value = "id") long id) {
		Optional<SubcriterioAvaliacao> subcriteiro = _subcriterioAvaliacaoRepository.findById(id);
		if (subcriteiro.isPresent()) {
			return ResponseEntity.ok(new SubcriterioExibicaoDTO(subcriteiro.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Cadastra um novo subcritério de avaliação")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<SubcriterioAvaliacao> add(@RequestBody SubcriterioAvaliacao subcriterio, UriComponentsBuilder uriBuilder) {
		SubcriterioAvaliacao cadastrado = _subcriterioAvaliacaoRepository.save(subcriterio);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza um subcritério de avaliação")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<SubcriterioAvaliacao> update(@RequestBody SubcriterioAvaliacao subcriterio) {
		SubcriterioAvaliacao cadastrado =  _subcriterioAvaliacaoRepository.save(subcriterio);
		return ResponseEntity.ok(cadastrado);
	}

	@ApiOperation(value = "Deleta um subcritério de avaliação pelo seu id")
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<SubcriterioAvaliacao> subcriterio = _subcriterioAvaliacaoRepository.findById(id);
		if (subcriterio.isPresent()) {
			_subcriterioAvaliacaoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
