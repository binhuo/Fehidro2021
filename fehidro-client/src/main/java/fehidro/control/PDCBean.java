package fehidro.control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import fehidro.rest.client.PDCRESTClient;
import fehidro.model.Meta;
import fehidro.model.PDC;
import fehidro.model.SubPDC;

@ManagedBean
@SessionScoped
public class PDCBean  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idPDC;
	private String consulta;
	private PDCRESTClient restPDC;
	private PDC pdc;
	private List<PDC> pdcs;
	private int ordemListagemSubpdcNovaMeta;
	private int idSubpdcNovaMeta;
	
	public PDCBean() {
		startView(true);
	}
	
	public String index() {
		startView(true);
		return "/pdc/index?faces-redirect=true"; 
	}
	
	public String cadastro() {
		startView(true);
		return "/pdc/cadastro?faces-redirect=true";
	}
	
	public String editar() 
	{
		if (getIdPDC() != null) {

			PDC p = this.restPDC.find(getIdPDC());
			setPDC(p);
		}
		
		setInfo();

		return "/pdc/cadastro?faces-redirect=true";
	}
	
	public String salvar() { 
		if (getIdPDC() == null) {
			this.restPDC.create(this.pdc);
		} else {
			this.restPDC.edit(this.pdc);
		}
		startView(true);

		return null;
	}
	
	public String addSubPDC() {
		
		int qtSubPDCs = 0;
		
		if (this.pdc.getSubPDCs() == null) {
			this.pdc.setSubPDCs(new ArrayList<SubPDC>());
		} else {
			qtSubPDCs = this.pdc.getSubPDCs().size();
		}
		
		SubPDC novo = new SubPDC();
		novo.setOrdemListagem(qtSubPDCs + 1);
		novo.setMetas(new ArrayList<Meta>());
		
		this.pdc.getSubPDCs().add(novo);
		 
		return null;
	}
	
	public String addMetaSubpdc() {
		if (this.pdc.getId() == null || this.pdc.getId() == 0) {
			this.pdc.getSubPDCs().get(getOrdemListagemSubpdcNovaMeta() - 1).getMetas().add(new Meta());
		} else {
			
			Optional<SubPDC> optSubpdcNovaMeta = this.pdc.getSubPDCs().stream()
					.filter(s -> s.getId() == this.idSubpdcNovaMeta)
				    .findFirst();
			
			if (optSubpdcNovaMeta.isPresent()) {
				SubPDC subpdcNovaMeta = optSubpdcNovaMeta.get();
				subpdcNovaMeta.getMetas().add(new Meta());
			}			
		}
		
		return null;
	}
	
	private void startView(boolean setInfo) {
		this.pdc = new PDC();
		this.pdcs = new ArrayList<PDC>();
		this.pdc.setSubPDCs(new ArrayList<SubPDC>());
		this.restPDC = new PDCRESTClient();
		
		if (setInfo)
			setInfo();
	}
	
	private void setInfo() {
		this.setPdcs(this.restPDC.findAll());
	}

	public Long getIdPDC() {
		return idPDC;
	}

	public void setIdPDC(Long idPDC) {
		this.idPDC = idPDC;
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public PDCRESTClient getRestPDC() {
		return restPDC;
	}

	public void setRestPDC(PDCRESTClient restPDC) {
		this.restPDC = restPDC;
	}

	public PDC getPDC() {
		return pdc;
	}

	public void setPDC(PDC pDC) {
		pdc = pDC;
	}

	public List<PDC> getPdcs() {
		return pdcs;
	}

	public void setPdcs(List<PDC> pDCs) {
		pdcs = pDCs;
	}

	public int getOrdemListagemSubpdcNovaMeta() {
		return ordemListagemSubpdcNovaMeta;
	}

	public void setOrdemListagemSubpdcNovaMeta(int ordemListagemSubpdcNovaMeta) {
		this.ordemListagemSubpdcNovaMeta = ordemListagemSubpdcNovaMeta;
	}

	public int getIdSubpdcNovaMeta() {
		return idSubpdcNovaMeta;
	}

	public void setIdSubpdcNovaMeta(int idSubpdcNovaMeta) {
		this.idSubpdcNovaMeta = idSubpdcNovaMeta;
	}
}
