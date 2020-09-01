package fehidro.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import fehidro.control.ItemRelatorio;

//TODO: REFATORAR
public class Relatorio  {
	
	protected HashMap<Long, ItemRelatorio> itensRelatorio;
	//TODO: Substituir por Sets para evitar repeticao de calculo de classificacao - vide setItensRelatorio()
	protected List<Long> idsPropostas; //Lista usada para auxiliar na manipulacao dos itemRelatorio dentro do mapa. Deve ser igual aos Keys do mapa itensRelatorio.
	protected List<Long> idsSubpdcs;
	protected List<ItemRelatorio> classificacao;
	
	//////Construtores
	public Relatorio()
	{
		itensRelatorio = new HashMap<Long, ItemRelatorio>();
		idsPropostas = new ArrayList<Long>();
		idsSubpdcs = new ArrayList<Long>();
		classificacao = new ArrayList<ItemRelatorio>();
	}
	public Relatorio(List<Avaliacao> avaliacoes)
	{
		setItensRelatorio(avaliacoes);
	}
	
	//////Metodos
	
	/**
	 * Retorna todos os itens pertencentes ao subpdc especificado dentro de listaFiltrar
	 * @param id - id do SubPdc
	 * @param listaFiltrar - A lista a ser filtrada
	 * @return ItemRelatorio[] - Array de todos os items de relatorio pertencentes ao subPDC especificado
	 */
	//TODO: considerar substituir tipo do id de Long para Subpdc
	public static ItemRelatorio[] itensPorSubpdc(Long id, ArrayList<ItemRelatorio> listaFiltrar)	{
		ArrayList<ItemRelatorio> auxOut = new ArrayList<>();
		for(int i=0;i<listaFiltrar.size();i++)
		{
			if(listaFiltrar.get(i).getProposta().getSubPDC().getId() == id) {
				auxOut.add(listaFiltrar.get(i));
			}
		}
		ItemRelatorio[] out = new ItemRelatorio[auxOut.size()];
		out = auxOut.toArray(out);
		
		return out;
	}
	
	/**
	 * Calcula a classificacao de todos os itens desse relatorio
	 */
	public void calcularClassificacao() {
		ItemRelatorio[] arr = new ItemRelatorio[itensRelatorio.size()];
		System.out.println(">>"+Integer.toString(arr.length));
		//for(int j=0;j<idsSubpdcs.size();j++)
		//{
			//Pega todos os itens do relatorio
			arr = this.itensRelatorio.values().toArray(new ItemRelatorio[this.itensRelatorio.values().size()]);
			
			QuickSort q = new QuickSort();
			q.sort(arr, 0, arr.length-1); //ordena por soma das notas
			
			classificacao = new ArrayList<ItemRelatorio>();
			//atribui o numero da classificacao
			for(int i=0;i<arr.length;i++) 
			{
				itensRelatorio.get(arr[(arr.length-1) - i].getProposta().getId()).setClassificacao(i+1);
				classificacao.add(i, itensRelatorio.get(arr[(arr.length-1) - i].getProposta().getId()) );
			}
		//}
			
			
	}
	
	public List<ItemRelatorio> getClassificacao() {
		return classificacao;
	}
	
	
	/**
	 * Calcula a classificacao de todos os itens desse relatorio por subpdc
	 */
	public void calcularClassificacaoPorSubpdc() {
		ItemRelatorio[] arr = new ItemRelatorio[itensRelatorio.size()]; 
		for(int j=0;j<idsSubpdcs.size();j++)
		{
			//Pega todos itens do subpdc atual
			arr = itensPorSubpdc(idsSubpdcs.get(j), new ArrayList<ItemRelatorio>(this.itensRelatorio.values()));
			System.out.println("Lenght>>>"+Integer.toString(arr.length));
			
			QuickSort q = new QuickSort();
			q.sort(arr, 0, arr.length-1); //ordena por soma das notas
			
			//atribui o numero da classificacao
			for(int i=0;i<arr.length;i++) 
			{
				//System.out.println("<><>>>"+Integer.toString(i+1));
				itensRelatorio.get(arr[(arr.length-1) - i].getProposta().getId()).setClassificacaoSubpdc(i+1);
				classificacao.add(i, itensRelatorio.get(arr[(arr.length-1) - i].getProposta().getId()) );
			}
		}
	}
	
