import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;

import org.junit.Assert;

import utils.Utils;

public class PropostaTest {
	private WebDriver driver;

	JavascriptExecutor js;
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver_86.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@After
	public void tearDown() {
		driver.quit();
	}
	
	@Test
	@Ignore
	public void existePropostaCadastrada() throws InterruptedException {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("menuProposta")).findElement(By.linkText("Propostas")).click();
		String expected = "Prefeitura de Santos";
		String actual = driver.findElement(By.cssSelector("td:nth-child(1)")).getText();
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	@Ignore
	public void consultaProposta() throws InterruptedException {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		Thread.sleep(4000);
		driver.findElement(By.id("menuProposta")).findElement(By.linkText("Propostas")).click();
		Thread.sleep(4000);
	    driver.findElement(By.name("tabela:0:j_idt65:j_idt66")).click();
		Thread.sleep(4000);

		String intituicao = Utils.selectedOptionById(driver, "formProposta:txtInstituicao"), 
				nomeProjeto = Utils.attributeById(driver, "formProposta:txtNomeProjeto", "value"),
				subpdc = Utils.selectedOptionById(driver, "formProposta:txtSubPDC");
		List<WebElement> tiposProposta = driver.findElement(By.className("custom-checkbox")).findElements(By.name("formProposta:j_idt74"));
		
		String instituicaoEsperada = "Prefeitura de Santos";
		String nomeProjetoEsperado = "Projeto 1";
				String subpdcEsperado = "SubPDC";
		List<String> tiposPropostaEsperados = new ArrayList<String>();
		tiposPropostaEsperados.addAll(Arrays.asList("2"));
		
		for(WebElement e : tiposProposta) {
			if(e.isSelected()) {
				boolean result = tiposPropostaEsperados.stream().anyMatch(t -> t.equals(e.getAttribute("value")));
				Assert.assertTrue(result);
			}
		}
		
		Assert.assertEquals(instituicaoEsperada, intituicao);
		Assert.assertEquals(nomeProjetoEsperado, nomeProjeto);
		Assert.assertEquals(subpdcEsperado, subpdc);
	}
	
	@Test
	public void cadastraProposta() throws InterruptedException {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("menuProposta")).findElement(By.linkText("Propostas")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("j_idt55:btnNovaProposta")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("formProposta:txtInstituicao")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("formProposta:txtInstituicao"));
	      dropdown.findElement(By.xpath("//option[. = 'Governo do Estado de São Paulo']")).click();
	    }
	    driver.findElement(By.id("formProposta:txtInstituicao")).click();
	    driver.findElement(By.id("formProposta:txtNomeProjeto")).sendKeys("Projeto 4");
	    driver.findElement(By.id("formProposta:txtSubPDC")).click();
	    {
	      WebElement dropdown = driver.findElement(By.id("formProposta:txtSubPDC"));
	      dropdown.findElement(By.xpath("//option[. = 'A']")).click();
	    }
	    
	    driver.findElement(By.cssSelector(".custom-control:nth-child(1) > .custom-control-label")).click();
	    driver.findElement(By.cssSelector(".custom-control:nth-child(2) > .custom-control-label")).click();
	    
	    String filePath = "C:\\Users\\FCamara\\Documents\\GitHub\\fehidro\\fehidro-tests\\src\\test\\resources\\arquivos\\teste.pdf";
	    
	    driver.findElement(By.id("formProposta:fileTermoReferencia")).sendKeys(filePath);
	    
	    driver.findElement(By.id("formProposta:fileCronogramaFinanceiro")).sendKeys(filePath);
	    
	    driver.findElement(By.id("formProposta:filePlanilhaOrcamento")).sendKeys(filePath);
	    
	    driver.findElement(By.id("formProposta:fileFichaResumo")).sendKeys(filePath);
	    
	    driver.findElement(By.id("formProposta:btnCadastrar")).click();
	    Thread.sleep(4000);
	}
}
