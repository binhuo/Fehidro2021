import org.junit.Test;
import org.junit.Before;
import org.junit.Ignore;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PdcTest {
	private WebDriver driver;
	private String tituloPdcEsperado = "PDC", numeroSubPdcEsperado = "1", tituloSubpdcEsperado = "SubPDC",
			descricaoMetaSubpdcEsperado = "Meta 1", acaoMetaSubpdcEsperado = "Ação da Meta 1",
			valorMetaSubpdcEsperado = "R$ 6.220,00";
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
	public void cadastraPdc() throws InterruptedException {
		driver.get("http://localhost:8080/fehidro-client/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("42387203690");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("j_idt57:btnNovoPDC")).click();
		driver.findElement(By.id("formPDC:txtTituloPDC")).sendKeys(tituloPdcEsperado);
		driver.findElement(By.id("formPDC:btnNovoSubPDC")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("formPDC:subpdc:0:txtNumeroSubPDC")).sendKeys(numeroSubPdcEsperado);
		driver.findElement(By.id("formPDC:subpdc:0:txtTituloSubPDC")).sendKeys(tituloSubpdcEsperado);
		driver.findElement(By.id("formPDC:subpdc:0:j_idt71")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtDescricaoMeta")).sendKeys(descricaoMetaSubpdcEsperado);
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtAcaoMeta")).sendKeys(acaoMetaSubpdcEsperado);
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtValorMeta")).sendKeys(valorMetaSubpdcEsperado);
		
		driver.findElement(By.id("formPDC:btnCadastrar")).click();
		
		Thread.sleep(5000);
		assertEquals("", driver.findElement(By.id("formPDC:txtTituloPDC")).getText());
	}

	@Test
	public void existePdcCadastrado() throws InterruptedException {
		driver.get("http://localhost:8080/fehidro-client/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("42387203690");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		Thread.sleep(4000);
		String actual = driver.findElement(By.cssSelector("tr:nth-child(2) > td:nth-child(1)")).getText();
		Assert.assertEquals(tituloPdcEsperado, actual);
	}
	
	@Test
	public void consultaPdc() throws InterruptedException {
		driver.get("http://localhost:8080/fehidro-client/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("42387203690");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		Thread.sleep(2000);
		driver.findElement(By.name("tabela:1:j_idt64:j_idt65")).click();

		String tituloPdc = driver.findElement(By.id("formPDC:txtTituloPDC")).getAttribute("value");
		String numeroSubpdc = driver.findElement(By.id("formPDC:subpdc:0:txtNumeroSubPDC")).getAttribute("value");
		String tituloSubpdc = driver.findElement(By.id("formPDC:subpdc:0:txtTituloSubPDC")).getAttribute("value");

		String descricaoMetaSubpdc = driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtDescricaoMeta"))
				.getAttribute("value");
		String acaoMetaSubpdc = driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtAcaoMeta")).getAttribute("value");
		String valorMetaSubpdc = driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtValorMeta"))
				.getAttribute("value");

		assertEquals(tituloPdcEsperado, tituloPdc);
		assertEquals(numeroSubPdcEsperado, numeroSubpdc);
		assertEquals(tituloSubpdcEsperado, tituloSubpdc);
		assertEquals(descricaoMetaSubpdcEsperado, descricaoMetaSubpdc);
		assertEquals(acaoMetaSubpdcEsperado, acaoMetaSubpdc);
		assertEquals(valorMetaSubpdcEsperado, valorMetaSubpdc);
	}
}
