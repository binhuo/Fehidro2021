package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import fehidro.model.SubPDC;

public class SubPDCRESTClient extends BaseRESTClient implements RESTClientInterface<SubPDC>{
	@Override
	public List<SubPDC> findAll() {
		List<SubPDC> subPDCs = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBPDC_URL).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<SubPDC>> () {});
		
		return subPDCs;
	}

	@Override
	public SubPDC find(Long id) {
		SubPDC subPDC = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_SUBPDC_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(SubPDC.class);
		
		return subPDC;
	}

	@Override
	public SubPDC create(SubPDC obj) {
		return null;
	}

	@Override
	public SubPDC edit(SubPDC obj) {
		return null;
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
}
