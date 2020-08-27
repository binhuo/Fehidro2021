package fehidro.api.model;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Table(name = "tb_subpdc")
@Entity
public class SubPDC extends AbstractEntity {
	
	@Column(name="nr_subpdc")
	private int numero;
	
	@Column(name="nm_titulo", length = 50)
	private String titulo;
	
	@ManyToOne
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private PDC pdc;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonIgnore
	@JoinColumn(name = "subpdc_id")
	private List<Proposta> propostas;

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public PDC getPdc() {
		return pdc;
	}

	public void setPdc(PDC pdc) {
		this.pdc = pdc;
	}
}
