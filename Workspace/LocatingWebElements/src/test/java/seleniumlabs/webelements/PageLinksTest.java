package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLinksTest {
  @Test
  public void checkLinks() {
	  
	  WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver = new EdgeDriver();
		//WebDriver driver = getDriver();
		
		driver.get("http://newtours.demoaut.com/");         // open browser and navigate
		
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");
		Assert.assertEquals(driver.getCurrentUrl(), "http://newtours.demoaut.com/");
 
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		
		checkLinksCorrectly(driver, links);		

						
						driver.quit();
			}

			

		private void checkLinksCorrectly(WebDriver driver, List<WebElement> links) {
				
				String[] linkTexts = new String[links.size()];
				int i=0;
				
				for (WebElement welm :links)  {          // avoid StaleElementReferenceException
					linkTexts[i++] =  welm.getText();
				}
				for (String txt : linkTexts)  {
					driver.findElement(By.linkText(txt)).click();    // get fresh reference to WebElement
					
					System.out.println("Testing Link \"" + txt + "\"" + " Directs to : " + driver.getTitle());
					
					String underConstruction = "Under Construction: Mercury Tools";
					if (driver.getTitle().equals(underConstruction))  
		{
						System.out.println("\t Directs to Under Construction.");
					}


					else if (driver.getTitle().equals("404 Not Found"))  {
						System.out.println("\t Link Is Broken");		
					}



					else  {
						System.out.println("\t Link Is Working");
					}


					driver.navigate().back();
				}
			}

 }
  
  		
  

