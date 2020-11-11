import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
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
	public void realizarLogin() {
		driver.get("https://portalcbhbs.herokuapp.com/login/index.xhtml");
		driver.findElement(By.id("formLogin:txtLogin")).sendKeys("vivian.teste1");
		driver.findElement(By.id("formLogin:txtSenha")).sendKeys("unisantos123");
		driver.findElement(By.id("formLogin:submitLogin")).click();
	}
}
