package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PageLinksTest {
	public static final String appURL = "http://newtours.demoaut.com/";
	public static final String underConstruction= "Under Construction: Mercury Tours" ;

	@Test
	public void checkLinks() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(appURL);
		Assert.assertEquals(driver.getTitle(), "Welcome: Mercury Tours");

		// One Mercury Tours Home Page
		// ===============================
		List<WebElement> links = driver.findElements(By.tagName("a"));
		System.out.println("Links: " + links);
		
		//checkLinksBadly(driver, links);
		checkLinksCorrectly(driver, links);		

		Thread.sleep(3000);
		driver.quit();
	}

	private void checkLinksBadly(WebDriver driver, List<WebElement> links) {
		
		for (WebElement welm :links)  {          
			System.out.println("Testing Link \"" + welm.getText() + "\"" + " Directs to : " + driver.getTitle());
			
			if (driver.getTitle().equals(underConstruction))  {
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

	private void checkLinksCorrectly(WebDriver driver, List<WebElement> links) {
		
		String[] linkTexts = new String[links.size()];
		int i=0;
		
		for (WebElement welm :links)  {          // avoid StaleElementReferenceException
			linkTexts[i++] =  welm.getText();
		}
		for (String txt : linkTexts)  {
			driver.findElement(By.linkText(txt)).click();    // get fresh reference to WebElement
			
			System.out.println("Testing Link \"" + txt + "\"" + " Directs to : " + driver.getTitle());
			
			if (driver.getTitle().equals(underConstruction))  {
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
