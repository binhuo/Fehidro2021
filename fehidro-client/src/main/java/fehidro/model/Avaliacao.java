package fehidro.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Avaliacao {
	 	
		private Long id;
		private Pontuacao nota;
		
	    private Usuario avaliador;
		
	    private Proposta proposta;
	    private SubcriterioAvaliacao subcriterio;
	    //TODO: Considerar reimplementar caso seja necessario para a desclassificacao por criteiro
//	    private CriterioAvaliacao criterio;

	    //Id
	    public Long getId()
	    {
	    	return id;
	    }
		public void setId(Long id) {
			this.id = id;
		}
		//Nota
		public Pontuacao getNota() {
			return nota;
		}
		public void setNota(Pontuacao nota) {
			this.nota = nota;
		}
		//Avaliador
		public Usuario getAvaliador() {
			return avaliador;
		}
		public void setAvaliador(Usuario avaliador) {
			this.avaliador = avaliador;
		}
		//Proposta
		public Proposta getProposta() {
			return proposta;
		}
		public void setProposta(Proposta proposta) {
			this.proposta = proposta;
		}
//		//Criterio
//		public CriterioAvaliacao getCriterio() {
//			return criterio;
//		}
//		public void setCriterio(CriterioAvaliacao criterio) {
//			this.criterio = criterio;
//		}
		//Subcriterio
		public SubcriterioAvaliacao getSubcriterio() {
			return subcriterio;
		}
		public void setSubcriterio(SubcriterioAvaliacao subcriterio) {
			this.subcriterio = subcriterio;
		}
		
}
