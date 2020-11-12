import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import java.util.*;

public class relatorioSubPDC01_test {
  private WebDriver driver;
  JavascriptExecutor js;
  
  @Before
  public void setUp() {
	System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver_86.exe");
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
  }
  
  @After
  public void tearDown() {
    driver.quit();
  }
  
  @Test
  public void relatorioSubPDC01() {
    driver.get("http://localhost:8080/fehidro-client/login/index.xhtml");
    driver.manage().window().setSize(new Dimension(1346, 708));
    driver.findElement(By.id("formLogin:txtLogin")).click();
    driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vanessa.lessa");
    driver.findElement(By.id("formLogin:txtSenha")).sendKeys("1234");
    driver.findElement(By.id("formLogin:submitLogin")).click();
    driver.findElement(By.linkText("Relatório")).click();
    driver.findElement(By.id("btnRelatorioPacial")).click();
    driver.findElement(By.id("formProposta:txtSubPDC")).click();
    {
      WebElement dropdown = driver.findElement(By.id("formProposta:txtSubPDC"));
      dropdown.findElement(By.xpath("//option[. = 'Disponibilidade']")).click();
    }
    driver.findElement(By.id("formProposta:txtSubPDC")).click();
    driver.findElement(By.id("formProposta:btnCadastrar")).click();
    
    WebElement element = driver.findElement(By.xpath("/html/body/div/div/table/tbody/tr[1]/td[1]"));
    String teste = element.getText();
    String esperado =  "Revestimento de Trecho do Canal Guaramar";
    Assert.assertEquals(esperado,teste);
    
    
  }
}

