package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;
import fehidro.model.Relatorio;
import fehidro.model.SubPDC;
import fehidro.rest.client.AvaliacaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
import fehidro.rest.client.RelatorioRESTClient;
import fehidro.rest.client.SubPDCRESTClient;

//TODO: Renomear de Parcial para SubPDC
@ManagedBean(name="relatorioParcial")
@SessionScoped
public class RelatorioParcialBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Relatorio relatorio;
	private AvaliacaoRESTClient rest;
	private SubPDCRESTClient restSubpdc;
	private List<SelectItem> subPdcs;
	private SubPDC subPdc;
	private RelatorioRESTClient restRelatorio; //Para geracao de arquivo
	
	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioParcialBean() {
		RelatorioRESTClient r = new RelatorioRESTClient();
		relatorio = new Relatorio();
		rest  = new AvaliacaoRESTClient();
		this.restSubpdc = new SubPDCRESTClient();
		
		setSubPdcs();
		
		subPdc = new SubPDC();
	}

	public String getUrl() {
		this.relatorio = new Relatorio();//RESET
		if(this.subPdc != null) {
			List<Avaliacao> avaliacoes = rest.listarSubPDC(subPdc);
			this.relatorio.setItensRelatorio(avaliacoes);
		}
		return "/relatorio/relatorioParcial";
	}
	
	public String getSelecao() {
		return "/relatorio/relatorioSelecaoSubPdc";
	}

	public List<SelectItem> getSubPdcs() {
		return subPdcs;
	}

	public void setSubPdcs() {
		List<SubPDC> subPdcsBase = this.restSubpdc.findAll();
		List<SelectItem> subpdcs = new ArrayList<>();

		for (SubPDC i : subPdcsBase) 
		{
			subpdcs.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.subPdcs = subpdcs;
	}

	public SubPDC getSubPdc() {
		return subPdc;
	}

	public void setSubPdc(SubPDC subPdc) {
		this.subPdc = subPdc;
	}
	
	public void downloadXlsxFinal(ActionEvent e) {
		
		
		System.out.println("geracao xlsx final 1");
		
		Object[] objectArray = this.relatorio.getItensRelatorio().toArray();
		int length = objectArray.length;
	    ItemRelatorio [] itensRelatorio = new ItemRelatorio[length];
	      for(int i =0; i < length; i++) {
	    	  itensRelatorio[i] = (ItemRelatorio) objectArray[i];
	      }
		
		restRelatorio.relatorioXLSXFinal(itensRelatorio);
		
		System.out.println("geracao xlsx final 2");
		
	}
	
	public void downloadXlsxSubPdc() {
		RelatorioRESTClient r = new RelatorioRESTClient();
		
		Object[] objectArray = this.relatorio.getItensRelatorio().toArray();
		int length = objectArray.length;
	    ItemRelatorio [] itensRelatorio = new ItemRelatorio[length];
	      for(int i =0; i < length; i++) {
	    	  itensRelatorio[i] = (ItemRelatorio) objectArray[i];
	      }
		
		r.relatorioXLSXSubPdc(itensRelatorio);
		
	}
	
	
}
