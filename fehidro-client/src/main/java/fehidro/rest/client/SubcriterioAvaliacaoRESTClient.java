package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import fehidro.model.Pontuacao;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.dto.SubcriterioExibicaoDTO;

public class SubcriterioAvaliacaoRESTClient implements RESTClientInterface<SubcriterioAvaliacao>{

	@Override
	public List<SubcriterioAvaliacao> findAll() {
		List<SubcriterioAvaliacao> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<SubcriterioAvaliacao>> () {});
		
		return subcriterios;		
	}
	
	public List<SubcriterioExibicaoDTO> obterSubcriteriosDTO() {
		List<SubcriterioExibicaoDTO> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "dtoExibicao/").
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<SubcriterioExibicaoDTO>> () {});
		
		return subcriterios;		
	}
	
	public List<SubcriterioExibicaoDTO> obterSubcriteriosDTOPorSubcriterio(Long id) {
		List<SubcriterioExibicaoDTO> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "dtoExibicao/" + id).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<SubcriterioExibicaoDTO>> () {});
		
		return subcriterios;		
	}
	
	public List<Pontuacao> obterPontuacoesPorSubcriterio(Long id) {
		List<Pontuacao> pontuacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "pontuacao/" + id).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Pontuacao>> () {});
		
		return pontuacoes;		
	}
 
	@Override
	public SubcriterioAvaliacao find(Long id) {
		SubcriterioAvaliacao subcriterio = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + id).
				request(MediaType.APPLICATION_JSON).get()
				.readEntity(SubcriterioAvaliacao.class);
		
		return subcriterio;
	}

	@Override
	public SubcriterioAvaliacao create(SubcriterioAvaliacao obj) {
		SubcriterioAvaliacao subcriterio = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL).
				queryParam("subcriterioAvaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).	
				readEntity(SubcriterioAvaliacao.class);	
		
		return subcriterio;
	}

	@Override
	public SubcriterioAvaliacao edit(SubcriterioAvaliacao obj) {
		SubcriterioAvaliacao subcriterio = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL).
				queryParam("subcriterioAvaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(SubcriterioAvaliacao.class);	
		
		return subcriterio;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
