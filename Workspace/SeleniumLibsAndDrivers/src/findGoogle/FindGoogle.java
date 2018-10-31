package findGoogle;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class FindGoogle {

	public static void main(String[] args)  throws InterruptedException {
			//WebDriver driver = new FirefoxDriver();
			WebDriver driver = new ChromeDriver();
		
			driver.get("http://www.google.com/");
			
			WebElement inputbox = driver.findElement(By.name("q"));
			inputbox.sendKeys("Selenium"); 
			Thread.sleep(5000);
			
			WebElement btnSearch = driver.findElement(By.name("btnK"));
			btnSearch.click();
			
			Thread.sleep(5000);
			
			
	}

}
