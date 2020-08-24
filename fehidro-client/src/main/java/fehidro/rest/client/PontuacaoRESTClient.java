package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.Pontuacao;

public class PontuacaoRESTClient implements RESTClientInterface<Pontuacao> {
	
	public List<Pontuacao> findAll(){
		List<Pontuacao> pontuacaos = 	
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PONTUACAO_URL).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Pontuacao>> () {});
		
		return pontuacaos;
	}

	@Override
	public Pontuacao find(Long id) {
		Pontuacao pontuacao = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PONTUACAO_URL + id).
				request(MediaType.APPLICATION_JSON).get()
				.readEntity(Pontuacao.class);
		
		return pontuacao;
	}

	@Override
	public Pontuacao create(Pontuacao obj) {
		Pontuacao pontuacao = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PONTUACAO_URL).
				queryParam("pontuacao", obj).
				request(MediaType.APPLICATION_JSON).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Pontuacao.class);
		
		return pontuacao;
	}

	@Override
	public Pontuacao edit(Pontuacao obj) {
		Pontuacao pontuacao = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PONTUACAO_URL).
				queryParam("pontuacao", obj).
				request(MediaType.APPLICATION_JSON).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Pontuacao.class);
		
		return pontuacao;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PONTUACAO_URL + id).
				request(MediaType.APPLICATION_JSON).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}
	
}