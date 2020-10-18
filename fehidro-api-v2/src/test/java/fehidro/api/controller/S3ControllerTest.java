package fehidro.api.controller;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class S3ControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@Ignore
	public void uploadOk() throws Exception {
		File fileTest = new File("C:\\Users\\FCamara\\Desktop\\teste-1.pdf");
		
        MockMultipartFile arquivo = new MockMultipartFile("file", "teste-1.pdf", "multipart/form-data", FileUtils.readFileToByteArray(fileTest));
		
        mockMvc.perform(MockMvcRequestBuilders
        		.multipart("/awss3/upload")
        		.file(arquivo))
		.andExpect(MockMvcResultMatchers.status().isCreated());
        
	}
	
	@Test
	public void downloadOk() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders
				.get("/awss3/download")
				.param("nomeArquivo", "teste-1.pdf"))
		.andExpect(MockMvcResultMatchers.header().string("Content-Disposition",
                "attachment; filename=\"teste-1.pdf\""));
	}

}
