package fehidro.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "tb_perfilacesso")
@Entity
public class PerfilAcesso extends AbstractEntity {
	
	@Column(name="nm_perfilacesso", length = 256)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
