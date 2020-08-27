package fehidro.model;

import java.util.List;

public class SubcriterioAvaliacao {
	private Long id;
	private String titulo;
	private int numero;
	private char letra;
	private List<Pontuacao> pontuacoes;
	private List<TipoProposta> tiposProposta;
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
	public List<TipoProposta> getTiposProposta() {
		return tiposProposta;
	}
	public void setTiposProposta(List<TipoProposta> tiposProposta) {
		this.tiposProposta = tiposProposta;
	}
	public char getLetra() {
		return letra;
	}
	public void setLetra(char letra) {
		this.letra = letra;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}
	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}
}
