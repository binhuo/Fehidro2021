package fehidro.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fehidro.control.ItemRelatorio;

public class Relatorio  {
	
	protected HashMap<Long, ItemRelatorio> itensRelatorio;
	
	protected HashSet<Long> idsPropostas; //Lista usada para auxiliar na manipulacao dos itemRelatorio dentro do mapa. Deve ser igual aos Keys do mapa itensRelatorio.
	protected HashSet<Long> idsSubpdcs;
	protected List<ItemRelatorio> classificacao;
	
	//////Construtores
	public Relatorio()
	{
		itensRelatorio = new HashMap<Long, ItemRelatorio>();
		idsPropostas = new HashSet<Long>();
		idsSubpdcs = new HashSet<Long>();
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
	//TODO: CONSIDERAR substituir tipo do id de Long para Subpdc
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
		//for(int j=0;j<idsSubpdcs.size();j++)
		//{
			//Pega todos os itens do relatorio
		
			classificacao = new ArrayList<ItemRelatorio>();//Reset
			arr = this.itensRelatorio.values().toArray(new ItemRelatorio[this.itensRelatorio.values().size()]);
			
			QuickSort q = new QuickSort();
			q.sort(arr, 0, arr.length-1); //ordena por soma das notas
			
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
		Iterator<Long> idsSubPdcsAsIterator = idsSubpdcs.iterator();
		for(int j=0;j<idsSubpdcs.size();j++)
		{
			//Pega todos itens do subpdc atual
			Long auxSubpdc = null;
			if(idsSubPdcsAsIterator.hasNext()) {
				auxSubpdc = idsSubPdcsAsIterator.next();
			}
			arr = itensPorSubpdc(auxSubpdc, new ArrayList<ItemRelatorio>(this.itensRelatorio.values()));
			
			QuickSort q = new QuickSort();
			q.sort(arr, 0, arr.length-1); //ordena por soma das notas
			
			//atribui o numero da classificacao
			for(int i=0;i<arr.length;i++) 
			{
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
		idsPropostas = new HashSet<Long>(); //Reset dos ids
		if(avaliacoes != null) {
			for(int i =0;i<avaliacoes.size();i++)
			{
				//Pega as propriedades da avaliacao atual
				avaliacaoAtual = avaliacoes.get(i);
				idPropostaAtual = avaliacaoAtual.getProposta().getId();
				idSubpdcAtual = avaliacaoAtual.getProposta().getSubPDC().getId();
				//Se nao existir um itemRelatorio para a proposta nao existir, crie um itemRelatorio
				if(this.itensRelatorio.get(idPropostaAtual) == null)
				{
					this.itensRelatorio.put(idPropostaAtual, new ItemRelatorio() );
					this.idsPropostas.add(idPropostaAtual);
					this.idsSubpdcs.add(idSubpdcAtual);
				}
				//Adicione a avaliacao a proposta
				this.itensRelatorio.get(idPropostaAtual).addAvaliacao(avaliacaoAtual);
			}
			
			//Recalcula a classificacao
			calcularClassificacaoPorSubpdc();
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
		Iterator<Long> idsPropostasAsIterator = idsPropostas.iterator();
		for(int i=0;i<this.itensRelatorio.size();i++)//Navegue todos os itens desse relatorio
		{
			Long auxIdsPropostas = null;
			if(idsPropostasAsIterator.hasNext()) {
				auxIdsPropostas = idsPropostasAsIterator.next();
			}
			itemAtual = itensRelatorio.get(auxIdsPropostas);
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
		Iterator<Long> idsPropostasAsIterator = idsPropostas.iterator();
		for(int i=0;i<this.itensRelatorio.size();i++)//Navegue todos os itens desse relatorio
		{
			Long auxIdsPropostas = null;
			if(idsPropostasAsIterator.hasNext()) {
				auxIdsPropostas = idsPropostasAsIterator.next();
			}
			itemAtual = itensRelatorio.get(auxIdsPropostas);
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
            }else {
//            	//CRITERIO DE DESEMPATE
//            	if(arr[j].getSoma() == pivot.getSoma()) {
//            		//Desempate criterio cinco
//	            	if(isDesempateCritCinco(arr[j], pivot)) {
//	            		i++; 
//	            		  
//	                    ItemRelatorio temp = arr[i]; 
//	                    arr[i] = arr[j]; 
//	                    arr[j] = temp; 
//	            	}
//            	}
            	
            }
        } 
  
        ItemRelatorio temp = arr[i+1]; 
        arr[i+1] = arr[high]; 
        arr[high] = temp; 
  
        return i+1; 
    }
    
//    private boolean isDesempateCritCinco(ItemRelatorio arr, ItemRelatorio pivot) {
//    	int somaCritCincoJ = 0;
//    	int somaCritCincoPivot = 0;
//    	for(int av=0;av<arr.getAvaliacoes().size();av++) {
//    		if(arr.getAvaliacoes().get(av).getSubcriterio().getNumero() == 5) {
//    			somaCritCincoJ += arr.getAvaliacoes().get(av).getNota().getPontos();
//    		}
//    	}
//    	for(int av=0;av<pivot.getAvaliacoes().size();av++) {
//    		if(pivot.getAvaliacoes().get(av).getSubcriterio().getNumero() == 5) {
//    			somaCritCincoPivot += pivot.getAvaliacoes().get(av).getNota().getPontos();
//    		}
//    	}
//    	
//    	return (somaCritCincoJ < somaCritCincoPivot); 
//    }
  
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
