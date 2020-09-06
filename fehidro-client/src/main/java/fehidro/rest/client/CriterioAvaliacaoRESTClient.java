package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.CriterioAvaliacao;
import fehidro.model.SubcriterioAvaliacao;

public class CriterioAvaliacaoRESTClient extends BaseRESTClient implements RESTClientInterface<CriterioAvaliacao> {
	@Override
	public List<CriterioAvaliacao> findAll() {
		List<CriterioAvaliacao> criterios  = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<CriterioAvaliacao>> () {});
		
		return criterios;
	}
	
	
	public List<SubcriterioAvaliacao> obterSubcriterios(long id) {
		List<SubcriterioAvaliacao> criterios  = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL + "subcriterios/" + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubcriterioAvaliacao>> () {});
		
		return criterios;
	}

	@Override
	public CriterioAvaliacao find(Long id) {
		CriterioAvaliacao criterio = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(CriterioAvaliacao.class);
		
		return criterio;
	}

	@Override
	public CriterioAvaliacao create(CriterioAvaliacao obj) {
		CriterioAvaliacao criterio = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL).
				queryParam("criterio", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).	
				readEntity(CriterioAvaliacao.class);	
		
		return criterio;
	}

	@Override
	public CriterioAvaliacao edit(CriterioAvaliacao obj) {
		CriterioAvaliacao criterio = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL).
				queryParam("criterio", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(CriterioAvaliacao.class);	
		
		return criterio;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_CRITERIO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}
}
