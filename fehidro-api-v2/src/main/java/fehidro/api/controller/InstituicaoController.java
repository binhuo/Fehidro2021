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
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/instituicao")
public class InstituicaoController {

	@Autowired
	private InstituicaoRepository _instituicaoRepository;
	
	@ApiOperation(value = "Retorna uma lista de instituições")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Instituicao>> getAll() {		
		return ResponseEntity.ok(_instituicaoRepository.findAll());
	}

	@ApiOperation(value = "Retorna uma instituição encontrada por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<Instituicao> get(@PathVariable(value = "id") Long id) {
		Optional<Instituicao> instituicao = _instituicaoRepository.findById(id);
		if(instituicao.isPresent()) {
			return ResponseEntity.ok(instituicao.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra uma nova instituição")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Instituicao> add(@RequestBody Instituicao instituicao, UriComponentsBuilder uriBuilder) {
		Instituicao cadastrado = _instituicaoRepository.save(instituicao);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza uma instituição")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Instituicao> update(@RequestBody Instituicao instituicao) {
		Optional<Instituicao> instituicaoBase = _instituicaoRepository.findById(instituicao.getId());
		if(instituicaoBase.isPresent()) {
			Instituicao base = instituicaoBase.get();
			instituicao.setPropostas(base.getPropostas());
			
			Instituicao cadastrado =  _instituicaoRepository.save(instituicao);
			return ResponseEntity.ok(cadastrado);
		}
		
		return ResponseEntity.notFound().build();
	}
}
