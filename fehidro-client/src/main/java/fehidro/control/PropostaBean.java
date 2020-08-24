package fehidro.control;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import fehidro.model.Instituicao;
import fehidro.model.Proposta;
import fehidro.model.SubPDC;
import fehidro.model.TipoProposta;
import fehidro.rest.client.InstituicaoRESTClient;
import fehidro.rest.client.PropostaRESTClient;
import fehidro.rest.client.SubPDCRESTClient;
import fehidro.rest.client.TipoPropostaRESTClient;

@ManagedBean
@SessionScoped
public class PropostaBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long idProposta;
	private String consulta;
	private String targetDownload;

	private PropostaRESTClient restProposta;
	private InstituicaoRESTClient restInstituicao;
	private TipoPropostaRESTClient restTipoProposta;
	private SubPDCRESTClient restSubPDC;
	
	private Proposta proposta;
	private List<Proposta> propostas;
	
	private List<SelectItem> instituicoes;
	private List<SelectItem> subpdcs;
	private List<TipoProposta> tiposProposta;
	private TipoProposta[] tiposPropostasSelecionados;
	
	private String folder = "C:\\Fehidro\\Propostas";
	
	public PropostaBean() {
		startView(true);
	}

	public String index() {
		startView(true);
		return "/proposta/index?faces-redirect=true"; 
	}

	public String cadastro() {
		startView(true);
		return "/proposta/cadastro?faces-redirect=true";
	}

	public String editar() 
	{
		if (getIdProposta() != null) {

			Proposta p = this.restProposta.find(getIdProposta());
			setProposta(p);
		}
		setInfo();

		return "/proposta/cadastro?faces-redirect=true";
	}

	public String salvar() {
		if (getIdProposta() == null) {
			//gero o id para a proposta
			Proposta p = this.restProposta.create(this.proposta);
			//uso o id gerado para o nome dos arquivos
			definirNomesArquivos(p);
			//atualizo nome dos arquivos na base
			this.restProposta.edit(p);
			//salvo no disco
			uploadArquivosProposta(p);
		}
		else {
			this.restProposta.edit(this.proposta);
			if (this.proposta.getTermoReferencia() != null) {
				uploadArquivoProposta(this.proposta.getTermoReferencia(), this.proposta.getNomeArquivoTermoReferencia());
			} 
			
			if (this.proposta.getCronogramaFisicoFinanceiro() != null) {
				uploadArquivoProposta(this.proposta.getCronogramaFisicoFinanceiro(), this.proposta.getNomeArquivoCronogramaFisicoFinanceiro());
			}
			
			if (this.proposta.getPlanilhaOrcamento() != null) {
				uploadArquivoProposta(this.proposta.getPlanilhaOrcamento(), this.proposta.getNomeArquivoPlanilhaOrcamento());
			}
			
			if (this.proposta.getFichaResumo() != null) {
				uploadArquivoProposta(this.proposta.getFichaResumo(), this.proposta.getNomeArquivoFichaResumo());
			}
			
		}
		startView(true);

		return null;
	}
	
	public void download() throws IOException {
		File file = new File(this.folder + "\\" + this.targetDownload);
		String fileName = file.getName();		
		String contentType =  Files.probeContentType(file.toPath());
		int contentLength = (int) file.length();

		FacesContext fc = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();

	    response.reset(); 
	    response.setContentType(contentType); 
	    response.setContentLength(contentLength);
	    response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); 

	    OutputStream output = response.getOutputStream();
	    Files.copy(file.toPath(), output);

	    fc.responseComplete(); 
	}
	
	private void definirNomesArquivos(Proposta p) {
		p.setNomeArquivoTermoReferencia(p.getId().toString() 
				+ ".termo-referencia" 
				+ obterExtensaoArquivo(this.proposta.getTermoReferencia().getSubmittedFileName()));   
		
		this.proposta.setNomeArquivoTermoReferencia(p.getNomeArquivoTermoReferencia());
		
		p.setNomeArquivoCronogramaFisicoFinanceiro(p.getId().toString() 
				+ ".cronograma-fisico-financeiro"
				+ obterExtensaoArquivo(this.proposta.getCronogramaFisicoFinanceiro().getSubmittedFileName()));
		
		this.proposta.setNomeArquivoCronogramaFisicoFinanceiro(p.getNomeArquivoCronogramaFisicoFinanceiro());
		
		p.setNomeArquivoPlanilhaOrcamento(p.getId().toString() 
				+ ".planilha-orçamento"
				+ obterExtensaoArquivo(this.proposta.getPlanilhaOrcamento().getSubmittedFileName()));
		
		this.proposta.setNomeArquivoPlanilhaOrcamento(p.getNomeArquivoPlanilhaOrcamento());
		
		p.setNomeArquivoFichaResumo(p.getId().toString() 
				+ ".ficha-resumo"
				+ obterExtensaoArquivo(this.proposta.getFichaResumo().getSubmittedFileName()));
		
		this.proposta.setNomeArquivoFichaResumo(p.getNomeArquivoFichaResumo());

	}
	
	private void uploadArquivosProposta(Proposta p) {
		uploadArquivoProposta(this.proposta.getTermoReferencia(), this.proposta.getNomeArquivoTermoReferencia());
		uploadArquivoProposta(this.proposta.getCronogramaFisicoFinanceiro(), this.proposta.getNomeArquivoCronogramaFisicoFinanceiro());
		uploadArquivoProposta(this.proposta.getPlanilhaOrcamento(), this.proposta.getNomeArquivoPlanilhaOrcamento());
		uploadArquivoProposta(this.proposta.getFichaResumo(), this.proposta.getNomeArquivoFichaResumo());
	}
	
	private void uploadArquivoProposta(Part file, String nomeArquivo) {
		uploadFile(file, nomeArquivo);
	}
	
	private void uploadFile(Part arquivo, String nomeArquivo) {
		try (InputStream input = arquivo.getInputStream()) {
			Files.copy(input, new File(folder, nomeArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }	
	}
	
	private String obterExtensaoArquivo(String nomeArquivo) {
		return nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());		
	}
	
	private void startView(boolean setInfo) {
		this.restProposta = new PropostaRESTClient();
		this.idProposta = null;
		this.proposta = new Proposta();
		this.proposta.setInstituicao(new Instituicao());
		this.proposta.setSubPDC(new SubPDC());
		this.proposta.setTiposProposta(new ArrayList<TipoProposta>());
			
		if (setInfo)
			setInfo();
	}

	private void setInfo() {
		this.setPropostas(this.restProposta.findAll());
		this.setInstituicoes();
		this.setSubpdcs();
		this.setTiposProposta();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public void setIdProposta(Long idProposta) {
		this.idProposta = idProposta;
	}

	public PropostaRESTClient getRestProposta() {
		return restProposta;
	}

	public void setRestProposta(PropostaRESTClient restProposta) {
		this.restProposta = restProposta;
	}

	public Proposta getProposta() {
		return proposta;
	}

	public void setProposta(Proposta proposta) {
		this.proposta = proposta;
	}

	public List<Proposta> getPropostas() {
		return propostas;
	}

	public void setPropostas(List<Proposta> propostas) {
		this.propostas = propostas;
	}

	public InstituicaoRESTClient getRestInstituicao() {
		return restInstituicao;
	}

	public void setRestInstituicao(InstituicaoRESTClient restInstituicao) {
		this.restInstituicao = restInstituicao;
	}
	
	public void setInstituicoes() {
		this.restInstituicao = new InstituicaoRESTClient();
		List<Instituicao> instituicoesBase = this.restInstituicao.findAll();
		List<SelectItem> instituicoes = new ArrayList<>();

		for (Instituicao i : instituicoesBase) 
		{
			instituicoes.add(new SelectItem(i.getId(), i.getNome()));
		}
		
		this.instituicoes = instituicoes;
	}
	
	public List<SelectItem> getInstituicoes() { 
		return instituicoes;
	}

	public List<SelectItem> getSubpdcs() {
		return subpdcs;
	}

	public void setSubpdcs() {
		this.restSubPDC = new SubPDCRESTClient();
		List<SubPDC> subPDCBase = this.getRestSubPDC().findAll();
		List<SelectItem> subpdcs = new ArrayList<>();
		
		for (SubPDC s : subPDCBase) {
			subpdcs.add(new SelectItem(s.getId(), s.getTitulo()));
		}
		
		this.subpdcs = subpdcs;
	}

	public SubPDCRESTClient getRestSubPDC() {
		return restSubPDC;
	}

	public void setRestSubPDC(SubPDCRESTClient restSubPDC) {
		this.restSubPDC = restSubPDC;
	}
	
	public TipoProposta[] getTiposPropostasSelecionados() {
		return tiposPropostasSelecionados;
	}

	public void setTiposPropostasSelecionados(TipoProposta[] tiposPropostasSelecionados) {
		this.tiposPropostasSelecionados = tiposPropostasSelecionados;
	}

	public TipoProposta[] getTiposPropostasSelecionadosValue() {
		this.tiposPropostasSelecionados = new TipoProposta[this.tiposProposta.size()];

		for(int i = 0; i < this.tiposProposta.size(); i++) {
			tiposPropostasSelecionados[i] = this.tiposProposta.get(i);
		}

		return this.tiposPropostasSelecionados;
	}
	
	public String getTiposPropostasSelecionadosInString() {
		return Arrays.toString(this.tiposPropostasSelecionados);
	}

	public List<TipoProposta> getTiposProposta() {
		return tiposProposta;
	}

	public void setTiposProposta() {
		this.restTipoProposta = new TipoPropostaRESTClient();
		this.tiposProposta  = this.restTipoProposta.findAll();		
	}

	public String getConsulta() {
		return consulta;
	}

	public void setConsulta(String consulta) {
		this.consulta = consulta;
	}

	public String getTargetDownload() {
		return targetDownload;
	}

	public void setTargetDownload(String targetDownload) {
		this.targetDownload = targetDownload;
	}

}
