package fehidro.model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import fehidro.control.ItemRelatorio;

public class Relatorio  {
	
	protected HashMap<Long, ItemRelatorio> itensRelatorio;
	//TODO: Substituir por Sets para evitar repeticao de calculo de classificacao - vide setItensRelatorio()
	protected List<Long> idsPropostas; //Lista usada para auxiliar na manipulacao dos itemRelatorio dentro do mapa. Deve ser igual aos Keys do mapa itensRelatorio.
	protected List<Long> idsSubpdcs;
	//Construtores
	public Relatorio()
	{
		itensRelatorio = new HashMap<Long, ItemRelatorio>();
		idsPropostas = new ArrayList<Long>();
		idsSubpdcs = new ArrayList<Long>();
	}
	
	public Relatorio(List<Avaliacao> avaliacoes)
	{
		setItensRelatorio(avaliacoes);
	}
	
	//Metodos
	
	public ItemRelatorio[] itensPorSubpdc(Long id, ArrayList<ItemRelatorio> listaFiltrar)	{
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
	
	public void calcularClassificacao() {
		ItemRelatorio[] arr = new ItemRelatorio[itensRelatorio.size()]; 
		for(int j=0;j<idsSubpdcs.size();j++)
		{
			arr = itensPorSubpdc(idsSubpdcs.get(j), new ArrayList<ItemRelatorio>(this.itensRelatorio.values())); 
			QuickSort q = new QuickSort();
			q.sort(arr, 0, arr.length-1); //ordena por soma das notas
			for(int i=0;i<arr.length;i++)//atribui a classificacao
			{
				itensRelatorio.get(arr[(arr.length-1) - i].getProposta().getId()).setClassificacao(i+1);
			}
		}
	}
	
	public void setItensRelatorio(List<Avaliacao> avaliacoes)
	{
		Avaliacao avaliacaoAtual;
		Long idPropostaAtual;
		Long idSubpdcAtual;
		idsPropostas = new LinkedList<>(); //Reset dos ids
		if(avaliacoes != null) {
			for(int i =0;i<avaliacoes.size();i++)
			{
				avaliacaoAtual = avaliacoes.get(i);
				idPropostaAtual = avaliacaoAtual.getProposta().getId();
				idSubpdcAtual = avaliacaoAtual.getProposta().getSubPDC().getId();
				if(this.itensRelatorio.get(idPropostaAtual) == null)//Se nao existir um itemRelatorio para a proposta nao existir, crie um itemRelatorio
				{
					this.itensRelatorio.put(idPropostaAtual, new ItemRelatorio() );
					this.idsPropostas.add(idPropostaAtual);
					this.idsSubpdcs.add(idSubpdcAtual);
				}
				
				this.itensRelatorio.get(idPropostaAtual).addAvaliacao(avaliacaoAtual);//Adicione a avaliacao a uma proposta
			}
			
			calcularClassificacao();//calcula a classificacao
		}
	}
	
	public LinkedList<ItemRelatorio> getItensRelatorio()
	{
		return new LinkedList<ItemRelatorio>(this.itensRelatorio.values());
	}
	
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

//Para uso na classificacao
class QuickSort 
{ 
    int partition(ItemRelatorio arr[], int low, int high) 
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
  
    void sort(ItemRelatorio arr[], int low, int high) 
    { 
        if (low < high) 
        { 
            int pi = partition(arr, low, high); 
  
            sort(arr, low, pi-1); 
            sort(arr, pi+1, high); 
        } 
    } 
}
