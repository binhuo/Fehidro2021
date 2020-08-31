package fehidro.api.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_meta")
@Entity
public class Meta extends AbstractEntity {
	
	@Column(name = "ds_meta")
	private String descricao;
	
	@Column(name = "ds_acao")
	private String acao;
	
	@Column(name = "vl_recursofinanceiro")
	private BigDecimal valor;
	
	@ManyToOne
	@JsonIgnore
	private SubPDC subpdc;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public SubPDC getSubpdc() {
		return subpdc;
	}

	public void setSubpdc(SubPDC subpdc) {
		this.subpdc = subpdc;
	}

}
