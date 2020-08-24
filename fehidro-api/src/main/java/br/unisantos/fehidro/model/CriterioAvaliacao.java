package br.unisantos.fehidro.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import com.fasterxml.jackson.annotation.JsonInclude;

@Table(name = "tb_criterioavaliacao")
@Entity
@NamedQueries({
	@NamedQuery(name = "CriterioAvaliacao.listarTodos",
			    query = "select c from CriterioAvaliacao c"),
	
	@NamedQuery(name = "CriterioAvaliacao.consultarPorId",
    			query = "select c from CriterioAvaliacao c where c.id=?1"),
	
	@NamedQuery(name = "CriterioAvaliacao.obterSubcriterios", 
	query = "select s from CriterioAvaliacao c join c.subcriterios s where c.id=?1"),
	
	@NamedQuery(name = "CriterioAvaliacao.obterPontuacoesPorCriterio", 
				query = "select p from CriterioAvaliacao c join c.pontuacoes p where c.id =?1"),
})
public class CriterioAvaliacao extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	@Column(name = "nm_titulo")
	private String titulo;

	@Column(name = "nr_criterio")
	private Integer numero;
		
	@OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
	@JoinColumn(name = "fk_criterioavaliacao_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<Pontuacao> pontuacoes;
	
	@OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})  //salva em cascata, altera pai e filho em cascata, exclui em cascata
	@JoinColumn(name = "fk_criterioavaliacao_id")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<SubcriterioAvaliacao> subcriterios;

	public CriterioAvaliacao() {
		super();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public List<Pontuacao> getPontuacoes() {
		return pontuacoes;
	}

	public void setPontuacoes(List<Pontuacao> pontuacoes) {
		this.pontuacoes = pontuacoes;
	}
	
	public void addPontuacoes(Pontuacao pontuacao) {
		if (this.pontuacoes.contains(pontuacao))
			return;
		
		this.pontuacoes.add(pontuacao);
		pontuacao.setCriterio(this);
	}
	
	public void removePontuacoes(Pontuacao pontuacao) {
		if (!this.pontuacoes.contains(pontuacao))
			return;
		
		this.pontuacoes.remove(pontuacao);
		pontuacao.setCriterio(null);
	}

	public List<SubcriterioAvaliacao> getSubcriterios() {
		return new ArrayList<SubcriterioAvaliacao>(subcriterios);
	}

	public void setSubcriterios(List<SubcriterioAvaliacao> subcriterios) {
		this.subcriterios = subcriterios;
	}
	
	public void addSubcriterios(SubcriterioAvaliacao sub) {
		if (this.subcriterios.contains(sub))
			return;
		
		this.subcriterios.add(sub);
		sub.setCriterio(this);
	}
	
	public void removeSubcriterios(SubcriterioAvaliacao sub) {
		if (!this.subcriterios.contains(sub))
			return;
		
		this.subcriterios.remove(sub);
		sub.setCriterio(null);
	}
	
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
}
