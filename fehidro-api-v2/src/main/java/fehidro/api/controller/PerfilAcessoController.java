package fehidro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fehidro.api.model.PerfilAcesso;
import fehidro.api.repository.PerfilAcessoRepository;

@RestController
@RequestMapping("/perfilAcesso")
public class PerfilAcessoController {
	
	@Autowired
	private PerfilAcessoRepository _perfilAcessoRepository;
	
	@GetMapping
	public ResponseEntity<List<PerfilAcesso>> getAll() {		
		return ResponseEntity.ok(_perfilAcessoRepository.findAll());
	}
}
