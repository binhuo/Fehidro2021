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

import fehidro.api.model.TipoProposta;
import fehidro.api.repository.TipoPropostaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/tipoProposta")
public class TipoPropostaController {
	
	@Autowired
	private TipoPropostaRepository _tipoPropostaRepository;
	
	@ApiOperation(value = "Retorna uma lista de tipos de proposta")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<TipoProposta>> getAll() {		
		return ResponseEntity.ok(_tipoPropostaRepository.findAll());
	}

	@ApiOperation(value = "Retorna um tipo de proposta encontrado por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<TipoProposta> get(@PathVariable(value = "id") Long id) {
		Optional<TipoProposta> tipoProposta = _tipoPropostaRepository.findById(id);
		if(tipoProposta.isPresent()) {
			return ResponseEntity.ok(tipoProposta.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra um novo tipo de proposta")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<TipoProposta> add(@RequestBody TipoProposta tipoProposta, UriComponentsBuilder uriBuilder) {
		TipoProposta cadastrado = _tipoPropostaRepository.save(tipoProposta);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza um tipo de proposta")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<TipoProposta> update(@RequestBody TipoProposta tipoProposta) {
		TipoProposta cadastrado =  _tipoPropostaRepository.save(tipoProposta);
		return ResponseEntity.ok(cadastrado);
	}
}
