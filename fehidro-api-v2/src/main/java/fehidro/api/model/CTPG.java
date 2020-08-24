package fehidro.api.model;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

import fehidro.api.model.Usuario;

@Entity
@Table(name = "tb_ctpg")
@Inheritance(strategy = InheritanceType.JOINED)
public class CTPG extends Usuario {
		
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "fk_instituicao_id")
	private Instituicao instituicao;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_inicio_mandato")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataInicioMandato;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "dt_nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date dataNascimento;
	
	@Column(name = "id_tipoavaliador")
	private int tipoavaliador;

		
	public Date getDataInicioMandato() {
		return dataInicioMandato;
	}

	
	public void setDataInicioMandato(Date dataInicioMandato) {
		this.dataInicioMandato = dataInicioMandato;
	}

	public Instituicao getInstituicao() {
		return instituicao;
	}

	public void setInstituicao(Instituicao instituicao) {
		this.instituicao = instituicao;
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
