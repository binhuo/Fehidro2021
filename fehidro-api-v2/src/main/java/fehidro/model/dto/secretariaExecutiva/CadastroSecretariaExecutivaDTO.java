package fehidro.model.dto.secretariaExecutiva;

import fehidro.api.model.SecretariaExecutiva;
import fehidro.model.dto.usuario.CadastroUsuarioDTO;

public class CadastroSecretariaExecutivaDTO extends CadastroUsuarioDTO{
	
	private boolean administrador;

	public CadastroSecretariaExecutivaDTO() {
		
	}
	
	public CadastroSecretariaExecutivaDTO(SecretariaExecutiva model) {
		super(model);
		if (model != null) {
			this.administrador = model.isAdministrador();
		}
	}
	
	public boolean isAdministrador() {
		return administrador;
	}

	public void setAdministrador(boolean administrador) {
		this.administrador = administrador;
	}
}
