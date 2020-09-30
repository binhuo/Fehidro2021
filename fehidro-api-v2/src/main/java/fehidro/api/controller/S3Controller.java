package fehidro.api.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fehidro.api.services.S3Service;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/awss3")
public class S3Controller {

	@Autowired
	private S3Service service;

	@ApiOperation(value = "Realiza o upload de um arquivo no AWS S3")
	@PostMapping(value = "/upload")
	public ResponseEntity<Void> uploadFile(@RequestParam(name = "file") MultipartFile file) {
		try {
		URI uri = service.upload(file);
		return ResponseEntity.created(uri).build();}
		catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@ApiOperation(value = "Realiza o download de um arquivo no AWS S3")
	@GetMapping(value = "/download", produces="application/json")
    public ResponseEntity<ByteArrayResource> downloadFile(@RequestParam(value= "nomeArquivo") final String keyName) {
        final byte[] data = service.downloadFile(keyName);
        final ByteArrayResource resource = new ByteArrayResource(data);
        return ResponseEntity
                .ok()
                .contentLength(data.length)
                .header("Content-type", "application/octet-stream")
                .header("Content-disposition", "attachment; filename=\"" + keyName + "\"")
                .body(resource);
    }

}
