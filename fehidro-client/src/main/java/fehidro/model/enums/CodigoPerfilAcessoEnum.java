package fehidro.model.enums;

public enum CodigoPerfilAcessoEnum {
	SecretariaExecutiva(1), AvaliadorCtpg(2);
	
	private int _codigo;
	
	CodigoPerfilAcessoEnum(int codigo) {
		_codigo = codigo;
	}
	
	public int getCodigo() {
		return _codigo;
	}

}
