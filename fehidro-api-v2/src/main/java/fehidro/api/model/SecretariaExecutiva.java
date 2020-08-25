package fehidro.api.model;

import javax.persistence.*;

import fehidro.model.dto.secretariaExecutiva.CadastroSecretariaExecutivaDTO;


@Entity
@Table(name = "tb_secretariaexecutiva")
public class SecretariaExecutiva extends Usuario {
	
	@Column(name = "ic_administrativo")
	private boolean administrador;

	public SecretariaExecutiva() {
		
	}
	
	public SecretariaExecutiva(CadastroSecretariaExecutivaDTO dto) {
		super(dto);
		this.administrador = dto.isAdministrador();
	}
	
	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}	
}