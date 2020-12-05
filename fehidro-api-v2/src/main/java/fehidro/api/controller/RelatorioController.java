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
import fehidro.api.model.Relatorio;
import fehidro.api.model.SubPDC;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;
import fehidro.api.repository.AvaliacaoRepository;
import fehidro.model.dto.avaliacao.CadastroAvaliacaoDTO;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/relatorio")
public class RelatorioController{
	
	@Autowired
	private AvaliacaoRepository _avaliacaoRepository;
	
	@ApiOperation(value = "Retorna relatorio por SubPdc")
	@GetMapping(value="/subPdc/{subPdc}",produces="application/json")
	public ResponseEntity<Relatorio> getSubPdc(@PathVariable(value = "subPdc") SubPDC subPdc) {
		Relatorio relatorio = new Relatorio();
		
		List<Avaliacao> avaliacoes = _avaliacaoRepository.findAllBySubPdc(subPdc);
		relatorio.setItensRelatorio(avaliacoes);
		
		return ResponseEntity.ok().body(relatorio);
	}
	
	@ApiOperation(value = "Retorna relatorio Final")
	@GetMapping(value="/final",produces="application/json")
	public ResponseEntity<Relatorio> getFinal() {
		Relatorio relatorio = new Relatorio();
		
		List<Avaliacao> avaliacoes = _avaliacaoRepository.findAll();
		relatorio.setItensRelatorio(avaliacoes);
		
		return ResponseEntity.ok().body(relatorio);
	}
	
}
