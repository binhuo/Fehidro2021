package fehidro.model;

import java.math.BigDecimal;

public class Meta {
	private long id;
	
	private String descricao;
	
	private String acao;
	
	private BigDecimal valor;
	
	private SubPDC subpdc;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

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