package fehidro.rest.client;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fehidro.model.CredenciaisUsuario;

public class AuthRESTClient extends BaseRESTClient {

	public boolean realizarLogin(String login, String senha) {
		try {
			CredenciaisUsuario creds = new CredenciaisUsuario(login, senha);

			Response resul = ClientBuilder.newClient().target(REST_WEBSERVICE_URL + "login").queryParam("request", creds)
					.request(MediaType.APPLICATION_JSON).post(Entity.entity(creds, MediaType.APPLICATION_JSON));
			
			if (resul.getStatus() == Response.Status.OK.getStatusCode()) {
				Object token = resul.getHeaders().getFirst("Authentication");
				authToken = token.toString();
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
