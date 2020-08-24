package br.unisantos.fehidro.resources;

import java.util.List;

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

import br.unisantos.fehidro.model.Avaliacao;
import br.unisantos.fehidro.model.dao.AvaliacaoDAO;


@Path("/avaliacao")
public class AvaliacaoResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		AvaliacaoDAO dao = new AvaliacaoDAO();
		List<Avaliacao> avaliacoes = dao.listar();
		if(avaliacoes == null)
		{
			Response.status(Response.Status.NO_CONTENT);
		}
		if(avaliacoes.size() <=0)
		{
			Response.status(Response.Status.NO_CONTENT);
		}
		return Response.ok(avaliacoes).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		AvaliacaoDAO dao = new AvaliacaoDAO();		
		Avaliacao usuario = dao.obter(id);
		if (usuario != null) {
			return Response.ok(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Avaliacao avaliacao) {
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.cadastrar(avaliacao);
		return Response.ok(avaliacao).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Avaliacao avaliacao) {	
		AvaliacaoDAO dao = new AvaliacaoDAO();
		dao.atualizar(avaliacao);
		return Response.ok(avaliacao).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		AvaliacaoDAO dao = new AvaliacaoDAO();
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}