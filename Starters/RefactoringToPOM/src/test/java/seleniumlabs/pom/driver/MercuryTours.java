package seleniumlabs.pom.driver;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumlabs.pom.pages.HomePage;
import seleniumlabs.pom.pages.FlightFinderPage;


public class MercuryTours {
	public static final String appURL = "http://newtours.demoaut.com/";
	private WebDriver driver;
	private HomePage homePage = null;
	private FlightFinderPage flghtFinderPg = null;
	
	public void buildDriver() {
		// driver = new FirefoxDriver();
		// driver = new InternetExplorerDriver();
		driver = new ChromeDriver();
		Assert.assertNotNull(driver, "Could Not Instantiate Driver");
	}
	
	@Test
	public void bookFlight() throws InterruptedException {
		buildDriver();
		homePage = new HomePage(driver);
		Assert.assertTrue(homePage.loadPage(), "Could not load home page");

		// One Mercury Tours Home Page
		// ===============================
		flghtFinderPg = homePage.login("mercury", "mercury");
		Assert.assertTrue(flghtFinderPg.onPage(), "Could not Login to FlightFinder page");
		
		// One Flight Finder Page
		// ===============================
//		List<WebElement> radiotripType = driver.findElements(By
//				.name("tripType"));
		List<WebElement> radiotripType = driver.findElements(By
				.name("tripType"));

		Select numPassengers = new Select(driver.findElement(By
				.name("passCount")));
		Select fromPort = new Select(driver.findElement(By.name("fromPort")));
		Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
		Select fromDate = new Select(driver.findElement(By.name("fromDay")));
		Select toPort = new Select(driver.findElement(By.name("toPort")));
		Select toMonth = new Select(driver.findElement(By.name("toMonth")));
		Select toDate = new Select(driver.findElement(By.name("toDay")));
		List<WebElement> radioCategory = driver.findElements(By
				.name("servClass"));
		Select airline = new Select(driver.findElement(By.name("airline")));

		if (radiotripType.get(1).isSelected()) { // 1-way selected
			radiotripType.get(0).click(); // want round trip
		} else {
			radiotripType.get(0).click();
		}
		numPassengers.selectByValue("2");
		fromPort.selectByValue("Frankfurt");
		fromMonth.selectByValue("11");
		fromDate.selectByValue("20");
		toPort.selectByValue("Paris");
		toMonth.selectByValue("12");
		toDate.selectByValue("1");
		if (radioCategory.get(0).isSelected()
				|| radioCategory.get(1).isSelected()) {
			radioCategory.get(2).click();         // want first class
		} else {
			radioCategory.get(2).click();
		}
		airline.selectByIndex(2);

		radioCategory.get(0).submit();            // on to flight finder page
		assertEquals("Select a Flight: Mercury Tours", driver.getTitle());

		Thread.sleep(2000);                    // wait so you can see what happened

		// One Mercury Tours Flight Selector
		// ==================================

		WebElement form = driver.findElement(By.name("results"));
		Assert.assertNotNull(form, "Couldn't find form");

		List<WebElement> tables = form.findElements(By.tagName("table"));
		
		WebElement departures = tables.get(0);
		Assert.assertNotNull(departures, "Couldn't find departures table");

		List<WebElement> depFlight = departures.findElements(By.name("outFlight"));
		Assert.assertNotNull(depFlight,	"Couldn't find list of departing flights");

		System.out.println("\t Booking Departure! ");
		for (WebElement cell : depFlight) {
				if (cell.getAttribute("type").equals("radio") &&
					cell.getAttribute("value").contains("Unified Airlines"))  {
				cell.click();            // select the flight
				break;
			}
		}

		WebElement returns = tables.get(2);
		Assert.assertNotNull(returns, "Couldn't find return table");

		List<WebElement> retFlight = returns.findElements(By.name("inFlight"));
		Assert.assertNotNull(retFlight,	"Couldn't find list of returning flights");

		System.out.println("\t Booking Return! ");
		for (WebElement cell : retFlight) {
			if (cell.getAttribute("type").equals("radio") &&
					cell.getAttribute("value").contains("Unified Airlines"))  {
				cell.click();            // select the flight
				break;
			}
		}
		returns.submit();                // submit the form
		assertEquals("Book a Flight: Mercury Tours", driver.getTitle());

		Thread.sleep(2000);                    // wait so you can see what happened

		driver.quit();
	}
	
	public void printTable(String header, WebElement table)  {
		System.out.println("Printing : " + header);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows)  {
			List<WebElement> cells = row.findElements(By.tagName("td"));
			
			for (WebElement cell : cells) {
				System.out.println("\t Cell type: " + cell.getAttribute("type")
						+ "   value: [" + cell.getAttribute("value") + "]");
				}	
		}

	}
}
