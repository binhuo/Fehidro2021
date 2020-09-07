package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import fehidro.model.Deliberacao;

public class DeliberacaoRESTClient extends BaseRESTClient implements RESTClientInterface<Deliberacao>{

	@Override
	public List<Deliberacao> findAll() {
		List<Deliberacao> deliberacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_DELIBERACAO_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Deliberacao>> () {});
		
		return deliberacoes;
	}

	@Override
	public Deliberacao find(Long id) {
		Deliberacao deliberacao = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_DELIBERACAO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(Deliberacao.class);
		
		return deliberacao;
	}

	@Override
	public Deliberacao create(Deliberacao obj) {
		Deliberacao deliberacao = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_DELIBERACAO_URL).
				queryParam("deliberacao", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Deliberacao.class);
		
		return deliberacao;
	}

	@Override
	public Deliberacao edit(Deliberacao obj) {
		Deliberacao deliberacao = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_DELIBERACAO_URL).
				queryParam("deliberacao", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Deliberacao.class);	
		
		return deliberacao;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
