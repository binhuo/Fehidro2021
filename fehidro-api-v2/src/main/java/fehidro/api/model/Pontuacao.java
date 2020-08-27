package fehidro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_pontuacao")
@Entity
public class Pontuacao extends AbstractEntity {
	@Column(name = "nm_titulo")
	private String titulo;
	
	@Column(name = "nr_ponto")
	private int pontos;
	
	@Column(name = "ic_desclassificavel")
	private boolean desclassificavel;

	@ManyToOne
	@JsonIgnore
	private CriterioAvaliacao criterio;
	
	@ManyToOne
	@JsonIgnore
	private SubcriterioAvaliacao subcriterio;
	
	public Pontuacao() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getPontos() {
		return pontos;
	}

	public void setPontos(int pontos) {
		this.pontos = pontos;
	}

	public boolean isDesclassificavel() {
		return desclassificavel;
	}

	public void setDesclassificavel(boolean desclassificavel) {
		this.desclassificavel = desclassificavel;
	}

	public CriterioAvaliacao getCriterio() {
		return criterio;
	}

	public void setCriterio(CriterioAvaliacao criterio) {
		this.criterio = criterio;
	}

	public SubcriterioAvaliacao getSubcriterio() {
		return subcriterio;
	}

	public void setSubcriterio(SubcriterioAvaliacao subcriterio) {
		this.subcriterio = subcriterio;
	}

	
	
}
