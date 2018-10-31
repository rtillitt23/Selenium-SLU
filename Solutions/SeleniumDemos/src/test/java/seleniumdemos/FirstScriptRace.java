package seleniumdemos;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirstScriptRace {
	
	public static void main(String[] args) throws InterruptedException {
		//WebDriver driver = new InternetExplorerDriver();
		WebDriver driver = new FirefoxDriver();
		//WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.google.com");   // open browser and navigate
		                                       // do a Google search
		WebElement input = driver.findElement(By.name("q"));
		input.sendKeys("selenium");            
		WebElement search = driver.findElement(By.name("btnK"));

		search.click();                        // button covered by input drop down
                                               // get org.openqa.selenium.NoSuchElementException
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(5000);                    // wait so you can see what happened
		
		driver.close();                        // close browser window
	}
}

