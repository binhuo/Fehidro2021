package fehidro.control;

import java.util.LinkedList;
import java.util.List;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;

public class ItemRelatorio {
	
	private Proposta proposta;
	private int soma;
	private List<Avaliacao> avaliacoes;
	private int classificacao;
	private boolean desclassificado;
	
	public ItemRelatorio() {
		avaliacoes = new LinkedList<Avaliacao>();
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
	
	//Desclassificacao
	public boolean isDesclassificado() {
		return desclassificado;
	}
	public void setDesclassificado(boolean desclassificado) {
		this.desclassificado = desclassificado;
	}

	//set(add) avaliacao
	public void addAvaliacao(Avaliacao a)
	{
		//Setando propriedades desse itemRelatorio caso n�o esteja setado
		if(this.proposta == null)
		{
			this.proposta = a.getProposta();
		}
		
		this.avaliacoes.add(a);//Adiciona a avaliacao � lista de avaliacoes
		if(a.getNota().isDesclassificavel())//Verifica se esta declassificado
		{
			this.desclassificado = true;
		}
		this.soma += a.getNota().getPontos();//Atualiza a soma das notas
	}
	
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	
	//Soma das avaliacoes
	public void soma() //Recalcula a soma
	{
		int s = 0;
		for(int i=0;i<avaliacoes.size();i++)//Navegue por tudo e soma tudo.
		{
			s += avaliacoes.get(i).getNota().getPontos();
		}
		this.soma = s;
	}
	public int getSoma() {
		return soma;
	}

	//Proposta
	public Proposta getProposta() {
		return proposta;
	}
//	public void setProposta(Proposta proposta) {
//		this.proposta = proposta;
//	}
	
	

	

	
	
	

	
}
