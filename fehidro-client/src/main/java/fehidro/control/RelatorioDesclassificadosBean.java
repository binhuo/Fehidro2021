package fehidro.control;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import fehidro.model.Avaliacao;
import fehidro.model.Relatorio;
import fehidro.rest.client.AvaliacaoRESTClient;

@ManagedBean(name="relatorioDesclassificados")
@ViewScoped
public class RelatorioDesclassificadosBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Relatorio relatorio;
	private AvaliacaoRESTClient rest;
	
	public Relatorio getRelatorio() {
		return relatorio;
	}

	public void setRelatorio(Relatorio relatorio) {
		this.relatorio = relatorio;
	}

	public RelatorioDesclassificadosBean() {
		relatorio = new Relatorio();
		rest  = new AvaliacaoRESTClient();
		List<Avaliacao> avaliacoes = rest.findAll();//TODO: Considerar armazenar desclassificacao no BD e pegar somente os desclassificados via REST.
		
		this.relatorio.setItensRelatorio(avaliacoes);
	}

	public String getUrl() {
		return "/relatorio/relatorioDesclassificados";
	}
	
}
