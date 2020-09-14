package fehidro.api.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fehidro.model.dto.avaliacao.CadastroAvaliacaoDTO;

@Table(name = "tb_avaliacao")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Avaliacao extends AbstractEntity {
	
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
    
  //TODO: Reimplementar caso seja necessario para a desclassificacao por criteiro
//    @ManyToOne
//    @JoinColumn(name = "criterio_avaliacao_id")
//    private CriterioAvaliacao criterio;
    
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "subPDC_avaliacao_id")
//    private SubPDC subpdc;
//    
//    @ManyToOne(cascade=CascadeType.ALL)
//    @JoinColumn(name = "pdc_avaliacao_id")
//    private PDC pdc;
	
	public Avaliacao() {
	}
	
	public Avaliacao(CadastroAvaliacaoDTO dto) {
		if(dto != null) {
			this.setId(dto.getId());
			this.nota = dto.getNota();
			this.avaliador = new Usuario(dto.getAvaliador());
			this.proposta = dto.getProposta();
			this.subcriterio = dto.getSubcriterio();
//			this.criterio = dto.getCriterio();
		}
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

//	public CriterioAvaliacao getCriterio() {
//		return criterio;
//	}
//
//	public void setCriterio(CriterioAvaliacao criterio) {
//		this.criterio = criterio;
//	}
	
	

}