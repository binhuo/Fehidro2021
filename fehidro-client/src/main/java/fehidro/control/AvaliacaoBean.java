package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fehidro.model.Avaliacao;
import fehidro.model.CriterioAvaliacao;
import fehidro.model.Pontuacao;
import fehidro.model.Proposta;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;
//import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.dto.SubcriterioExibicaoDTO;
import fehidro.rest.client.AvaliacaoRESTClient;
import fehidro.rest.client.CriterioAvaliacaoRESTClient;
import fehidro.rest.client.PontuacaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
import fehidro.rest.client.SubcriterioAvaliacaoRESTClient;
import fehidro.util.SessionContext;

@ManagedBean
@SessionScoped
public class AvaliacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	//Proposta
	private PropostaRESTClient restProposta;
	private List<SelectItem> propostas;
	//Subcriterio
	private SubcriterioAvaliacaoRESTClient restSubcriterio;
	private List<SelectItem> subcriterios;
	private int contadorSubcriterio = 0;
	private String stringSubcriteiroAtual;
	
	//Pontuacao
	private PontuacaoRESTClient restPontuacao;
	private List<SelectItem> pontuacoes;
	
	//Avaliacao
	private AvaliacaoRESTClient restAvaliacao;
	private Avaliacao avaliacao;
	private List<Avaliacao> avaliacoes;
	
	private String consulta;
	

	public String getConsulta() {
		return consulta;
	}
	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}
	
	public AvaliacaoBean() {
		startView(true);
	}
	
	public String index() {
		startView(true);
		return "/avaliacao/index?faces-redirect=true"; 
	}
	
	private void startView(boolean setInfo) {
		this.restAvaliacao = new AvaliacaoRESTClient();
		this.avaliacao = new Avaliacao();
		this.avaliacao.setProposta(new Proposta());
		this.avaliacao.setSubcriterio(new SubcriterioAvaliacao());
		ArrayList<Pontuacao> p = new ArrayList<Pontuacao>();
		p.add(new Pontuacao());
		this.avaliacao.getSubcriterio().setPontuacoes(p);
		this.avaliacao.setNota(new Pontuacao());
			
		if (setInfo)
			setInfo();
	}
	
	private void setInfo() {
		this.setAvaliacoes(this.restAvaliacao.findAll());
		this.setPropostas();
//		this.setCriterios();
		this.setSubcriterios();
		this.setPontuacoes();
	}
	
	public String cadastro() {
		startView(true);
		return "/avaliacao/cadastro?faces-redirect=true";
	}

	public String editar() 
	{
		if (avaliacao.getId() != null) {

			Avaliacao a = this.restAvaliacao.find(avaliacao.getId());
			setAvaliacao(a);
		}
		setInfo();

		return "/avaliacao/cadastro?faces-redirect=true";
	}
	
	public String salvar() {
		
		//Setando avaliador na avaliacao
		this.avaliacao.setAvaliador( (Usuario)SessionContext.getInstance().usuarioLogado() );
		
		this.avaliacao.getSubcriterio().setId( (Long)subcriterios.get(contadorSubcriterio).getValue() );
		
		
		System.out.println(this.avaliacao.getAvaliador());
		System.out.println(this.avaliacao.getAvaliador().getId());
		System.out.println(this.avaliacao.getNota());
		System.out.println(this.avaliacao.getNota().getId());
		System.out.println(this.avaliacao.getProposta());
		System.out.println(this.avaliacao.getProposta().getId());
		System.out.println(this.avaliacao.getSubcriterio());
		System.out.println(this.avaliacao.getSubcriterio().getId());
		
		if ( this.avaliacao.getId() == null) {
			this.restAvaliacao.create(this.avaliacao);
		}
		else {
			this.restAvaliacao.edit(this.avaliacao);
		}
		//startView(true);
		
		contadorSubcriterio++;
		if(contadorSubcriterio < subcriterios.size()) {
			stringSubcriteiroAtual = subcriterios.get(contadorSubcriterio).getLabel();
		}else {
			contadorSubcriterio = 0;
			stringSubcriteiroAtual = subcriterios.get(0).getLabel();
			return this.index();
		}

		//return "/avaliacao/cadastro?faces-redirect=true";
		return null;
	}
	
	public String pageSubcriterio() {
		return "/avaliacao/cadastroSubcriterio?faces-redirect=true";
	}

	protected void setStringSubcriterioAtual(String s) {
		this.stringSubcriteiroAtual = s;
	}
	public String getStringSubcriteiroAtual() {
		return stringSubcriteiroAtual;
	}
	
	//Propostas
	public List<SelectItem> getPropostas() {
		return propostas;
	}
	public void setPropostas() {
		this.restProposta = new PropostaRESTClient();
		List<Proposta> propostasBase = this.restProposta.findAll();
		List<SelectItem> propostas = new ArrayList<>();

		for (Proposta i : propostasBase) 
		{
			propostas.add(new SelectItem(i.getId(), i.getNomeProjeto()));
		}
		
		this.propostas = propostas;
	}
	//Pontuacoes
	public List<SelectItem> getPontuacoes() {
		return pontuacoes;
	}
	public void setPontuacoes() {
		this.restPontuacao = new PontuacaoRESTClient();
		List<Pontuacao> pontuacaoBase = this.restPontuacao.findAll(); //TODO: Substituir por pontuacao apropriada
		List<SelectItem> pontuacoes = new ArrayList<>();

		for (Pontuacao i : pontuacaoBase) 
		{
			pontuacoes.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.pontuacoes = pontuacoes;
	}
	//Subcriterios
	public List<SelectItem> getSubcriterios() {
		return subcriterios;
	}
	public void setSubcriterios() {
		this.restSubcriterio = new SubcriterioAvaliacaoRESTClient();
		List<SubcriterioExibicaoDTO> dtos = restSubcriterio.obterSubcriteriosDTO();
		List<SubcriterioAvaliacao> subcriteriosBase = new ArrayList<SubcriterioAvaliacao>();
		
		SubcriterioAvaliacao aux;
		if(dtos != null) {
			for(int i =0;i<dtos.size();i++)
			{
				aux = new SubcriterioAvaliacao();
				aux.setId(dtos.get(i).getId());
				aux.setLetra(dtos.get(i).getLetra());
				aux.setNumero(dtos.get(i).getNumero());
				aux.setTitulo(dtos.get(i).getTitulo());
				subcriteriosBase.add(aux);
			}
		
			List<SelectItem> subcriterios = new ArrayList<>();
	
			for (SubcriterioAvaliacao i:subcriteriosBase ) 
			{
				subcriterios.add(new SelectItem( i.getId() , i.getTitulo() ) );
			}
			
			contadorSubcriterio = 0;
			this.stringSubcriteiroAtual = subcriterios.get(0).getLabel();
			
			this.subcriterios = subcriterios;
		}
	}
	//Avaliacao
	public Avaliacao getAvaliacao() {
		return avaliacao;
	}
	public void setAvaliacao(Avaliacao avaliacao) {
		this.avaliacao = avaliacao;
	}
	//List Avaliacoes
	public List<Avaliacao> getAvaliacoes() {
		return avaliacoes;
	}
	public void setAvaliacoes(List<Avaliacao> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}

}
