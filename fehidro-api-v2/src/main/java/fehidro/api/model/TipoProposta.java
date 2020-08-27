package fehidro.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "tb_tipoproposta")
@Entity
public class TipoProposta extends AbstractEntity {
	public TipoProposta() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	@Column(name="nm_tipoproposta")
	private String nome;
	
    @ManyToMany(mappedBy="tiposProposta")
    @JsonIgnore
    private List<SubcriterioAvaliacao> subcriterios;
    
    @ManyToMany(mappedBy="tiposProposta", targetEntity=Proposta.class)
    @JsonIgnore
    private List<Proposta> propostas;

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}
	
	public void addProposta(Proposta proposta) {
		if (this.propostas.contains(proposta))
			return;
		
		this.propostas.add(proposta);
		proposta.addTiposProposta(this);
	}
	
	public void removeProposta(Proposta proposta) {
		if (!this.propostas.contains(proposta))
			return;
		
		this.propostas.remove(proposta);
		proposta.removeTiposProposta(this);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<SubcriterioAvaliacao> getSubcriterios() {
		return subcriterios;
	}

	public void setSubcriterios(List<SubcriterioAvaliacao> subcriterios) {
		this.subcriterios = subcriterios;
	}
	
	public void addSubcriterios(SubcriterioAvaliacao sub) {
		if (this.subcriterios.contains(sub))
			return;
		
		this.subcriterios.add(sub);
	}
	
	public void removeSubcriterios(SubcriterioAvaliacao sub) {
		if (!this.subcriterios.contains(sub))
			return;
		
		this.subcriterios.remove(sub);
	}
}
