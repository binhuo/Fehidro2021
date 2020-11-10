package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

 public final class Utils {

	public static String selectedOptionById(WebDriver driver, String idElement) {
		 Select select = new Select(driver.findElement(By.id(idElement)));
		 WebElement option = select.getFirstSelectedOption();
		 return option.getText();
	}
	
	public static String attributeById(WebDriver driver, String idElement, String attribute) {
		return driver.findElement(By.id(idElement)).getAttribute(attribute);
	}
}
