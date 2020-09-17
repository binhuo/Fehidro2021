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

import fehidro.api.model.Proposta;
import fehidro.api.repository.PropostaRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/proposta")
public class PropostaController {

	@Autowired
	private PropostaRepository _propostaRepository;
	
	@ApiOperation(value = "Retorna uma lista de propostas")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<Proposta>> getAll() {		
		return ResponseEntity.ok(_propostaRepository.findAll());
	}
	
//	@ApiOperation(value = "Retorna uma lista de propostas")
//	@GetMapping(value = "/{usuario}" ,produces="application/json")
//	public ResponseEntity<List<Proposta>> findEmAberto(@PathVariable(value = "usuario") Usuario usuario) {		
//		return ResponseEntity.ok(_propostaRepository.findEmAberto(usuario));
//	}

	@ApiOperation(value = "Retorna uma proposta encontrada por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<Proposta> get(@PathVariable(value = "id") Long id) {
		Optional<Proposta> proposta = _propostaRepository.findById(id);
		if(proposta.isPresent()) {
			return ResponseEntity.ok(proposta.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra uma nova proposta")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Proposta> add(@RequestBody Proposta proposta, UriComponentsBuilder uriBuilder) {
		Proposta cadastrado = _propostaRepository.save(proposta);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza uma proposta")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Proposta> update(@RequestBody Proposta proposta) {
		Proposta cadastrado =  _propostaRepository.save(proposta);
		return ResponseEntity.ok(cadastrado);
	}

	@ApiOperation(value = "Deleta uma proposta pelo seu id")
	@DeleteMapping
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Optional<Proposta> proposta = _propostaRepository.findById(id);
		if (proposta.isPresent()) {
			_propostaRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
