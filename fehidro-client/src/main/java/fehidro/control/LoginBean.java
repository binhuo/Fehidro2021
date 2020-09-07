package fehidro.control;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import fehidro.model.Usuario;
import fehidro.model.enums.CodigoPerfilAcessoEnum;
import fehidro.rest.client.AuthRESTClient;
import fehidro.rest.client.UsuarioRESTClient;
import fehidro.util.SessionContext;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	private UsuarioRESTClient rest;
	private AuthRESTClient authRest;

	public LoginBean() {
		setUsuario(new Usuario());
		this.rest = new UsuarioRESTClient();
		this.authRest = new AuthRESTClient();
	}

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String index() {
		return "/login/index";		
	}

	public String realizarLogin() { 
		FacesContext context = FacesContext.getCurrentInstance();

		if (usuario != null && usuario.getLogin() != null && usuario.getSenha() != null) 
		{
			boolean sucesso = authRest.realizarLogin(usuario.getLogin(), usuario.getSenha());
			
			if (sucesso) {
				Usuario user = rest.obterPorLogin(usuario.getLogin());	
				SessionContext.getInstance().setAttribute("usuarioLogado", user);
				if (user.getPerfilAcesso() == CodigoPerfilAcessoEnum.SecretariaExecutiva.getCodigo()) 
				{
					return "/deliberacao/index?faces-redirect=true";	
				}
				else if (user.getPerfilAcesso() == CodigoPerfilAcessoEnum.AvaliadorCtpg.getCodigo())
				{
					return "/avaliacao/index?faces-redirect=true";
				}
			} else {
				context.addMessage("formLogin:msgLogin", new FacesMessage("Erro: login/senha inválidos!"));
				context.getExternalContext().getFlash().setKeepMessages(true);
				return null;
			}
		} 
		else 
		{
			context.addMessage("formLogin:msgLogin", new FacesMessage(FacesMessage.SEVERITY_FATAL, "Login", "Informe seu login e senha!"));
			context.getExternalContext().getFlash().setKeepMessages(true);
			return null;
		}
		return null;
	}
	
	public String logout() {
		SessionContext.getInstance().encerrarSessao();
		return "/login/index?faces-redirect=true";
	}
}