	/**
	 * Adiciona os itens de relatorio a este relatorio com as avaliacoes especificadas
	 * @param avaliacoes
	 */
	public void setItensRelatorio(List<Avaliacao> avaliacoes)
	{
		Avaliacao avaliacaoAtual;
		Long idPropostaAtual;
		Long idSubpdcAtual;
		idsPropostas = new LinkedList<>(); //Reset dos ids
		if(avaliacoes != null) {
			for(int i =0;i<avaliacoes.size();i++)
			{
				//Pega as propriedades da avaliacao atual
				avaliacaoAtual = avaliacoes.get(i);
				idPropostaAtual = avaliacaoAtual.getProposta().getId();
				idSubpdcAtual = avaliacaoAtual.getProposta().getSubPDC().getId();
				//Se nao existir um itemRelatorio para a proposta nao existir, crie um itemRelatorio
				if(this.itensRelatorio.get(idPropostaAtual) == null) //TODO: adicionar || this.itensRelatorio.size() <= 0 ????
				{
					this.itensRelatorio.put(idPropostaAtual, new ItemRelatorio() );
					this.idsPropostas.add(idPropostaAtual);
					this.idsSubpdcs.add(idSubpdcAtual);
				}
				//Adicione a avaliacao a proposta
				this.itensRelatorio.get(idPropostaAtual).addAvaliacao(avaliacaoAtual);
			}
			
			//Recalcula a classificacao
			calcularClassificacaoPorSubpdc(); //TODO: REDUNDANTE?
			calcularClassificacao();
		}
	}
	
	public LinkedList<ItemRelatorio> getItensRelatorio()
	{
		return new LinkedList<ItemRelatorio>(this.itensRelatorio.values());
	}
	
	/**
	 * Retorna lista de ItemRelatorio com os itens de relatorio das propostas que foram classificadas
	 * @return
	 */
	public LinkedList<ItemRelatorio> getItensRelatorioClassificado()
	{
		LinkedList<ItemRelatorio> classificados = new LinkedList<ItemRelatorio>();
		ItemRelatorio itemAtual;
		for(int i=0;i<this.itensRelatorio.size();i++)//Navegue todos os itens desse relatorio
		{
			itemAtual = itensRelatorio.get(idsPropostas.get(i));
			if(!itemAtual.isDesclassificado())//Se NaO for desclassificado, adicione
			{
				classificados.add(itemAtual);
			}
		}
		
		return classificados;
	}
	
	/**
	 * Retorna lista de ItemRelatorio com os itens de relatorio das propostas que foram desclassificadas
	 * @return LinkedList<ItemRelatorio>
	 */
	public LinkedList<ItemRelatorio> getItensRelatorioDesclassificado()
	{
		LinkedList<ItemRelatorio> desclassificados = new LinkedList<ItemRelatorio>();
		ItemRelatorio itemAtual;
		for(int i=0;i<this.itensRelatorio.size();i++)//Navegue todos os itens desse relatorio
		{
			itemAtual = itensRelatorio.get(idsPropostas.get(i));
			if(itemAtual.isDesclassificado())//Se for desclassificado, adicione
			{
				desclassificados.add(itemAtual);
			}
		}
		
		return desclassificados;
	}
	
}

/**
 * Classe de ordenacao - para uso na classificacao
 */
class QuickSort 
{ 
    private int partition(ItemRelatorio arr[], int low, int high) 
    { 
        ItemRelatorio pivot = arr[high];  
        int i = (low-1);
        for (int j=low; j<high; j++) 
        { 
            if (arr[j].getSoma() < pivot.getSoma()) 
            { 
                i++; 
  
                ItemRelatorio temp = arr[i]; 
                arr[i] = arr[j]; 
                arr[j] = temp; 
            } 
        } 
  
        ItemRelatorio temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    } 
  
    public void sort(ItemRelatorio arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            int pi = partition(arr, low, high); 
  
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
}
