package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import fehidro.model.Cronograma;
import fehidro.model.Deliberacao;
import fehidro.model.Etapa;
import fehidro.rest.client.DeliberacaoRESTClient;

@ManagedBean
@SessionScoped
public class DeliberacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private DeliberacaoRESTClient restDeliberacao;
	
	private Deliberacao deliberacao;
	private List<Deliberacao> deliberacoes;
	private List<SelectItem> responsaveis;
	
	private String consulta;
	private Long idDeliberacao;
		
	public DeliberacaoBean() {
		startView(true);
	}
	
	public String index() 
	{
		startView(true);
		return "/deliberacao/index?faces-redirect=true";
	}
	
	public String cadastro() 
	{
		startView(true);
		return "/deliberacao/cadastro?faces-redirect=true";
	}
	
	public void addEtapa(AjaxBehaviorEvent event) 
	{
		Deliberacao d = this.getDeliberacao();
		
		if (d == null) 
		{
			d = new Deliberacao();
			List<Etapa> etapas = new ArrayList<>();
			d.setEtapas(etapas);
		}
		
		List<Etapa> e = d.getEtapas();
		
		int qtEtapas = e.size();
		Etapa novaEtapa = new Etapa();
		novaEtapa.setNumero(qtEtapas + 1);
		
		Cronograma cronograma = new Cronograma();
		List<Cronograma> c = new ArrayList<>();
		
		c.add(cronograma);
		novaEtapa.setCronogramas(c);
		
		this.getDeliberacao().getEtapas().add(novaEtapa);
	}
	
	public void addCronograma(AjaxBehaviorEvent event) 
	{		
		int numeroEtapaNovoCronograma = (int) event.getComponent().getAttributes().get("numeroEtapaNovoCronograma");
		Cronograma novoCronograma = new Cronograma();
		this.getDeliberacao().getEtapas().get(numeroEtapaNovoCronograma - 1).getCronogramas().add(novoCronograma);		
	}
	
	public String salvar() 
	{
		if(deliberacao.getId() == null) 
		{
			this.restDeliberacao.create(deliberacao);
		}
		else 
		{
			this.restDeliberacao.edit(deliberacao);
		}
		
		startView(true);
		
		return null;
	}
	
	public String editar() 
	{
		if (getIdDeliberacao() != null) {

			Deliberacao d = this.restDeliberacao.find(getIdDeliberacao());
			setDeliberacao(d);
		}
		
		return "/deliberacao/cadastro?faces-redirect=true";
	}
	
	public void startView(boolean setInfo) {
		List<Etapa> e = new ArrayList<Etapa>();
		Deliberacao d = new Deliberacao();
		d.setEtapas(e);
		this.setDeliberacao(d);
		this.restDeliberacao = new DeliberacaoRESTClient();
		this.idDeliberacao = null;
		
		if(setInfo)
			setInfo();
	}
	
	public void setInfo() {
		this.setDeliberacoes(this.restDeliberacao.findAll());
		this.setResponsaveis();
	}
	
	
	
	public Deliberacao getDeliberacao() {
		return deliberacao;
	}
	public void setDeliberacao(Deliberacao deliberacao) {
		this.deliberacao = deliberacao;
	}
	public List<Deliberacao> getDeliberacoes() {
		return deliberacoes;
	}
	public void setDeliberacoes(List<Deliberacao> deliberacoes) {
		this.deliberacoes = deliberacoes;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	public List<SelectItem> getResponsaveis() {
		return responsaveis;
	}
	public void setResponsaveis() {
		List<SelectItem> responsaveis = new ArrayList<>();
		responsaveis.add(new SelectItem("1", "Secretaria Executiva"));
		responsaveis.add(new SelectItem("2", "CT-PG"));
		responsaveis.add(new SelectItem("3", "CT-PG / Secretaria Executiva"));
		
		this.responsaveis = responsaveis;
	}
	public Long getIdDeliberacao() {
		return idDeliberacao;
	}
	public void setIdDeliberacao(Long idDeliberacao) {
		this.idDeliberacao = idDeliberacao;
	}
	public DeliberacaoRESTClient getRestDeliberacao() {
		return restDeliberacao;
	}

	public void setRestDeliberacao(DeliberacaoRESTClient restDeliberacao) {
		this.restDeliberacao = restDeliberacao;
	}

}
