package fehidro.rest.client;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.client.Invocation.Builder;

import fehidro.control.ItemRelatorio;
import fehidro.model.Proposta;
import fehidro.model.Relatorio;
import fehidro.model.SubPDC;


public class RelatorioRESTClient extends BaseRESTClient{
	
	public static String REST_RELATORIO_URL = "relatorio/";
	//public static String REST_RELATORIO_FINAL_XLSX_URL = "relatorio/xlsx/final/";
	//public static String REST_RELATORIO_SUPDC_XLSX_URL = "relatorio/xlsx/subPdc/";
	
//	public Response relatorioXLSXFinal(ItemRelatorio[] itensRelatorio) {
//		
//		System.out.println("Sending request");
//		Client c = ClientBuilder.newClient();
//		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_RELATORIO_FINAL_XLSX_URL + itensRelatorio);
//		Builder b = t.request().header(HttpHeaders.AUTHORIZATION, authToken);
//		Response r = b.get();
//		System.out.println("Received response");
//		
//		return r;
//	}
//	
//	public Response relatorioXLSXSubPdc(ItemRelatorio[] itensRelatorio) {
//
//		Client c = ClientBuilder.newClient();
//		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_RELATORIO_SUPDC_XLSX_URL + itensRelatorio);
//		Builder b = t.request().header(HttpHeaders.AUTHORIZATION, authToken);
//		Response r = b.get();
//		
//		return r;
//	}
	
	public Response relatorioFinal() {

		Client c = ClientBuilder.newClient();
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_RELATORIO_URL + "final");
		Builder b = t.request().header(HttpHeaders.AUTHORIZATION, authToken);
		Response r = b.get();
		
		return r;
	}
	
	public Response relatorioSubPdc(SubPDC subpdc) {

		Client c = ClientBuilder.newClient();
		WebTarget t = c.target(REST_WEBSERVICE_URL + REST_RELATORIO_URL + "subPdc/" + subpdc);
		Builder b = t.request().header(HttpHeaders.AUTHORIZATION, authToken);
		Response r = b.get();
		
		return r;
	}
}


