package fehidro.api.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table(name = "tb_deliberacao")
@Entity
//@NamedQueries({
//	@NamedQuery(name = "Deliberacao.listarTodas", query = "select d from Deliberacao d order by d.ano, d.numero"),
//	@NamedQuery(name = "Deliberacao.consultarPorId", query = "select d from Deliberacao d where d.id=?1")
//})
public class Deliberacao extends AbstractEntity {
	
	@Column(name="aa_deliberacao")
	private Integer ano;
	
	@Column(name="nr_deliberacao")
	private Integer numero;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "deliberacao_id")
	private List<Etapa> etapas = new ArrayList<Etapa>();

	
	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public List<Etapa> getEtapas() {
		return etapas;
	}

	public void setEtapas(List<Etapa> etapas) {
		this.etapas = etapas;
	}
}
