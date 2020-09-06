package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import fehidro.model.PerfilAcesso;

public class PerfilAcessoRESTClient extends BaseRESTClient implements RESTClientInterface<PerfilAcesso>{

	@Override
	public List<PerfilAcesso> findAll() {
		List<PerfilAcesso> perfis = 	
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_PERFILACESSO_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<PerfilAcesso>>() {});
		
		return perfis;
	}

	@Override
	public PerfilAcesso find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilAcesso create(PerfilAcesso obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PerfilAcesso edit(PerfilAcesso obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
