package fehidro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fehidro.api.model.AbstractEntity;
import fehidro.api.repository.UsuarioRepository;
import fehidro.model.dto.usuario.CadastroUsuarioDTO;

@Table(name = "tb_usuario")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario extends AbstractEntity {
	
	@Column(name = "nm_usuario", length = 256)
	private String nome;

	@Column(name = "nm_sobrenome", length = 256)
	private String sobrenome;

	@Column(name = "nr_cpf", length = 256)
	private String CPF;

	@Column(name = "ds_email", length = 256)
	private String email;

	@Column(name = "ds_login", length = 256)
	private String login;

	@Column(name = "ds_senha", length = 256)
	private String senha;

	@Column(name = "ic_ativo")
	private Boolean ativo;

	@Column(name = "nr_celular")
	private String celular;

	@OneToOne
	@JoinColumn(name = "perfilacesso_id")
	private PerfilAcesso perfilAcesso;
	
	public Usuario() {
	}
	
	public Usuario(CadastroUsuarioDTO dto) {
		if(dto != null) {
			this.setId(dto.getId());
			this.nome = dto.getNome();
			this.sobrenome = dto.getSobrenome();
			this.CPF = dto.getCPF();
			this.email = dto.getEmail();
			this.login = dto.getLogin();
			this.senha = dto.getSenha();
			this.ativo = dto.getAtivo();
			this.celular = dto.getCelular();
			
			if (dto.getPerfilAcesso() > 0) {
				this.perfilAcesso = new PerfilAcesso();
				this.perfilAcesso.setId(dto.getPerfilAcesso());
			}
		}
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

	public void setCPF(String cpf) {
		// TODO
		CPF = cpf;
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

	public void setLogin() {
		this.login = this.CPF;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo() {
		this.ativo = true;
	}

	public void setInativo() {
		this.ativo = false;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public PerfilAcesso getPerfilAcesso() {
		return perfilAcesso;
	}

	public void setPerfilAcesso(PerfilAcesso perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	
	public boolean CpfJaCadastrado(UsuarioRepository _usuarioRepository) {
		return _usuarioRepository.findByCPF(this.CPF).isPresent();
	}
	

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", sobrenome=" + sobrenome + ", CPF=" + CPF + 
				", email=" + email + ", login=" + login + ", senha=" + senha + "]";
	}
}