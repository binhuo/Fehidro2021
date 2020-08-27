package br.unisantos.fehidro.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Table(name = "tb_proposta")
@Entity
@NamedQueries({ @NamedQuery(name = "Proposta.listarTodos", query = "select p from Proposta p join fetch p.instituicao order by p.nomeProjeto"),
		@NamedQuery(name = "Proposta.consultarPorId", query = "select p from Proposta p where p.id=?1"),
		@NamedQuery(name = "Proposta.listarPorInstituicao", query = "select p from Proposta p join fetch p.instituicao i where i.id=?1") })

public class Proposta extends AbstractEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = "nm_projeto")
	private String nomeProjeto;
		
	@Column(name = "nm_arquivoTermoReferencia")     
	private String nomeArquivoTermoReferencia;

	@Column(name = "nm_arquivoCronogramaFisicoFinanceiro")      
	private String nomeArquivoCronogramaFisicoFinanceiro;

	@Column(name = "nm_arquivoPlanilhaOrcamento")       
	private String nomeArquivoPlanilhaOrcamento;

	@Column(name = "nm_arquivoFichaResumo")     
	private String nomeArquivoFichaResumo;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity=TipoProposta.class)
	@JoinTable(name = "tb_proposta_tb_tipoproposta", 
 		joinColumns = {@JoinColumn(name = "proposta_id", referencedColumnName = "id")}, 
 		inverseJoinColumns = {@JoinColumn(name = "tipoproposta_id", referencedColumnName = "id") })
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<TipoProposta> tiposProposta;
	
	@ManyToOne
	private Instituicao instituicao;

	@ManyToOne
	private SubPDC subPDC;
	
	
	public Proposta() {
		super();
	}

	
	
	public String getNomeProjeto() {
		return nomeProjeto;
	}


	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}


	public Instituicao getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}


	public SubPDC getSubPDC() {
		return subPDC;
	}


	public void setSubPDC(SubPDC subPDC) {
		this.subPDC = subPDC;
	}


	public List<TipoProposta> getTiposProposta() {
		return tiposProposta;
	}


	public void setTiposProposta(List<TipoProposta> tiposProposta) {
		this.tiposProposta = tiposProposta;
	}

	public void addTiposProposta(TipoProposta tipoProposta) {
		if(this.tiposProposta.contains(tipoProposta))
			return;
		
		this.tiposProposta.add(tipoProposta);
		tipoProposta.addProposta(this);
	} 
	
	public void removeTiposProposta(TipoProposta tipoProposta) {
		if(!this.tiposProposta.contains(tipoProposta))
			return;
		
		this.tiposProposta.remove(tipoProposta);
		tipoProposta.removeProposta(this);
	} 

	public String getNomeArquivoTermoReferencia() {
		return nomeArquivoTermoReferencia;
	}


	public void setNomeArquivoTermoReferencia(String nomeArquivoTermoReferencia) {
		this.nomeArquivoTermoReferencia = nomeArquivoTermoReferencia;
	}


	public String getNomeArquivoCronogramaFisicoFinanceiro() {
		return nomeArquivoCronogramaFisicoFinanceiro;
	}


	public void setNomeArquivoCronogramaFisicoFinanceiro(String nomeArquivoCronogramaFisicoFinanceiro) {
		this.nomeArquivoCronogramaFisicoFinanceiro = nomeArquivoCronogramaFisicoFinanceiro;
	}


	public String getNomeArquivoPlanilhaOrcamento() {
		return nomeArquivoPlanilhaOrcamento;
	}


	public void setNomeArquivoPlanilhaOrcamento(String nomeArquivoPlanilhaOrcamento) {
		this.nomeArquivoPlanilhaOrcamento = nomeArquivoPlanilhaOrcamento;
	}


	public String getNomeArquivoFichaResumo() {
		return nomeArquivoFichaResumo;
	}


	public void setNomeArquivoFichaResumo(String nomeArquivoFichaResumo) {
		this.nomeArquivoFichaResumo = nomeArquivoFichaResumo;
	}	

}
