package fehidro.model.dto.usuario;

import fehidro.api.model.Usuario;

public class CadastroUsuarioDTO {

	private long id;
	
	private String nome;

	private String sobrenome;

	private String CPF;

	private String email;

	private String login;

	private String senha;

	private Boolean ativo;

	private String celular;

	private long perfilAcesso;

	public CadastroUsuarioDTO() {

	}

	public CadastroUsuarioDTO(Usuario model) {
		if (model != null) {
			this.setId(model.getId());
			this.nome = model.getNome();
			this.sobrenome = model.getSobrenome();
			this.CPF = model.getCPF();
			this.email = model.getEmail();
			this.login = model.getLogin();
			this.senha = model.getSenha();
			this.ativo = model.isAtivo();
			this.celular = model.getCelular();
			this.perfilAcesso = model.getPerfilAcesso() == null ? 0 : model.getPerfilAcesso().getId();
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public long getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(long perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

}
