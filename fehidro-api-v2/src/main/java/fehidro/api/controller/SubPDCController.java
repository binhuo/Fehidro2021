package fehidro.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fehidro.api.model.SubPDC;
import fehidro.api.repository.SubPDCRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/subpdc")
public class SubPDCController {
	
	@Autowired
	private SubPDCRepository _subPDCRepository;
	
	@ApiOperation(value = "Retorna uma lista de subPDCs")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<SubPDC>> getAll() {		
		return ResponseEntity.ok(_subPDCRepository.findAll());
	}

	@ApiOperation(value = "Retorna um subPDC encontrado por seu id")
	@GetMapping(value = "/{id}", produces="application/json")
	public ResponseEntity<SubPDC> get(@PathVariable(value = "id") Long id) {
		Optional<SubPDC> subpdc = _subPDCRepository.findById(id);
		if(subpdc.isPresent()) {
			return ResponseEntity.ok(subpdc.get());
		}

		return ResponseEntity.notFound().build();
	}
}
