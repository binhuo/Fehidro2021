package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import javax.faces.model.SelectItem;
import javax.faces.validator.ValidatorException;

import fehidro.exception.CpfJaCadastradoException;
import fehidro.model.CTPG;
import fehidro.model.Instituicao;
import fehidro.model.PerfilAcesso;
import fehidro.model.SecretariaExecutiva;
import fehidro.model.Usuario;
import fehidro.model.enums.PerfilAcessoEnum;
import fehidro.rest.client.CTPGRESTClient;
import fehidro.rest.client.InstituicaoRESTClient;
import fehidro.rest.client.PerfilAcessoRESTClient;
import fehidro.rest.client.SecretariaExecutivaRESTClient;
import fehidro.rest.client.UsuarioRESTClient;
import fehidro.util.SessionContext;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long idusuario; 
	private Long idtipousuario; 

	private UsuarioRESTClient restUsuario;
	private SecretariaExecutivaRESTClient restSecretaria;
	private CTPGRESTClient restCTPG;
	private InstituicaoRESTClient restInstituicao;
	private PerfilAcessoRESTClient restPerfilAcesso;
	
	private Usuario usuario;
	private CTPG ctpg;
	private SecretariaExecutiva secretaria;

	private String consulta;
	private List<Usuario> usuarios;
	private List<SelectItem> perfisAcesso;
	private List<SelectItem> instituicoes;
	private List<SelectItem> tiposAvaliadores;
	
	
	public UsuarioBean() {
		startView(true);
	}


	public String index() 
	{
		startView(true);
		return "/usuario/index?faces-redirect=true";
	}
	
	public String cadastro() 
	{
		startView(true);
		return "/usuario/cadastro?faces-redirect=true";
	}
	
	public String configuracoes() 
	{
		startView(true);
		Usuario u = SessionContext.getInstance().usuarioLogado();
		this.idusuario = u.getId();
		this.idtipousuario = (long) u.getPerfilAcesso();
		obterDadosUsuario();

		return "/usuario/configuracoes?faces-redirect=true";
	}


	public String salvar() {
		if (usuario.getPerfilAcesso() == 1) 
		{
			this.restSecretaria = new SecretariaExecutivaRESTClient();
			map(usuario, secretaria);
			if (secretaria.getId() == null)
			{
				return salvarSecretariaExecutiva(true);		
			}
			else
			{
				return salvarSecretariaExecutiva(false);		
			}
		} 
		else 
		{
			this.restCTPG = new CTPGRESTClient();
			map(usuario, ctpg);
			if (ctpg.getId() == null)
			{
				return salvarAvaliadorCTPG(true);
			}
			else
			{
				return salvarAvaliadorCTPG(false);
			}
		}
	}

	public String editar() 
	{
		obterDadosUsuario();
		return "/usuario/cadastro?faces-redirect=true";
	}
	
	public String salvarConfiguracoes() {
		if (!this.usuario.getSenha().equals(this.usuario.getConfirmacaoSenha())) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("formConfiguracoes:txtConfirmacaoSenha", new FacesMessage("Senhas diferentes!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return null;
		}
		else {
			return salvar();
		}
	}
	
	private void obterDadosUsuario() {
		if (getIdusuario() != null) {
			if (getIdtipousuario() == 1) 
			{
				this.restSecretaria = new SecretariaExecutivaRESTClient();
				SecretariaExecutiva user = this.restSecretaria.find(getIdusuario());
				map(user, usuario);
				secretaria = user;
			}
			else 
			{
				this.restCTPG = new CTPGRESTClient();
				CTPG user = this.restCTPG.find(getIdusuario());
				map(user, usuario);
				ctpg = user;
			}		
		}
	}
	
	private void startView(boolean setInfo) 
	{
		this.idusuario = null;
		this.idtipousuario = null;
		this.usuario = new Usuario();
		this.ctpg = new CTPG();
		this.ctpg.setInstituicao(new Instituicao());
		this.secretaria = new SecretariaExecutiva();
		this.restUsuario = new UsuarioRESTClient();
		this.restInstituicao = new InstituicaoRESTClient();
		this.restPerfilAcesso = new PerfilAcessoRESTClient();
		
		if (setInfo)
			setInfo();
	}
	
	private void setInfo() 
	{
		this.setUsuarios(restUsuario.findAll());
		this.setPerfisAcesso();
		this.setInstituicoes();
		this.setTiposAvaliadores();
	}
	
	private String salvarSecretariaExecutiva(boolean novo) {
		try {
			if (novo)
				this.restSecretaria.create(secretaria);
			else 
				this.restSecretaria.edit(secretaria);
			return refresh();				
		} catch (CpfJaCadastradoException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("formUsuario:msgCadastroUsuario", new FacesMessage("Erro: " + e.getMessage()));
			return null;
		}
	}
	
	private String salvarAvaliadorCTPG(boolean novo) {
		try {
			if (novo)
				this.restCTPG.create(ctpg);
			else 
				this.restCTPG.edit(ctpg);
			return refresh();			
		} catch (CpfJaCadastradoException e) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage("formUsuario:msgCadastroUsuario", new FacesMessage("Erro: " + e.getMessage()));
			return null;
		}
	}
	
	private String refresh() {
		startView(true);
		return null;
	}
	
	private void map(Usuario u, CTPG c) 
	{
		c.setId(u.getId());
		c.setNome(u.getNome());
		c.setSobrenome(u.getSobrenome());
		c.setCPF(u.getCPF());
		c.setPerfilAcesso(u.getPerfilAcesso());
		c.setCelular(u.getCelular());
		c.setEmail(u.getEmail());
		c.setLogin(u.getLogin());
		c.setSenha(u.getSenha());
		c.setAtivo(u.getAtivo());
	}
	
	private void map(Usuario u, SecretariaExecutiva s) 
	{
		s.setId(u.getId());
		s.setNome(u.getNome());
		s.setSobrenome(u.getSobrenome());
		s.setCPF(u.getCPF());
		s.setPerfilAcesso(u.getPerfilAcesso());
		s.setCelular(u.getCelular());
		s.setEmail(u.getEmail());
		s.setLogin(u.getLogin());
		s.setSenha(u.getSenha());
		s.setAtivo(u.getAtivo());
	}
	
	private void map(CTPG c, Usuario u) 
	{
		u.setId(c.getId());
		u.setNome(c.getNome());
		u.setSobrenome(c.getSobrenome());
		u.setCPF(c.getCPF());
		u.setPerfilAcesso(c.getPerfilAcesso());
		u.setCelular(c.getCelular());
		u.setEmail(c.getEmail());
		u.setLogin(c.getLogin());
		u.setSenha(c.getSenha());
		u.setAtivo(c.getAtivo());
	}
	
	private void map(SecretariaExecutiva s, Usuario u) 
	{
		u.setId(s.getId());
		u.setNome(s.getNome());
		u.setSobrenome(s.getSobrenome());
		u.setCPF(s.getCPF());
		u.setPerfilAcesso(s.getPerfilAcesso());
		u.setCelular(s.getCelular());
		u.setEmail(s.getEmail());
		u.setLogin(s.getLogin());
		u.setSenha(s.getSenha());
		u.setAtivo(s.getAtivo());
	}

	public void validaCPF(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String CPF = value.toString();

		String msg = "CPF inválido!";

		if (CPF.equals("00000000000") || CPF.equals("11111111111") ||
				CPF.equals("22222222222") || CPF.equals("33333333333") ||
				CPF.equals("44444444444") || CPF.equals("55555555555") ||
				CPF.equals("66666666666") || CPF.equals("77777777777") ||
				CPF.equals("88888888888") || CPF.equals("99999999999") || (CPF.length() != 11)) {
			FacesMessage message = new FacesMessage(msg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);

		}

		char dig10, dig11;
		int sm, i, r, num, peso;

		try {
			sm = 0;
			peso = 10;
			for (i=0; i<9; i++) {              
				// converte o i-esimo caractere do CPF em um numero:
				// (48 eh a posicao de '0' na tabela ASCII)         
				num = (int)(CPF.charAt(i) - 48); 
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig10 = '0';
			else dig10 = (char)(r + 48); 

			// Calculo do 2o. Digito Verificador
			sm = 0;
			peso = 11;
			for(i=0; i<10; i++) {
				num = (int)(CPF.charAt(i) - 48);
				sm = sm + (num * peso);
				peso = peso - 1;
			}

			r = 11 - (sm % 11);
			if ((r == 10) || (r == 11))
				dig11 = '0';
			else dig11 = (char)(r + 48);

			if (!((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))) {
				FacesMessage message = new FacesMessage(msg);
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}
		} catch (Exception erro) {
			FacesMessage message = new FacesMessage(msg);
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			throw new ValidatorException(message);
		}
	}
	
	public Long getIdusuario() {
		return idusuario;
	}
 
	public void setIdusuario(Long idusuario) {
		this.idusuario = idusuario;
	}
	
	public Long getIdtipousuario() {
		return idtipousuario;
	}

	public void setIdtipousuario(Long idtipousuario) {
		this.idtipousuario = idtipousuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<SelectItem> getPerfisAcesso() {
		return this.perfisAcesso;
	}

	public CTPG getCtpg() {
		return ctpg;
	}

	public void setCtpg(CTPG ctpg) {
		this.ctpg = ctpg;
	}

	public List<SelectItem> getInstituicoes() { 
		return instituicoes;
	}

	public void setInstituicoes() {
		this.restInstituicao = new InstituicaoRESTClient();
		List<Instituicao> instituicoesBase = this.restInstituicao.findAll();
		List<SelectItem> instituicoes = new ArrayList<>();

		for (Instituicao i : instituicoesBase) 
		{
			instituicoes.add(new SelectItem(i.getId(), i.getNome()));
		}
		
		this.instituicoes = instituicoes;
	}

	public void setPerfisAcesso() {
		List<PerfilAcesso> perfisBase = this.restPerfilAcesso.findAll();
		List<SelectItem> perfis = new ArrayList<>();
		
		perfis = perfisBase.stream().map(p -> {
			return new SelectItem(p.getId(), p.getNome());
		}).collect(Collectors.toList());
		
//		perfis.add(new SelectItem("1", "Secretaria Executiva"));
//		perfis.add(new SelectItem("2", "Avaliador CT-PG"));

		this.perfisAcesso = perfis;
	}
	
	public List<SelectItem> getTiposAvaliadores() {
		return tiposAvaliadores;
	}

	public void setTiposAvaliadores() {
		List<SelectItem> tiposAvaliadores = new ArrayList<>();
		tiposAvaliadores.add(new SelectItem("1", "Avaliador Titular"));
		tiposAvaliadores.add(new SelectItem("2", "Avaliador Suplente"));
		tiposAvaliadores.add(new SelectItem("3", "Avaliador Externo"));
		
		this.tiposAvaliadores = tiposAvaliadores;
	}
	
	
	public SecretariaExecutiva getSecretaria() {
		return secretaria;
	}


	public void setSecretaria(SecretariaExecutiva secretaria) {
		this.secretaria = secretaria;
	}
	
	public PerfilAcessoEnum[] getPerfilAcesso(){
		return PerfilAcessoEnum.values();
	}
}
