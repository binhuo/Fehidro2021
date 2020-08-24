package fehidro.api.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_instituicao")
@Entity
//@NamedQueries({
//	@NamedQuery(name = "Instituicao.listarTodas", query = "select i from Instituicao i order by i.nome, i.tipo"),
//	@NamedQuery(name = "Instituicao.consultarPorId", query = "select i from Instituicao i where i.id=?1")
//})
public class Instituicao extends AbstractEntity {
	
	@Column(name="nm_instituicao")
	private String nome;
	
	@Column(name="id_tipoinstituicao")
	private int tipo;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "instituicao_id")
	private List<Proposta> propostas;

	@JsonIgnore
	public List<Proposta> getPropostas() {
		return propostas;
	}

	@JsonIgnore
	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
}
