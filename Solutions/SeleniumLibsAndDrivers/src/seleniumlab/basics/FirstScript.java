package seleniumlab.basics;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class FirstScript {
	
	public static void main(String[] args) throws InterruptedException {
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new EdgeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver = getDriver();
		
		driver.get("http://awful-valentine.com/");         // open browser and navigate
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		
		WebElement rightArrow = driver.findElement(By.id("right-arrow"));
		Thread.sleep(1000);                                 // wait so you can see what happened
		rightArrow.click();            						// see next card

		Thread.sleep(1000);                                 // wait so you can see what happened
		rightArrow.click();            						// see next card
		Thread.sleep(1000);                                 // wait so you can see what happened
		rightArrow.click();            						// see next card
		Thread.sleep(1000);                                 // wait so you can see what happened

		WebElement info = driver.findElement(By.linkText("More Info"));
		info.click();
		System.out.println("Now Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		 
		Thread.sleep(3000);                                // wait so you can see what happened
		driver.close();
	}
	
	// alternative way to set location of a WebDriver
	//
	public WebDriver getDriver() {
//		System.setProperty("webdriver.edge.driver","C:\\SeleniumLabs\\SeleniumServers\\MicrosoftWebDriver.exe");
//		System.setProperty("webdriver.gecko.driver", "C:\\SeleniumLabs\\SeleniumServers\\geckodriver.exe");
		System.setProperty("webdriver.chrome.driver", "C:\\SeleniumLabs\\SeleniumServers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		return driver;
	}
}


