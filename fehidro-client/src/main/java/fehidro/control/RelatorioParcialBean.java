package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
//import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import fehidro.model.Avaliacao;
import fehidro.model.Proposta;
import fehidro.model.Relatorio;
import fehidro.model.SubPDC;
import fehidro.rest.client.AvaliacaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
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
	
	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioParcialBean() {
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
			//System.out.println("SUBPDC = "+ subPdc.getId());
			//System.out.println("SIZE AVALIACOES REST listarSUBPDC = " + avaliacoes.size());
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
	
	
	
}
