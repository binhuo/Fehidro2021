package fehidro.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SubPDC {
	private Long id;
	private String titulo;
	private int numero;
	private PDC pdc;
	private List<Meta> metas;
	
	@JsonIgnore
	private int ordemListagem;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void settitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public PDC getPdc() {
		return pdc;
	}
	public void setPdc(PDC pdc) {
		this.pdc = pdc;
	}
	public List<Meta> getMetas() {
		return metas;
	}
	public void setMetas(List<Meta> metas) {
		this.metas = metas;
	}
	public int getOrdemListagem() {
		return ordemListagem;
	}
	public void setOrdemListagem(int ordemListagem) {
		this.ordemListagem = ordemListagem;
	}	
}
