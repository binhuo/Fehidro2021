package fehidro.control;

public class RelatorioFacade {
	
	public void gerarRelatorioFinal() {
		//TODO
		String out = gerarRelatorio(new RelatorioFinal());
		
		//OQUE � FEITO COM A SAIDA DO RELATORIO? COMO CONECTA AO HTML?
		0/0
	}
	
	public void gerarRelatorioParcial() {
		//TODO
		String out = gerarRelatorio(new RelatorioParcial());;
		
		//OQUE � FEITO COM A SAIDA DO RELATORIO? COMO CONECTA AO HTML?
	}
	
	public void gerarRelatorioDesclassificados() {
		//TODO
		String out = gerarRelatorio(new RelatorioDesclassificados());
		
		//OQUE � FEITO COM A SAIDA DO RELATORIO? COMO CONECTA AO HTML?
	}
	
	private String gerarRelatorio(Relatorio r)
	{
		return r.gerar();
	}
	
}
