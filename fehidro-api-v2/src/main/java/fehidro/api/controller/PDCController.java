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

import fehidro.api.model.PDC;
import fehidro.api.repository.PDCRepository;
import io.swagger.annotations.ApiOperation;
import fehidro.api.model.Meta;
import fehidro.api.model.SubPDC;

@RestController
@RequestMapping("/pdc")
public class PDCController {

	@Autowired
	private PDCRepository _pdcRepository;
	
	@ApiOperation(value = "Retorna uma lista de PDCs")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<PDC>> getAll() {		
		return ResponseEntity.ok(_pdcRepository.findAll()); 
	}

	@ApiOperation(value = "Retorna um PDC encontrado por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<PDC> get(@PathVariable(value = "id") Long id) {
		Optional<PDC> pdc = _pdcRepository.findById(id);
		if(pdc.isPresent()) {
			return ResponseEntity.ok(pdc.get());
		}

		return ResponseEntity.notFound().build();
	}

	@ApiOperation(value = "Cadastra um novo PDC")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<PDC> add(@RequestBody PDC pdc, UriComponentsBuilder uriBuilder) {
		PDC cadastrado = _pdcRepository.save(pdc);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
		return ResponseEntity.created(uri).body(cadastrado);
	}

	@ApiOperation(value = "Atualiza um PDC")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<PDC> update(@RequestBody PDC pdc) {
		
		for(SubPDC s : pdc.getSubPDCs()){
			s.setPdc(pdc);
			for (Meta m : s.getMetas()) {
				m.setSubpdc(s);
			}
		}
		
		PDC cadastrado =  _pdcRepository.save(pdc);
		return ResponseEntity.ok(cadastrado);
	}
}
