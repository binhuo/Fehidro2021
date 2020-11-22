package fehidro.exception;

public class CpfJaCadastradoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CpfJaCadastradoException() {}
	public CpfJaCadastradoException(String message) {
		super(message);
	}
	
	public CpfJaCadastradoException(String message, Throwable cause) {
		super(message, cause);
	}
}
