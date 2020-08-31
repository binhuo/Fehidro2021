package fehidro.control;

import java.util.LinkedList;
import java.util.List;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;

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
	private boolean desclassificado;
	
	public ItemRelatorio() {
		avaliacoes = new LinkedList<Avaliacao>();
		classificacaoSubpdc = 0;
		classificacao = 0;
		soma = 0;
		desclassificado = false;
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
		
		checkDesclassificacao(a);
		
		//Atualiza a soma das notas
		this.soma += a.getNota().getPontos();
	}
	
	/**
	 * Verifica se esta desclassificado e retorna o valor da propriedade 'desclassificado' desse objeto. 
	 * @param a - Avaliacao a ser adicionada
	 */
	protected boolean checkDesclassificacao(Avaliacao a) {
		
		//Desclassificacao automatica por subcriteiro
		if(a.getNota().isDesclassificavel()) {	//TODO: considerar substituir por um for() e remover o parametro - menos performance porem pode prevenir alguns erros
			this.desclassificado = true;
		}
		//TODO: outros criterios de avaliacao
		
		return this.desclassificado;
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	////Soma das avaliacoes
	//Recalcula a soma
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
