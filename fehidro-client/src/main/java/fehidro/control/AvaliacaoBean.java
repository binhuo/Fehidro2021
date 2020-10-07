package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

import fehidro.model.Avaliacao;
import fehidro.model.CTPG;
import fehidro.model.Pontuacao;
import fehidro.model.Proposta;
import fehidro.model.SubcriterioAvaliacao;
import fehidro.model.Usuario;
import fehidro.model.enums.CodigoPerfilAcessoEnum;
import fehidro.rest.client.AvaliacaoRESTClient;
import fehidro.rest.client.CTPGRESTClient;
import fehidro.rest.client.PontuacaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
import fehidro.rest.client.SubcriterioAvaliacaoRESTClient;
import fehidro.rest.client.UsuarioRESTClient;
import fehidro.util.SessionContext;

@ManagedBean
@SessionScoped 
public class AvaliacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	//Proposta
	private PropostaRESTClient restProposta;
	private List<SelectItem> propostas;
	private CTPGRESTClient restCTPG;
	public String isPropostasVazio = "false"; //Usado para desabilitar o botao de cadastro de avaliacao em index.html
	//Subcriterio
	private SubcriterioAvaliacaoRESTClient restSubcriterio;
	private List<SubcriterioAvaliacao> subcriteriosObject;
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
	
	//Consulta
	private String consulta;
	
	//Usuario
	private UsuarioRESTClient restUsuario;
	
	//===Construtor===
	public AvaliacaoBean() {
		startView(true);
		//Idealmente qualquer adicao de codigo que voce coloque deve ir dentro de startView, 
		//se voce colocar mais algo dentro do construtor entao ele sera executado somente uma vez por objeto quando ele eh criado,
		//mas nao quando voce precisar "resetar" essa classe.
	}
	
	//===METODOS===
	
	//Consulta
		public String getConsulta() {
			return consulta;
		}
		public void setConsulta(String consulta) {
			this.consulta = consulta;
		}
	
	public String index() {
		startView(true); //TODO: Possivelmente redundante devido a f:viewAction em index.html 
		return "/avaliacao/index?faces-redirect=true"; 
	}
	
	/**
	 * Somente utilizado em viewAction em index.html para poder resetar o botao ao utiliza o menu lateral
	 */
	public void startView() {
		//Inicialmente nos definimos esse bean como SessionScoped pois me pareceu a forma mais simples de fazer
		//porem 
		//A necessidade desse metodo me faz questionar se esse Bean realmente deve ser SessionScoped, 
		//porem isso ser SessionScoped trouxe bastante facilidades para manter os dados
		//entre avaliacao/index.xhtml, avaliacao/cadastro.xhtml e avaliacao/cadastroSubcriterio.xhtml
		//Nao possuo tempo o suficiente para rever isso no momento, mas caso seja necessario no futuro,
		//eh possivel que esse bean tenha que ser reestruturado
		//mas no momento ele cumpre o que precisa. 
		this.startView(true);
	}
	
	private void startView(boolean setInfo) {
		this.restAvaliacao = new AvaliacaoRESTClient();
		
		//Reset Avaliacao
		this.avaliacao = new Avaliacao();
		this.avaliacao.setProposta(new Proposta());
		this.avaliacao.setSubcriterio(new SubcriterioAvaliacao());
		this.avaliacao.setNota(new Pontuacao());
		this.avaliacao.setComentario("");
		ArrayList<Pontuacao> p = new ArrayList<Pontuacao>();
		p.add(new Pontuacao());
		this.avaliacao.getSubcriterio().setPontuacoes(p);
		
		//Setando avaliador na avaliacao
		this.avaliacao.setAvaliador( (Usuario)SessionContext.getInstance().usuarioLogado() );
		
		this.subcriteriosObject = new ArrayList<SubcriterioAvaliacao>();
		
		//Carrega as informacoes usando o REST
		if (setInfo) {
			setInfo();
		}
	}
	
	private void setInfo() {
		if(SessionContext.getInstance().usuarioLogado().getPerfilAcesso() == CodigoPerfilAcessoEnum.SecretariaExecutiva.getCodigo()) {
			//Pega todas as avaliacoes
			this.setAvaliacoes(this.restAvaliacao.findAll());
		}else {
			//Pega somente as avaliacoes do usuario
			this.setAvaliacoes(this.restAvaliacao.findAllUsuario( SessionContext.getInstance().usuarioLogado() ));
		}
		this.setPropostas();
		//this.setCriterios();
		
	}
	
	public String cadastro() {
		startView(true); //questionavel?
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
		
		this.avaliacao.setSubcriterio( subcriteriosObject.get(contadorSubcriterio) );
		
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
			this.avaliacao.getSubcriterio().setId( (Long) subcriterios.get(contadorSubcriterio).getValue() );
			this.setPontuacoes();
		}else {
			contadorSubcriterio = 0;
			stringSubcriteiroAtual = subcriterios.get(0).getLabel();
			this.setPontuacoes();
			return this.index();
		}

		return this.pageSubcriterio();
	}
	
	public String pageSubcriterio() {
		this.setSubcriterios();
		this.avaliacao.setProposta(restProposta.find(this.avaliacao.getProposta().getId()));
		if(subcriterios == null) {
			return index();
		}else {
			if(subcriterios.size() <= 0){
				return index();
			}
		}
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
        List<Proposta> propostasBase;
        List<SelectItem> propostas = new ArrayList<>();

        if(this.avaliacao.getAvaliador().getPerfilAcesso() == 1) {
            propostasBase = this.restProposta.findEmAberto(this.avaliacao.getAvaliador()); 
            
        }else {
            this.restCTPG = new CTPGRESTClient();
            CTPG user = this.restCTPG.find(this.avaliacao.getAvaliador().getId());
            propostasBase = this.restProposta.findEmAberto(this.avaliacao.getAvaliador(), user.getInstituicao().getId());
        }

        for (Proposta i : propostasBase) 
        {
            propostas.add(new SelectItem(i.getId(), i.getNomeProjeto()));
        }

        this.propostas = propostas;
        getIsPropostasVazio();
    }
	
	public String getIsPropostasVazio() {
		if(this.propostas.size() <= 0) {
			this.isPropostasVazio = "true";
			return "true";
		}else {
			this.isPropostasVazio = "false";
			return "false";
		}
	}
	
	//Pontuacoes
	public List<SelectItem> getPontuacoes() {
		return pontuacoes;
	}
	public void setPontuacoes() {
		this.restPontuacao = new PontuacaoRESTClient();
		
		List<Pontuacao> pontuacaoBase = this.restPontuacao.findAllBySubcriterio(this.avaliacao.getSubcriterio());
		List<SelectItem> pontuacoes = new ArrayList<>();

		for (Pontuacao i : pontuacaoBase) 
		{
			pontuacoes.add(new SelectItem(i.getId(), i.getTitulo()));
		}
		
		this.pontuacoes = pontuacoes;
	}
	
	
	//Subcriterios
	protected boolean todosSecretariaAvaliaramEm5AB() {
		System.out.println("todosSecretariaAvaliaramEm5AB()");
		restUsuario = new UsuarioRESTClient();
		
		int qtd = restAvaliacao.findAllAvaliacaoSubcriterioSecretaria(this.avaliacao.getProposta()).size();
		int qtdUsuario = restUsuario.obterPorPerfilAcesso(new Long(1)).size();
		//TODO: fazer validacao apropriada
		if(qtd >= (2*qtdUsuario) ) {
			return true;
		}
		
		return false;
	}
	public List<SelectItem> getSubcriterios() {
		return subcriterios;
	}
	public void setSubcriterios() {
		this.restSubcriterio = new SubcriterioAvaliacaoRESTClient();
		//List<SubcriterioExibicaoDTO> dtos = restSubcriterio.obterSubcriteriosDTO();
		
//		if(SessionContext.getInstance().usuarioLogado().getPerfilAcesso() == CodigoPerfilAcessoEnum.SecretariaExecutiva.getCodigo()) {
			//Secretaria Executiva
			subcriteriosObject = restSubcriterio.findEmAberto(this.avaliacao.getAvaliador(), this.avaliacao.getProposta());
//		}else { 
//			subcriteriosObject = restSubcriterio.findEmAberto(this.avaliacao.getAvaliador(), this.avaliacao.getProposta());
//		}
		
		List<SubcriterioAvaliacao> subcriteriosBase = subcriteriosObject;
		
		if(subcriteriosBase.size() <= 0)
		{
			contadorSubcriterio = 0;
			System.out.println("vazio - return");
			return;
		}
		
		SubcriterioAvaliacao aux;
		//if(dtos != null) {
//			for(int i =0;i<dtos.size();i++)
//			{
//				aux = new SubcriterioAvaliacao();
//				aux.setId(dtos.get(i).getId());
//				aux.setLetra(dtos.get(i).getLetra());
//				aux.setNumero(dtos.get(i).getNumero());
//				aux.setTitulo(dtos.get(i).getTitulo());
//				subcriteriosBase.add(aux);
//			}
		
			List<SelectItem> subcriterios = new ArrayList<>();
	
			for (SubcriterioAvaliacao i:subcriteriosBase ) 
			{
				subcriterios.add(new SelectItem( i.getId() , i.getTitulo() ) );
			}
			
			contadorSubcriterio = 0;
			this.avaliacao.getSubcriterio().setId((Long)subcriterios.get(0).getValue());
			this.setPontuacoes();
			this.stringSubcriteiroAtual = subcriterios.get(0).getLabel();
			
			this.subcriterios = subcriterios;
		//}
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
