package br.unisantos.fehidro.model.dao;

import java.util.List;

import br.unisantos.fehidro.model.Avaliacao;
import br.unisantos.fehidro.model.CriterioAvaliacao;
//import br.unisantos.fehidro.model.PDC;
import br.unisantos.fehidro.model.Proposta;
//import br.unisantos.fehidro.model.SubPDC;
import br.unisantos.fehidro.model.SubcriterioAvaliacao;
import br.unisantos.fehidro.model.Usuario;

public class AvaliacaoDAO extends DAOFactory<Avaliacao> {


	public AvaliacaoDAO() {
		super(Avaliacao.class);
	}
	
	public void cadastrar(Avaliacao avaliacao) {
		adicionar(avaliacao);
	}
	
	public void atualizar(Avaliacao avaliacao) {
		alterar(avaliacao);//FIXME?
	}

	public Avaliacao obter(long id) {
		return consultarGenerico("Avaliacao.consultarPorId", id);
	}

	//Listar todos
	public List<Avaliacao> listar() {
		return listarGenerico("Avaliacao.listarTodos");
	}
	
	//========Listar especifico=========
	
//	public List<Avaliacao> listarPDC(PDC pdc)
//	{
//		return listarGenerico("Avaliacao.listarPDC", pdc);
//	}
//	
//	public List<Avaliacao> listarSubPDC(SubPDC subpdc)
//	{
//		return listarGenerico("Avaliacao.listarSubPDC",subpdc);
//	}
	
	public List<Avaliacao> listarAvaliador(Usuario avaliador)
	{
		return listarGenerico("Avaliacao.listarAvaliador", avaliador);
	}
	
	public List<Avaliacao> listarCriterio(CriterioAvaliacao criterio)
	{
		return listarGenerico("Avaliacao.listarCriterio", criterio);
	}
	
	public List<Avaliacao> listarSubcriterio(SubcriterioAvaliacao subcriterio)
	{
		return listarGenerico("Avaliacao.listarSubcriterio", subcriterio);
	}
	
	public List<Avaliacao> listarProposta(Proposta proposta)
	{
		return listarGenerico("Avaliacao.listarProposta", proposta);
	}
	
	public List<Avaliacao> listarAvaliadorProposta(Usuario avaliador, Proposta proposta)
	{
		return listarGenerico("Avaliacao.listarAvaliadorProposta",avaliador,proposta);
	}
	
	public List<Avaliacao> listarCriterioProposta(CriterioAvaliacao criterio, Proposta proposta)
	{
		return listarGenerico("Avaliacao.listarCriterioProposta",criterio,proposta);
	}
	
	public List<Avaliacao> listarSubcriteiroProposta(SubcriterioAvaliacao subcriterio, Proposta proposta)
	{
		return listarGenerico("Avaliacao.listarCriterioProposta",subcriterio,proposta);
	}
	
	
	
}
