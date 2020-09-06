package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.TipoProposta;

public class TipoPropostaRESTClient extends BaseRESTClient implements RESTClientInterface<TipoProposta>{

	@Override
	public List<TipoProposta> findAll() {
		List<TipoProposta> tiposProposta = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_TIPOPROPOSTA_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<TipoProposta>> () {});
		
		return tiposProposta;
	}

	@Override
	public TipoProposta find(Long id) {
		TipoProposta tipoProposta = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_TIPOPROPOSTA_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(TipoProposta.class);
		
		return tipoProposta;
	}

	@Override
	public TipoProposta create(TipoProposta obj) {
		TipoProposta tipoProposta = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_TIPOPROPOSTA_URL).
				queryParam("tipoProposta", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(TipoProposta.class);	
		
		return tipoProposta;
	}

	@Override
	public TipoProposta edit(TipoProposta obj) {
		TipoProposta tipoProposta = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_TIPOPROPOSTA_URL).
				queryParam("usuario", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(TipoProposta.class);	
		
		return tipoProposta;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_TIPOPROPOSTA_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}

}
