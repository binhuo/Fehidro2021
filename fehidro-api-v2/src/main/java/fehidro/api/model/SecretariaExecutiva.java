package fehidro.api.model;

import javax.persistence.*;


@Entity
@Table(name = "tb_secretariaexecutiva")
public class SecretariaExecutiva extends Usuario {
	
	@Column(name = "ic_administrativo")
	private boolean administrador;

	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
	
}