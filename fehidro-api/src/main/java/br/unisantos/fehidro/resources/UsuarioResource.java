package br.unisantos.fehidro.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

import br.unisantos.fehidro.model.dao.UsuarioDAO;
import br.unisantos.fehidro.model.Usuario;
import br.unisantos.fehidro.util.email.EmailUtil;
import br.unisantos.fehidro.util.password.Password;


@Path("/usuario")
public class UsuarioResource {

	@GET
	@Produces("application/json")
	public Response getAll() {
		UsuarioDAO dao = new UsuarioDAO();
		List<Usuario> usuarios = dao.listar();
		return Response.ok(usuarios).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response get(@PathParam("id") Long id) {
		UsuarioDAO dao = new UsuarioDAO();		
		Usuario usuario = dao.obter(id);
		if (usuario != null) {
			return Response.ok(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/obterPorLogin/{login}")
	@GET
	@Produces("application/json")
	public Response obterPorLogin(@PathParam("login") String login) {
		UsuarioDAO dao = new UsuarioDAO();		
		Usuario usuario = dao.obterPorLogin(login);
		if (usuario != null) {
			return Response.ok(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/obterPorCPF/{CPF}")
	@GET
	@Produces("application/json")
	public Response obterPorCPF(@PathParam("CPF") Long CPF) {
		UsuarioDAO dao = new UsuarioDAO();		
		Usuario usuario = dao.obterPorCPF(CPF);
		if (usuario != null) {
			return Response.ok(usuario).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@Path("/obterPorPerfilAcesso/{perfilacesso}")
	@GET
	@Produces("application/json")
	public Response obterPorPerfilAcesso(@PathParam("perfilacesso") Long perfilacesso) {
		UsuarioDAO dao = new UsuarioDAO();		
		List<Usuario> usuarios = dao.obterPorPerfilAcesso(perfilacesso);
		if (usuarios != null) {
			return Response.ok(usuarios).build();
		} else {
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}

	@POST
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response add(Usuario usuario) throws Exception {
		UsuarioDAO dao = new UsuarioDAO();
		usuario.setLogin();
		usuario.setAtivo();	
		String senha = Password.generateRandomPassword(10);
		usuario.setSenha(Password.hashPassword(senha));
		EmailUtil.sendMail(usuario.getEmail(), usuario.getNome(), usuario.getLogin(), senha);
		dao.cadastrar(usuario);
		return Response.ok(usuario).build();
	}

	@PUT
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(Usuario usuario) {		
		UsuarioDAO dao = new UsuarioDAO();
		dao.atualizar(usuario);
		return Response.ok(usuario).build();
	}

	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public Response delete(@PathParam("id") Long id) {
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.excluir(id)) {
			return Response.ok().build();
		}
		return Response.status(Response.Status.NOT_FOUND).build();
	}
}
