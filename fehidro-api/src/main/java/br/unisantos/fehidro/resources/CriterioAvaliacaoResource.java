package br.unisantos.fehidro.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.unisantos.fehidro.model.dao.CriterioAvaliacaoDAO;
import br.unisantos.fehidro.model.CriterioAvaliacao;
import br.unisantos.fehidro.model.Pontuacao;
import br.unisantos.fehidro.model.SubcriterioAvaliacao;

@Path("/criterioAvaliacao")
public class CriterioAvaliacaoResource {
	
	@GET
	@Produces("application/json")
	public Response getAll() {
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
		List<CriterioAvaliacao> criterios = dao.listar();
		for(CriterioAvaliacao c : criterios) {
			c.setPontuacoes(new ArrayList<Pontuacao>());
			c.setSubcriterios(new ArrayList<SubcriterioAvaliacao>());
		}
		return Response.ok(criterios).build();
	}
	
	@Path("subcriterios/{id}")
	@GET
	@Produces("application/json")
	public Response obterSubcriterios(@PathParam("id") Long id) {
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
		List<SubcriterioAvaliacao> subcriterios = dao.obterSubcriterios(id);
		for(SubcriterioAvaliacao c : subcriterios) {
			c.setPontuacoes(new ArrayList<Pontuacao>());
		}
		return Response.ok(subcriterios).build();
	}
	
//	@Path("/completo")
//	@GET
//	@Produces("application/json")
//	public Response getCompleto() {
//		System.out.println("Resource - getCompleto()");
//		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
//		List<CriterioAvaliacao> criterios = dao.listarCriterioCompleto();
//		return Response.ok(criterios).build();
//	}
	
	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();		
		CriterioAvaliacao criterio = dao.obter(id);
		if (criterio != null) {
			return Response.ok(criterio).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(CriterioAvaliacao criterio) {
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
		dao.cadastrar(criterio);
		return Response.ok(criterio).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(CriterioAvaliacao criterio) {		
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
		dao.atualizar(criterio);
		return Response.ok(criterio).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		CriterioAvaliacaoDAO dao = new CriterioAvaliacaoDAO();
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
