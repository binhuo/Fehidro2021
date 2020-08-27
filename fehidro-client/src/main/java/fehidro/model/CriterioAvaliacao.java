package fehidro.model;

import java.util.List;

public class CriterioAvaliacao {
	private Long id;
	private Integer numero;
	private String titulo;
	private List<Pontuacao> pontuacoes;
	private List<SubcriterioAvaliacao> subcriterios;
	private PerfilAcesso perfilAcesso;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public List<SubcriterioAvaliacao> getSubcriterios() {
		return subcriterios;
	}
	public void setSubcriterios(List<SubcriterioAvaliacao> subcriterios) {
		this.subcriterios = subcriterios;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}
	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}
}
