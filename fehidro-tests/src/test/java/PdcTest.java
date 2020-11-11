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
	public void existePdcCadastrado() {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		String expected = "PDC";
		String actual = driver.findElement(By.cssSelector("td:nth-child(1)")).getText();
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void consultaPdc() {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		driver.findElement(By.name("tabela:0:j_idt62:j_idt63")).click();

		String tituloPdcEsperado = "PDC", numeroSubPdcEsperado = "1", tituloSubpdcEsperado = "SubPDC",
				descricaoMetaSubpdcEsperado = "Meta 1", acaoMetaSubpdcEsperado = "Ação da Meta 1",
				valorMetaSubpdcEsperado = "R$ 6.220,00";

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

	@Test
	public void cadastraPdc() throws InterruptedException {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).click();
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
		
		driver.findElement(By.id("menuPDC")).findElement(By.linkText("PDCs e SubPDCs")).click();
		
		driver.findElement(By.id("j_idt55:btnNovoPDC")).click();
		driver.findElement(By.id("formPDC:txtTituloPDC")).sendKeys("Teste");
		driver.findElement(By.id("formPDC:btnNovoSubPDC")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("formPDC:subpdc:0:txtNumeroSubPDC")).sendKeys("1");
		driver.findElement(By.id("formPDC:subpdc:0:txtTituloSubPDC")).sendKeys("A");
		driver.findElement(By.id("formPDC:subpdc:0:j_idt69")).click();
		
		Thread.sleep(4000);
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtDescricaoMeta")).sendKeys("Descrição Meta SubPDC A");
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtAcaoMeta")).sendKeys("Ação Meta SubPDC A");
		driver.findElement(By.id("formPDC:subpdc:0:metas:0:txtValorMeta")).click();
		
		driver.findElement(By.id("formPDC:btnCadastrar")).click();
		
		Thread.sleep(5000);
		assertEquals("", driver.findElement(By.id("formPDC:txtTituloPDC")).getText());
	}

}
