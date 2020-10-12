package fehidro.rest.client;

import java.io.File;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.MultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;


public class S3RESTClient extends BaseRESTClient {
	
	public void upload(File file) {
		try {
			MultiPart multiPart = new MultiPart();
	        multiPart.setMediaType(MediaType.MULTIPART_FORM_DATA_TYPE);
	        FileDataBodyPart bodyPart = new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE);	        
	        multiPart.bodyPart(bodyPart);
	        
			ClientBuilder.newBuilder()
				    .register(MultiPartFeature.class)
				    .build()
				    .target(REST_WEBSERVICE_URL + REST_AWSS3_URL + "upload")
					.request()
					.header(HttpHeaders.AUTHORIZATION, authToken)
					.post(Entity.entity(multiPart, multiPart.getMediaType()));
								
		} catch(Exception e) {
			e.printStackTrace();			
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
	

