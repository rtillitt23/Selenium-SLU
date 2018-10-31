package seleniumlabs.webelements;


import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.Assert; 

import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;



public class MercuryTours {
  @Test
public void bookFlight() throws InterruptedException {

			WebDriver driver = new ChromeDriver();
			//WebDriver driver = new InternetExplorerDriver();
			//WebDriver driver = new EdgeDriver();
			//WebDriver driver = getDriver();
			
			driver.get("http://newtours.demoaut.com/");         // open browser and navigate
			
			Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
			
			
				driver.manage().window().maximize();		
			
				driver.findElement(By.name("userName")).click();
			    driver.findElement(By.name("userName")).clear();
			    driver.findElement(By.name("userName")).sendKeys("mercury");
			    driver.findElement(By.name("password")).click();
			    driver.findElement(By.name("password")).clear();
			    driver.findElement(By.name("password")).sendKeys("mercury");
			    driver.findElement(By.name("login")).submit();
			    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Type:'])[1]/following::input[1]")).click();
			    driver.findElement(By.name("passCount")).click();
			    new Select(driver.findElement(By.name("passCount"))).selectByVisibleText("2");
			    driver.findElement(By.name("passCount")).click();
			    driver.findElement(By.name("fromPort")).click();
			    new Select(driver.findElement(By.name("fromPort"))).selectByVisibleText("Frankfurt");
			    driver.findElement(By.name("fromPort")).click();
			    driver.findElement(By.name("fromMonth")).click();
			    new Select(driver.findElement(By.name("fromMonth"))).selectByVisibleText("November");
			    driver.findElement(By.name("fromMonth")).click();
			    driver.findElement(By.name("fromDay")).click();
			    new Select(driver.findElement(By.name("fromDay"))).selectByVisibleText("20");
			    driver.findElement(By.name("fromDay")).click();
			    
			    driver.findElement(By.name("toPort")).click();
			    new Select(driver.findElement(By.name("toPort"))).selectByVisibleText("Paris");
			    driver.findElement(By.name("toPort")).click();
			    driver.findElement(By.name("toMonth")).click();
			    new Select(driver.findElement(By.name("toMonth"))).selectByVisibleText("December");
			    driver.findElement(By.name("toMonth")).click();
			    driver.findElement(By.name("toDay")).click();
			    new Select(driver.findElement(By.name("toDay"))).selectByVisibleText("1");
			    driver.findElement(By.name("toDay")).click();
			    
			    driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Airline:'])[1]/preceding::input[1]")).click();
			    driver.findElement(By.name("airline")).click();
			    new Select(driver.findElement(By.name("airline"))).selectByVisibleText("Unified Airlines");
			    driver.findElement(By.name("airline")).click();
					       
			    
			   driver.findElement(By.name("findFlights")).submit();
			    
  
			   
			   Thread.sleep(5000);
			   
			   
			//    driver.quit();

		
  }

	  
	
 }
  
  


