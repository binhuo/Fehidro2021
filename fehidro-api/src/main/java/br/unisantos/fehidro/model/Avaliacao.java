package br.unisantos.fehidro.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//Classe que mantem a nota dada por um avaliador

@Table(name = "tb_avaliacao")
@Entity
@NamedQueries({ 
	@NamedQuery(name = "Avaliacao.listarTodos", query = "select a from Avaliacao a"),
	@NamedQuery(name = "Avaliacao.consultarPorId", query = "select a from Avaliacao a where a.id=?1"),
	@NamedQuery(name = "Avaliacao.listarAvaliador", query = "select a from Avaliacao a where a.avaliador = ?1"),
	@NamedQuery(name = "Avaliacao.listarCriterio", query = "select a from Avaliacao a where a.criterio = ?1"),
	@NamedQuery(name = "Avaliacao.listarSubcriterio", query = "select a from Avaliacao a where a.subcriterio = ?1"),
	@NamedQuery(name = "Avaliacao.listarProposta", query = "select a from Avaliacao a where a.proposta = ?1"),
	@NamedQuery(name = "Avaliacao.listarAvaliadorProposta", query = "select a from Avaliacao a where a.proposta = ?1 and a.avaliador =? 2"),
	@NamedQuery(name = "Avaliacao.listarCriterioProposta", query = "select a from Avaliacao a where a.proposta = ?1 and a.criterio =? 2"),
	@NamedQuery(name = "Avaliacao.listarSubcriteiroProposta", query = "select a from Avaliacao a where a.proposta = ?1 and a.subcriterio =? 2"),
})
public class Avaliacao extends AbstractEntity {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
    @JoinColumn(name = "nota_avaliacao_id")
    private Pontuacao nota;
	
    @ManyToOne
    @JoinColumn(name = "usuario_avaliacao_id")
    private Usuario avaliador;
    
    @ManyToOne
    @JoinColumn(name = "proposta_avaliacao_id")
    private Proposta proposta;
    
    @ManyToOne
    @JoinColumn(name = "subcriterio_avaliacao_id")
    private SubcriterioAvaliacao subcriterio;
    
    @ManyToOne
    @JoinColumn(name = "criterio_avaliacao_id")
    private CriterioAvaliacao criterio;
    
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "subPDC_avaliacao_id")
//    private SubPDC subpdc;
//    
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "pdc_avaliacao_id")
//    private PDC pdc;
    
    
	public Avaliacao() {
		
	}	

	public Pontuacao getNota() {
		return nota;
	}

	public void setNota(Pontuacao nota) {
		this.nota = nota;
	}

	public Usuario getAvaliador() {
		return avaliador;
	}

	public void setAvaliador(Usuario avaliador) {
		this.avaliador = avaliador;
	}


	public Proposta getProposta() {
		return proposta;
	}


	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}


	public SubcriterioAvaliacao getSubcriterio() {
		return subcriterio;
	}


	public void setSubcriterio(SubcriterioAvaliacao subcriterio) {
		this.subcriterio = subcriterio;
	}


	public CriterioAvaliacao getCriterio() {
		return criterio;
	}


	public void setCriterio(CriterioAvaliacao criterio) {
		this.criterio = criterio;
	}


//	public SubPDC getSubpdc() {
//		return subpdc;
//	}
//
//
//	public void setSubpdc(SubPDC subpdc) {
//		this.subpdc = subpdc;
//	}
//
//
//	public PDC getPdc() {
//		return pdc;
//	}
//
//
//	public void setPdc(PDC pdc) {
//		this.pdc = pdc;
//	}
//    
    
    
    
	
}
