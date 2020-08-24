package fehidro.control;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fehidro.model.Usuario;
import fehidro.model.enums.CodigoPerfilAcessoEnum;
import fehidro.util.SessionContext;


@ManagedBean
@SessionScoped
public class BaseBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Usuario usuarioLogado = SessionContext.getInstance().usuarioLogado();
	private boolean isSecExecutiva = usuarioLogado.getPerfilAcesso() == CodigoPerfilAcessoEnum.SecretariaExecutiva.getCodigo();
	private boolean isAvaliadorCtpg = usuarioLogado.getPerfilAcesso() == CodigoPerfilAcessoEnum.AvaliadorCtpg.getCodigo();
	
	public BaseBean() 
	{

	}

	public boolean getIsSecExecutiva() {
		return isSecExecutiva;
	}

	public boolean getIsAvaliadorCtpg() {
		return isAvaliadorCtpg;
	}

	
	
}
