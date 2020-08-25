package fehidro.model.dto.ctpg;

import java.util.Date;

import fehidro.api.model.CTPG;
import fehidro.api.model.Instituicao;
import fehidro.model.dto.usuario.CadastroUsuarioDTO;

public class CadastroCtpgDTO extends CadastroUsuarioDTO {
	
	private Instituicao instituicao;
	
	private Date dataInicioMandato;
	
	private Date dataNascimento;
	
	private int tipoavaliador;
	
	public CadastroCtpgDTO() {
		
	}
	
	public CadastroCtpgDTO(CTPG model) {
		super(model);
		this.instituicao = model.getInstituicao();
		this.dataInicioMandato = model.getDataInicioMandato();
		this.dataNascimento = model.getDataNascimento();
		this.tipoavaliador = model.getTipoavaliador();
	}
		
	public Instituicao getInstituicao() {
		return instituicao;
	}
	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
	}
	public Date getDataInicioMandato() {
		return dataInicioMandato;
	}
	public void setDataInicioMandato(Date dataInicioMandato) {
		this.dataInicioMandato = dataInicioMandato;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getTipoavaliador() {
		return tipoavaliador;
	}
	public void setTipoavaliador(int tipoavaliador) {
		this.tipoavaliador = tipoavaliador;
	}
	
	

}
