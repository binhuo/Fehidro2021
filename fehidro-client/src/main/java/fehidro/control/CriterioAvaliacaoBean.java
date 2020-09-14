package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import fehidro.model.CriterioAvaliacao;
import fehidro.model.PerfilAcesso;
import fehidro.model.Pontuacao;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.TipoProposta;
import fehidro.rest.client.CriterioAvaliacaoRESTClient;
import fehidro.rest.client.PerfilAcessoRESTClient;
import fehidro.rest.client.TipoPropostaRESTClient;

@ManagedBean
@SessionScoped
public class CriterioAvaliacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private CriterioAvaliacaoRESTClient restCriterio;
	private TipoPropostaRESTClient restTipoProposta;
	private PerfilAcessoRESTClient restPerfilAcesso;
	private List<CriterioAvaliacao> criterios;
	private CriterioAvaliacao criterio;

	private String consulta;
	private Long idCriterioAvaliacao;
	//private int numeroSubcriterioNovaPontuacao;
	private List<TipoProposta> tiposProposta;
	private TipoProposta[] tiposPropostasSelecionados;
	private List<SelectItem> perfisAcesso;

	public CriterioAvaliacaoBean() {
		startView(true);
	}

	public String index() {
		startView(true);
		return "/criterioAvaliacao/index?faces-redirect=true"; 
	}

	public String cadastro() {
		startView(true);
		definirNumeroCriterio();

		return "/criterioAvaliacao/cadastro?faces-redirect=true";
	}

	private void definirNumeroCriterio() {
		List<CriterioAvaliacao> criteriosExistentes = this.getCriterios();

		if (criteriosExistentes == null) {
			this.criterio.setNumero(1);
		}
		else {
			this.criterio.setNumero(criteriosExistentes.size() + 1);
		}
	}

	public String editar() 
	{
		if (getIdCriterioAvaliacao() != null) {

			CriterioAvaliacao c = this.restCriterio.find(getIdCriterioAvaliacao());
			c.getSubcriterios().forEach(s -> {
				if (s.getPerfilAcesso() == null)
					s.setPerfilAcesso(new PerfilAcesso());
			});
			setCriterio(c);
		}
		setInfo();

		return "/criterioAvaliacao/cadastro?faces-redirect=true";
	}

	public String salvar() {
		if (getIdCriterioAvaliacao() == null) {
			this.restCriterio.create(this.criterio);
		}
		else {
			this.restCriterio.edit(this.criterio);
		}
		startView(true);
		definirNumeroCriterio();

		return null;
	}

	public void addPontuacaoCriterio (AjaxBehaviorEvent event) {
		CriterioAvaliacao c = this.getCriterio();
		List<Pontuacao> p = c.getPontuacoes();

		if (p == null) {
			this.criterio.setPontuacoes(new ArrayList<Pontuacao>());
		}

		c.getPontuacoes().add(new Pontuacao());
		this.criterio.setSubcriterios(new ArrayList<SubcriterioAvaliacao>());
	}

	public void addSubcriterio(AjaxBehaviorEvent event) {
		CriterioAvaliacao c = this.getCriterio();
		c.setPerfilAcesso(null);
		c.setPontuacoes(new ArrayList<Pontuacao>());
		List<SubcriterioAvaliacao> subs = c.getSubcriterios();

		if (subs == null) {
			this.criterio.setSubcriterios(new ArrayList<SubcriterioAvaliacao>());
		}

		//65 - 90: maiusculo; 97 - 122: minusculo;
		int qtSubcriterios = subs.size();
		int letra = qtSubcriterios + 97;

		if (qtSubcriterios > 122) {
			letra = 97;
		}

		SubcriterioAvaliacao novoSubcriterio = new SubcriterioAvaliacao();
		novoSubcriterio.setLetra((char)letra);
		novoSubcriterio.setNumero(qtSubcriterios + 1);
		novoSubcriterio.setPontuacoes(new ArrayList<Pontuacao>());
		novoSubcriterio.setTiposProposta(new ArrayList<TipoProposta>());
		novoSubcriterio.setPerfilAcesso(new PerfilAcesso());

		c.getSubcriterios().add(novoSubcriterio);
		novoSubcriterio.setPontuacoes(new ArrayList<Pontuacao>());
	}

	public void addPontuacaoSubcriterio(AjaxBehaviorEvent event) {
		int numeroSubcriterioNovaPontuacao = (int) event.getComponent().getAttributes().get("numeroSubcriterioNovaPontuacao");
		this.getCriterio().getSubcriterios().get(numeroSubcriterioNovaPontuacao - 1).getPontuacoes().add(new Pontuacao());
	}

	private void startView(boolean setInfo) {
		this.restCriterio = new CriterioAvaliacaoRESTClient();
		this.setRestPerfilAcesso(new PerfilAcessoRESTClient());
		this.criterio = new CriterioAvaliacao();
		this.criterio.setSubcriterios(new ArrayList<SubcriterioAvaliacao>());
		this.criterio.setPontuacoes(new ArrayList<Pontuacao>());
		this.criterio.setPerfilAcesso(new PerfilAcesso());
		setIdCriterioAvaliacao(null);
		
		if (setInfo)
			setInfo();
	}

	private void setInfo() {
		this.setCriterios(this.restCriterio.findAll());
		this.setTiposProposta();
		this.setPerfisAcesso();
	}
	
	public Long getIdCriterioAvaliacao() {
		return idCriterioAvaliacao;
	}
	public void setIdCriterioAvaliacao(Long idCriterioAvaliacao) {
		this.idCriterioAvaliacao = idCriterioAvaliacao;
	}
	public CriterioAvaliacaoRESTClient getRestCriterio() {
		return restCriterio;
	}
	public void setRestCriterio(CriterioAvaliacaoRESTClient restCriterio) {
		this.restCriterio = restCriterio;
	}
	public List<CriterioAvaliacao> getCriterios() {
		return criterios;
	}
	public void setCriterios(List<CriterioAvaliacao> criterios) {
		this.criterios = criterios;
	}
	public CriterioAvaliacao getCriterio() {
		return criterio;
	}
	public void setCriterio(CriterioAvaliacao criterio) {
		this.criterio = criterio;
	}
	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public List<TipoProposta> getTiposProposta() {
		return tiposProposta;
	}

	public void setTiposProposta() {
		this.restTipoProposta = new TipoPropostaRESTClient();
		this.tiposProposta  = this.restTipoProposta.findAll();		
	}

	public TipoPropostaRESTClient getRestTipoProposta() {
		return restTipoProposta;
	}

	public void setRestTipoProposta(TipoPropostaRESTClient restTipoProposta) {
		this.restTipoProposta = restTipoProposta;
	}

	public TipoProposta[] getTiposPropostasSelecionados() {
		return tiposPropostasSelecionados;
	}

	public void setTiposPropostasSelecionados(TipoProposta[] tiposPropostasSelecionados) {
		this.tiposPropostasSelecionados = tiposPropostasSelecionados;
	}

	public TipoProposta[] getTiposPropostasSelecionadosValue() {
		this.tiposPropostasSelecionados = new TipoProposta[this.tiposProposta.size()];

		for(int i = 0; i < this.tiposProposta.size(); i++) {
			tiposPropostasSelecionados[i] = this.tiposProposta.get(i);
		}

		return this.tiposPropostasSelecionados;
	}

	public String getTiposPropostasSelecionadosInString() {
		return Arrays.toString(this.tiposPropostasSelecionados);
	}

	public PerfilAcessoRESTClient getRestPerfilAcesso() {
		return restPerfilAcesso;
	}

	public void setRestPerfilAcesso(PerfilAcessoRESTClient restPerfilAcesso) {
		this.restPerfilAcesso = restPerfilAcesso;
	}

	public List<SelectItem> getPerfisAcesso() {
		return perfisAcesso;
	}

	public void setPerfisAcesso() {
		List<PerfilAcesso> perfisBase = this.restPerfilAcesso.findAll();
		List<SelectItem> perfis = new ArrayList<>();
			
		perfis = perfisBase.stream().map(p -> {
			return new SelectItem(p.getId(), p.getNome());
		}).collect(Collectors.toList());
		
		this.perfisAcesso = perfis;
	}
}

