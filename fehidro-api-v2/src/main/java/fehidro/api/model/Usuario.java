package fehidro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fehidro.api.model.AbstractEntity;

@Table(name = "tb_usuario")
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
//@NamedQueries({ @NamedQuery(name = "Usuario.listarTodos", query = "select u from Usuario u order by u.nome"),
//		@NamedQuery(name = "Usuario.consultarPorId", query = "select u from Usuario u where u.id=?1"),
//		@NamedQuery(name = "Usuario.consultarPorLogin", query = "select u from Usuario u where u.login=?1"),
//		@NamedQuery(name = "Usuario.consultarPorCPF", query = "select u from Usuario u where u.CPF=?1"),
//		@NamedQuery(name = "Usuario.consultarPorPerfilAcesso", query = "select u from Usuario u where u.perfilAcesso=?1") })
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

	@Column(name = "id_perfilacesso")
	private long perfilAcesso;

	
	
	public Usuario() {
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
//		char space = 32;
//		String nomeUsuario = this.nome.replace(space, '.').toLowerCase();
//		String sobrenomeUsuario = this.sobrenome.replace(space, '.').toLowerCase();
//		String login = String.join(".", nomeUsuario, sobrenomeUsuario);

		/**
		 * Se existir um usuario com o mesmo login que outro usuario adicionar um valor
		 * inteiro no final
		 */

		String[] nomes = this.nome.toLowerCase().split(" ");
		String[] sobrenomes = this.sobrenome.toLowerCase().split(" ");
		String login = nomes[0] + "." + sobrenomes[sobrenomes.length - 1];
		
		this.login = login;
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

	public long getPerfilAcesso() {
		return perfilAcesso;
	}
	
	public void setPerfilAcesso(long perfilAcesso) {
		this.perfilAcesso = perfilAcesso;
	}

	@Override
	public String toString() {
		return "Usuario [nome=" + nome + ", sobrenome=" + sobrenome + ", CPF=" + CPF + 
				", email=" + email + ", login=" + login + ", senha=" + senha + "]";
	}
}