package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;

import fehidro.model.Avaliacao;
import fehidro.model.CriterioAvaliacao;
import fehidro.model.PDC;
import fehidro.model.Proposta;
import fehidro.model.SubPDC;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;


public class AvaliacaoRESTClient implements RESTClientInterface<Avaliacao>{

	@Override
	public List<Avaliacao> findAll() {
		//List<Avaliacao> avaliacoes = ClientBuilder.newClient().target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL).request(MediaType.APPLICATION_JSON).get().readEntity(new GenericType<List<Avaliacao>> () {});

		//System.out.println("==================================");
		Client c = ClientBuilder.newClient();
		//System.out.println(c.toString());
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL);
		//System.out.println(t.toString());
		Builder b = t.request(MediaType.APPLICATION_JSON);
		//System.out.println(b.toString());
		Response r = b.get();
		//System.out.println(r.toString());
		List<Avaliacao> out = r.readEntity(new GenericType<List<Avaliacao>> () {});
		//System.out.println("<==================================>");
		return out;
		//return avaliacoes;
	}

	@Override
	public Avaliacao find(Long id) {
		Avaliacao avaliacao = ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + id).
				request(MediaType.APPLICATION_JSON).get()
				.readEntity(Avaliacao.class);
		
		return avaliacao;
	}

//	public List<Avaliacao> listarPDC(PDC pdc) {
//		List<Avaliacao> avaliacoes = 
//				ClientBuilder.newClient().
//				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarPDC/" +pdc).
//				request(MediaType.APPLICATION_JSON).get().
//				readEntity(new GenericType<List<Avaliacao>> () {});
//		
//		return avaliacoes;
//	}
	
	//target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubPDC/" + subpdc).
	public List<Avaliacao> listarSubPDC(SubPDC subpdc) {
//		List<Avaliacao> avaliacoes = 
//				ClientBuilder.newClient().
//				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubPDC/" + subpdc.getId()).
//				request(MediaType.APPLICATION_JSON).get().
//				readEntity(new GenericType< List<Avaliacao> > () {});
		
		Client c = ClientBuilder.newClient();
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubPDC/" + subpdc.getId());
		Builder b = t.request(MediaType.APPLICATION_JSON);
		Response r = b.get();
		List<Avaliacao> out = r.readEntity(new GenericType<List<Avaliacao>> () {});
		System.out.println("out REST listarSubPDC() size = "+out.size());
		return out;
		
		
		//return avaliacoes;
	}
	
	public List<Avaliacao> listarAvaliador(Usuario avaliador) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarAvaliador/" +avaliador).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterio(CriterioAvaliacao criterio) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarCriterio/" +criterio).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterio(SubcriterioAvaliacao subcriterio) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubcriterio/" +subcriterio).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarProposta(Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarProposta/" +proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}

	public List<Avaliacao> listarAvaliadorProposta(Usuario avaliador, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarAvaliadorProposta/" +avaliador+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarCriterioProposta(CriterioAvaliacao criterio, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarCriterioProposta/" +criterio+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
				readEntity(new GenericType<List<Avaliacao>> () {});
		
		return avaliacoes;
	}
	
	public List<Avaliacao> listarSubcriterioProposta(SubcriterioAvaliacao subcriterio, Proposta proposta) {
		List<Avaliacao> avaliacoes = 
				ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + "listarSubcriterioProposta/" +subcriterio+"/"+proposta).
				request(MediaType.APPLICATION_JSON).get().
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
				put(Entity.entity(obj, MediaType.APPLICATION_JSON)).
				readEntity(Avaliacao.class);
		
		return avaliacao;
	}

	@Override
	public boolean delete(Long id) {
		return 	ClientBuilder.newClient().
				target(REST_WEBSERVICE_URL + REST_AVALIACAO_URL + id).
				request(MediaType.APPLICATION_JSON).
				delete().getStatus() 
				== Response.Status.OK.getStatusCode();
	}

}
