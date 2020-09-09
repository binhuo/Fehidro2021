package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import fehidro.model.Pontuacao;
import fehidro.model.Proposta;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;
import fehidro.model.dto.SubcriterioExibicaoDTO;

public class SubcriterioAvaliacaoRESTClient extends BaseRESTClient implements RESTClientInterface<SubcriterioAvaliacao>{

	@Override
	public List<SubcriterioAvaliacao> findAll() {
		List<SubcriterioAvaliacao> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubcriterioAvaliacao>> () {});
		
		return subcriterios;		
	}
	
	public List<SubcriterioAvaliacao> findEmAberto(Usuario usuario, Proposta proposta) {
		System.out.println(usuario.getId());
		System.out.println(proposta.getId());
		List<SubcriterioAvaliacao> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "emAberto/" + usuario.getId() + "/" + proposta.getId()).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubcriterioAvaliacao>> () {});
		
		return subcriterios;		
	}
	
	public List<SubcriterioExibicaoDTO> obterSubcriteriosDTO() {
		List<SubcriterioExibicaoDTO> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "dtoExibicao/").
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubcriterioExibicaoDTO>> () {});
		
		return subcriterios;		
	}
	
	public List<SubcriterioExibicaoDTO> obterSubcriteriosDTOPorSubcriterio(Long id) {
		List<SubcriterioExibicaoDTO> subcriterios = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "dtoExibicao/" + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubcriterioExibicaoDTO>> () {});
		
		return subcriterios;		
	}
	
	public List<Pontuacao> obterPontuacoesPorSubcriterio(Long id) {
		List<Pontuacao> pontuacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + "pontuacao/" + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Pontuacao>> () {});
		
		return pontuacoes;		
	}
 
	@Override
	public SubcriterioAvaliacao find(Long id) {
		SubcriterioAvaliacao subcriterio = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(SubcriterioAvaliacao.class);
		
		return subcriterio;
	}

	@Override
	public SubcriterioAvaliacao create(SubcriterioAvaliacao obj) {
		SubcriterioAvaliacao subcriterio = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBCRITERIO_URL).
				queryParam("subcriterioAvaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
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
				header(HttpHeaders.AUTHORIZATION, authToken).
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
