package fehidro.rest.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.http.Part;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.Boundary;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;


public class S3RESTClient extends BaseRESTClient {
	
	public boolean upload(Part file) {
		try {
			MediaType contentType = MediaType.MULTIPART_FORM_DATA_TYPE;
			contentType = Boundary.addBoundary(contentType);
			
			ClientBuilder.newBuilder()
				    .register(MultiPartFeature.class)
				    .build()
				    .target(REST_WEBSERVICE_URL + REST_AWSS3_URL + "upload")
					.request()
					.header(HttpHeaders.AUTHORIZATION, authToken)
					.post(Entity.entity(file, contentType));
					
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public byte[] download(String nomeArquivo) {
		
		try {
			byte[] resul = ClientBuilder.newClient().
			target(REST_WEBSERVICE_URL + REST_AWSS3_URL + "download").
			queryParam("nomeArquivo", nomeArquivo).
			request(MediaType.APPLICATION_JSON).
			header(HttpHeaders.AUTHORIZATION, authToken).get().
			readEntity(byte[].class);
			
			return resul;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
	

