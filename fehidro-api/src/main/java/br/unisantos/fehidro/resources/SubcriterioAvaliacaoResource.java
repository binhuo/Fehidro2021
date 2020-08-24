package br.unisantos.fehidro.resources;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.unisantos.fehidro.model.Pontuacao;
//import br.unisantos.fehidro.model.Avaliacao;
import br.unisantos.fehidro.model.SubcriterioAvaliacao;
//import br.unisantos.fehidro.model.dao.AvaliacaoDAO;
import br.unisantos.fehidro.model.dao.SubcriterioAvaliacaoDAO;
import br.unisantos.fehidro.util.jpa.JPAEntityManager;
import fehidro.model.dto.SubcriterioExibicaoDTO;

@Path("/subcriterioAvaliacao")
public class SubcriterioAvaliacaoResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();
		List<SubcriterioAvaliacao> subcriterios = dao.listar();
		return Response.ok(subcriterios).build();
	}
	
	@Path("/dto")
	@GET
	@Produces("application/json")
	public Response getDTO() {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<SubcriterioExibicaoDTO> query = manager.createQuery("select new " + 
		"fehidro.model.dto.SubcriterioExibicaoDTO("+ 
				"c.id, c.titulo, c.numero, c.letra) from SubcriterioAvaliacao c",SubcriterioExibicaoDTO.class);
		List<SubcriterioExibicaoDTO> subcriterios = query.getResultList();
		return Response.ok(subcriterios).build();
	}
	
	@Path("/dto/{id}")
	@GET
	@Produces("application/json")
	public Response getDTO(@PathParam("id") Long id) {
		EntityManager manager = JPAEntityManager.getEntityManager();
		TypedQuery<SubcriterioExibicaoDTO> query = manager.createQuery("select new " + 
		"fehidro.model.dto.SubcriterioExibicaoDTO("+ 
				"c.id, c.titulo, c.numero, c.letra) from CriterioAvaliacao a join a.subcriterios c where a.id = "+id,SubcriterioExibicaoDTO.class);
		List<SubcriterioExibicaoDTO> subcriterios = query.getResultList();
		return Response.ok(subcriterios).build();
	}
	
	@Path("/pontuacao/{id}")
	@GET
	@Produces("application/json")
	public Response getPontuacoes(@PathParam("id") Long id) {
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();
		List<Pontuacao> pontuacoes = dao.obterPontuacaoPorSubcriterio(id);
		return Response.ok(pontuacoes).build();
	}
	
	
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();		
		SubcriterioAvaliacao subcriterio = dao.obter(id);
		if (subcriterio != null) {
			return Response.ok(subcriterio).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	
	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(SubcriterioAvaliacao subcriterio) {
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();
		dao.cadastrar(subcriterio);
		return Response.ok(subcriterio).build();
	}
	
	@PUT
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(SubcriterioAvaliacao subcriterio) {		
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();
		dao.atualizar(subcriterio);
		return Response.ok(subcriterio).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		SubcriterioAvaliacaoDAO dao = new SubcriterioAvaliacaoDAO();
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
	
}
