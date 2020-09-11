package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;

import fehidro.model.Avaliacao;
//import fehidro.model.CriterioAvaliacao;
import fehidro.model.PDC;
import fehidro.model.Proposta;
import fehidro.model.SubPDC;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;


public class AvaliacaoRESTClient extends BaseRESTClient implements RESTClientInterface<Avaliacao>{

	@Override
	public List<Avaliacao> findAll() {
		//List<Avaliacao> avaliacoes = ClientBuilder.newClient().target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL).request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Avaliacao>> () {});

		Client c = ClientBuilder.newClient();
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL);
		Builder b = t.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, authToken);
		Response r = b.get();
		List<Avaliacao> out = r.readEntity(new GenericType<List<Avaliacao>> () {});
		return out;
		
		//return avaliacoes;
	}
	
	public List<Avaliacao> findAllAvaliacaoSubcriterioSecretaria(Proposta proposta) {
		//List<Avaliacao> avaliacoes = ClientBuilder.newClient().target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL).request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Avaliacao>> () {});

		Client c = ClientBuilder.newClient();
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "findAllAvaliacaoSubcriterioSecretaria/" + proposta.getId());
		Builder b = t.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, authToken);
		Response r = b.get();
		List<Avaliacao> out = r.readEntity(new GenericType<List<Avaliacao>> () {});
		return out;
		
		//return avaliacoes;
	}

	@Override
	public Avaliacao find(Long id) {
		Avaliacao avaliacao = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get()
				.readEntity(Avaliacao.class);
		
		return avaliacao;
	}

//	 public List<Avaliacao> listarPDC(PDC pdc) {
//	 	List<Avaliacao> avaliacoes = 
//	 			ClientBuilder.newClient().
//	 			target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarPDC/" +pdc).
//				request(MediaType.APPLICATION_JSON).
//	 			header(HttpHeaders.AUTHORIZATION, authToken).get().
//	 			readEntity(new GenericType<List<Avaliacao>> () {});
//		
//	 	return avaliacoes;
//	}
	
		public List<Avaliacao> listarSubPDC(SubPDC subpdc) {
			
			Client c = ClientBuilder.newClient();
			WebTarget t = c.target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubPDC/" + subpdc.getId());
			Builder b = t.request(MediaType.APPLICATION_JSON);
			Response r = b.header(HttpHeaders.AUTHORIZATION, authToken).get();
			List<Avaliacao> out = r.readEntity(new GenericType<List<Avaliacao>> () {});
			return out;
			
			
			//return avaliacoes;
		}
	
	public List<Avaliacao> listarAvaliador(Usuario avaliador) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarAvaliador/" +avaliador).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	// public List<Avaliacao> listarCriterio(CriterioAvaliacao criterio) {
	// 	List<Avaliacao> avaliacoes = 
	// 			ClientBuilder.newClient().
	// 			target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarCriterio/" +criterio).
	// 			request(MediaType.APPLICATION_JSON).
	// 			header(HttpHeaders.AUTHORIZATION, authToken).get().
	// 			readEntity(new GenericType<List<Avaliacao>> () {});
		
	// 	return avaliacoes;
	// }
	
	public List<Avaliacao> listarSubcriterio(SubcriterioAvaliacao subcriterio) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubcriterio/" +subcriterio).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarProposta(Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarProposta/" +proposta).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}

	public List<Avaliacao> listarAvaliadorProposta(Usuario avaliador, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarAvaliadorProposta/" +avaliador+"/"+proposta).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	// public List<Avaliacao> listarCriterioProposta(CriterioAvaliacao criterio, Proposta proposta) {
	// 	List<Avaliacao> avaliacoes = 
	// 			ClientBuilder.newClient().
	// 			target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarCriterioProposta/" +criterio+"/"+proposta).
	// 			request(MediaType.APPLICATION_JSON).
	// 			header(HttpHeaders.AUTHORIZATION, authToken).get().
	// 			readEntity(new GenericType<List<Avaliacao>> () {});
		
	// 	return avaliacoes;
	// }
	
	public List<Avaliacao> listarSubcriterioProposta(SubcriterioAvaliacao subcriterio, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubcriterioProposta/" +subcriterio+"/"+proposta).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	@Override
	public Avaliacao create(Avaliacao obj) {
		Avaliacao avaliacao = 			
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL).
				queryParam("avaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				post(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	@Override
	public Avaliacao edit(Avaliacao obj) {
		Avaliacao avaliacao = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL).
				queryParam("avaliacao", obj).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + id).
				request(MediaType.APPLICATION_JSON).
				header(HttpHeaders.AUTHORIZATION, authToken).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}

}
