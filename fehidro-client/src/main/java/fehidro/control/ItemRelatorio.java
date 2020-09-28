package fehidro.control;

import java.util.LinkedList;
import java.util.List;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;
import fehidro.model.SubcriterioAvaliacao;

/**
 * Classe usada para representar um item no relatorio (uma proposta)
 *
 */
public class ItemRelatorio {
	
	private Proposta proposta;
	private int soma; //Soma das notas
	private List<Avaliacao> avaliacoes;
	private int classificacaoSubpdc; //A classificacao (rank) no subPDC
	private int classificacao; //A classificacao geral (rank)
	/**
	 * Se esse ItemRelatorio eh desclassificado (true) ou nao (false). Eu recomendo NUNCA atribuir o valor false a esse depois do construtor.
	 */
	private boolean desclassificado = false;
	private String stringDesclassificado;
	
	public ItemRelatorio() {
		desclassificado = false; //Essa deve ser a unica instancia onde o valor desclassificado deve ser atribuido manualmente para false.
		avaliacoes = new LinkedList<Avaliacao>();
		classificacaoSubpdc = 0;
		classificacao = 0;
		soma = 0;
	}
	
	//Classificacao (ranking)
	public int getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(int classificacao) {
		this.classificacao = classificacao;
	}
	
	//Classificacao por subpdc (ranking)
	public int getClassificacaoSubpdc() {
		return classificacaoSubpdc;
	}
	public void setClassificacaoSubpdc(int classificacao) {
		this.classificacaoSubpdc = classificacao;
	}
	
	//Desclassificacao
	public boolean isDesclassificado() {
		return desclassificado;
	}
	/**
	 * Seta esse ItemRelatorio como desclassificado
	 * @return
	 */
	public void desclassificar() {
		this.desclassificado = true;
	}
	
	public String getStringDesclassificado() {
		if(desclassificado) {
			return "Desclassificado";
		}
		else {
			return "Classificado";
		}
	}

	/**
	 * Adiciona uma avaliacao a este item de relatorio
	 * @param a - A avaliacao a ser adicionada
	 */
	public void addAvaliacao(Avaliacao a)
	{
		//Setando propriedades desse itemRelatorio caso nao esteja setado
		if(this.proposta == null)
		{
			this.proposta = a.getProposta();
		}
		
		//Adiciona a avaliacao
		this.avaliacoes.add(a);//Adiciona a avaliacao a lista de avaliacoes
		
		//Atualiza a soma das notas
		this.soma += a.getNota().getPontos();
		
		checkDesclassificacao(a);
	}
	
	/**
	 * Verifica se esta desclassificado e retorna o valor da propriedade 'desclassificado' desse objeto. 
	 * @param a - Avaliacao a ser adicionada
	 */
	protected boolean checkDesclassificacao(Avaliacao a) {
		
		//--- DESCLASSIFICACAO ---
		//Desclassificacao automatica por subcriteiro
		if(a.getNota().isDesclassificavel()) {
			this.desclassificar();
		}
		
		//Desclassificacao por nota total < 120
		if(this.soma < 120) {
			this.desclassificar();
		}
		
		//Desclassificacao por subcriterio 1A a 1J (RN06)
		int cDesclassificacaoUm = 0;
		for(int i=0; i<this.avaliacoes.size();i++) {
			SubcriterioAvaliacao s = this.avaliacoes.get(i).getSubcriterio();
			if( s.getNumero() == 1 ) {
				if( s.getLetra() >= 'a' && s.getLetra() <= 'j') {
					if(this.avaliacoes.get(i).getNota().getPontos() == 0) {
						cDesclassificacaoUm++;
					}
				}
			}
		}
		if(cDesclassificacaoUm >= 3) {
			this.desclassificar();
		}
		
		return this.desclassificado;
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	/**
	 * Recalcula a soma das notas nesse ItemRelatorio
	 */
	public void soma() 
	{
		int s = 0;
		for(int i=0;i<avaliacoes.size();i++){
			s += avaliacoes.get(i).getNota().getPontos();
		}
		//Atualiza a soma
		this.soma = s;
	}
	public int getSoma() {
		return soma;
	}

	//Proposta
	public Proposta getProposta() {
		return proposta;
	}
	
	

	

	
	
	

	
}
