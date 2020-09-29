package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import fehidro.model.PerfilAcesso;
import fehidro.model.Proposta;
import fehidro.model.Usuario;

public class PropostaRESTClient extends BaseRESTClient implements RESTClientInterface<Proposta>{

	@Override
	public List<Proposta> findAll() {
		List<Proposta> propostas = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Proposta>> () {});
		
		return propostas;		
	} 
	
	public List<Proposta> findEmAberto(Usuario usuario, long instituicao) {
        List<Proposta> propostas = 
                ClientBuilder.newClient().
                target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL + "emAberto/" + usuario.getId() + "/" + instituicao).
                request(MediaType.APPLICATION_JSON).
                header(HttpHeaders.AUTHORIZATION, authToken).get().
                readEntity(new GenericType<List<Proposta>> () {});

        return propostas;
    }
	
	public List<Proposta> findEmAberto(Usuario usuario) {
        List<Proposta> propostas = 
                ClientBuilder.newClient().
                target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL + "emAberto/" + usuario.getId() ).
                request(MediaType.APPLICATION_JSON).
                header(HttpHeaders.AUTHORIZATION, authToken).get().
                readEntity(new GenericType<List<Proposta>> () {});

        return propostas;
    }
 
 
	@Override
	public Proposta find(Long id) {
		Proposta proposta = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				get().
				readEntity(Proposta.class);
		
		return proposta;
	}

	@Override
	public Proposta create(Proposta obj) {
		Proposta proposta = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL).
				queryParam("proposta", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).	
				readEntity(Proposta.class);	
		
		return proposta;
	}

	@Override
	public Proposta edit(Proposta obj) {
		Proposta proposta = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PROPOSTA_URL).
				queryParam("proposta", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Proposta.class);	
		
		return proposta;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
