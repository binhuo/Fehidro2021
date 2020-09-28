package fehidro.api.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import fehidro.api.model.Avaliacao;
import fehidro.api.model.PerfilAcesso;
//import fehidro.api.model.CriterioAvaliacao;
import fehidro.api.model.Proposta;
import fehidro.api.model.SubPDC;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;
import fehidro.api.repository.AvaliacaoRepository;
import fehidro.model.dto.avaliacao.CadastroAvaliacaoDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoRepository _avaliacaoRepository;
	
	@ApiOperation(value = "Retorna todas as avaliacoes")
	@GetMapping(produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> getAll() {
		List<Avaliacao> list = _avaliacaoRepository.findAll();
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna uma avaliação encontrada por seu id")
	@GetMapping(value="/{id}", produces="application/json")
	public ResponseEntity<CadastroAvaliacaoDTO> get(@PathVariable(value = "id") Long id) {
		Optional<Avaliacao> aval = _avaliacaoRepository.findById(id);
		if (aval.isPresent()) {
			return ResponseEntity.ok(new CadastroAvaliacaoDTO(aval.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@ApiOperation(value = "Retorna todas as avaliacoes feita por um usuario")
	@GetMapping(value="/findAllUsuario/{usuario}" , produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> findAllUsuario(@PathVariable(value = "usuario") Usuario usuario) {
		List<Avaliacao> list = _avaliacaoRepository.findAllUsuario(usuario);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliacoes onde a avaliacao no subcriterio e feito pela Secretaria Executiva")
	@GetMapping(value="/findAllAvaliacaoSubcriterioSecretaria/{proposta}" , produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> findAllAvaliacaoSubcriterioSecretaria(@PathVariable(value = "proposta") Proposta proposta) {
		List<Avaliacao> list = _avaliacaoRepository.findAllAvaliacaoSubcriterioSecretaria(proposta);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas avaliação efetuada pelo avaliador especificado")
	@GetMapping(value="/listarAvaliador/{avaliador}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarAvaliador(@PathVariable(value = "avaliador") Usuario avaliador) {
		List<Avaliacao> list = _avaliacaoRepository.findAllByAvaliador(avaliador);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliação feitas em um subcriterio especificado")
	@GetMapping(value = "/listarSubcriteiro/{subcriterio}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarSubcriteiro(@PathVariable(value = "subcriterio") SubcriterioAvaliacao subcriterio) {
		List<Avaliacao> list = _avaliacaoRepository.findAllBySubcriterio(subcriterio);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliação feitas em um SubPDC especificado")
	@GetMapping(value="/listarSubPDC/{subpdc}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarSubPDC(@PathVariable(value = "subpdc") SubPDC subpdc) {
		List<Avaliacao> list = _avaliacaoRepository.findAllBySubPdc(subpdc);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliação feitas em uma proposta especificada")
	@GetMapping(value="/listarProposta/{proposta}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarProposta(@PathVariable(value = "proposta") Proposta proposta) {
		List<Avaliacao> list = _avaliacaoRepository.findAllByProposta(proposta);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliação feitas por um especifico usuario dentro de uma proposta especificada")
	@GetMapping(value = "/listarAvaliadorProposta/{proposta}/{avaliador}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarAvaliadorProposta(@PathVariable(value = "proposta") Proposta proposta,@PathVariable(value = "avaliador") Usuario avaliador ) {
		List<Avaliacao> list = _avaliacaoRepository.findAllByAvaliadorProposta(proposta,avaliador);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Retorna todas as avaliação feitas em um subcriterio de uma proposta especificada")
	@GetMapping(value = "/listarSubcriterioProposta/{proposta}/{subcriterio}", produces="application/json")
	public ResponseEntity<List<CadastroAvaliacaoDTO>> listarSubcriterioProposta(@PathVariable(value = "proposta") Proposta proposta,@PathVariable(value = "subcriterio") SubcriterioAvaliacao subcriterio ) {
		List<Avaliacao> list = _avaliacaoRepository.findAllBySubcriterioProposta(proposta,subcriterio);
		List<CadastroAvaliacaoDTO> resul = list.stream().map(u -> {return new CadastroAvaliacaoDTO(u);}).collect(Collectors.toList());
		return ResponseEntity.ok().body(resul);
	}
	
	@ApiOperation(value = "Cadastra uma avaliacao")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<CadastroAvaliacaoDTO> add(@RequestBody CadastroAvaliacaoDTO avaliacao, UriComponentsBuilder uriBuilder) {
		
		try {
			Avaliacao novo = new Avaliacao(avaliacao);

			Avaliacao aval = _avaliacaoRepository.save(novo);
			CadastroAvaliacaoDTO cadastrado = new CadastroAvaliacaoDTO(aval);
			URI uri = uriBuilder.path("/{id}").buildAndExpand(cadastrado.getId()).toUri();
			return ResponseEntity.created(uri).body(cadastrado);

		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
	}
	
	@ApiOperation(value = "Atualiza uma avaliacao")
	@PutMapping(produces="application/json", consumes="application/json")
	public ResponseEntity<Avaliacao> update(@RequestBody Avaliacao avaliacao) {
		Avaliacao aval = _avaliacaoRepository.save(avaliacao);	
		return ResponseEntity.ok(aval);
	}
	
}
