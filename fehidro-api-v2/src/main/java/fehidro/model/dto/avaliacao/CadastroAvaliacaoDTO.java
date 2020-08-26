package fehidro.model.dto.avaliacao;

import fehidro.api.model.Avaliacao;
import fehidro.api.model.CriterioAvaliacao;
import fehidro.api.model.Pontuacao;
import fehidro.api.model.Proposta;
import fehidro.api.model.SubcriterioAvaliacao;
import fehidro.api.model.Usuario;

public class CadastroAvaliacaoDTO {

	private long id;
    private Pontuacao nota;
    private Usuario avaliador;
    private Proposta proposta;
    private SubcriterioAvaliacao subcriterio;
    private CriterioAvaliacao criterio;

	public CadastroAvaliacaoDTO() {

	}

	public CadastroAvaliacaoDTO(Avaliacao model) {
		if (model != null) {
			this.setId(model.getId());
			this.nota = model.getNota();
			this.avaliador = model.getAvaliador();
			this.proposta = model.getProposta();
			this.subcriterio = model.getSubcriterio();
			this.criterio = model.getCriterio();
		}
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id; 
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

	

}
