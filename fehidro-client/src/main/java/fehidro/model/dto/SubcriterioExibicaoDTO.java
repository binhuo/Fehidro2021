package fehidro.model.dto;


public class SubcriterioExibicaoDTO {

	private Long id;
	private String titulo;
	
	private int numero;
	
	private char letra;

	
	
	public SubcriterioExibicaoDTO(Long id,String titulo, int numero, char letra) {
		this.id = id;
		this.titulo = titulo;
		this.numero = numero;
		this.letra = letra;
	}
	
	public SubcriterioExibicaoDTO() {
		
	}

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public char getLetra() {
		return letra;
	}

	public void setLetra(char letra) {
		this.letra = letra;
	}
	
}
