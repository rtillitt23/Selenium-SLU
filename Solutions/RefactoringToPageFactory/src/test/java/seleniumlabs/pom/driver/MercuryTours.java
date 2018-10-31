package seleniumlabs.pom.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumlabs.pom.pages.FlightFinderPage;
import seleniumlabs.pom.pages.FlightSelectorPage;
import seleniumlabs.pom.pages.HomePage;
import seleniumlabs.pom.pages.PassengerInformationPage;

public class MercuryTours {
	public static final String appURL = "http://newtours.demoaut.com/";
	private WebDriver driver;
	private HomePage homePage = null;
	private FlightFinderPage flghtFinderPg = null;
	private FlightSelectorPage flghtSelectPg = null;
	private PassengerInformationPage passInfoPg = null;

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
		Assert.assertTrue(flghtFinderPg.onPage(), "Could not Login to Flight Finder page");

		// One Flight Finder Page
		// ===============================
		flghtFinderPg.setTripType(FlightFinderPage.RoundTrip);
		flghtFinderPg.setFlight(2, "Frankfurt", "Paris", "11", "20", "12", "1");
		flghtFinderPg.setClass(FlightFinderPage.FirstClass);
		flghtFinderPg.setAirline(FlightFinderPage.UnifiedAirlines);

		flghtSelectPg = flghtFinderPg.sumbitFlightCriteria();
		Assert.assertTrue(flghtSelectPg.onPage(), "Could not Login to Flight Selector page");
		Thread.sleep(2000); // wait so you can see what happened

		// One Mercury Tours Flight Selector
		// ==================================
		flghtSelectPg.selectDepartureAirline(FlightSelectorPage.UnifiedAirlines);
		flghtSelectPg.selectReturnAirline(FlightSelectorPage.UnifiedAirlines);
		
		passInfoPg = flghtSelectPg.sumbitFlightSelections();
		Assert.assertTrue(passInfoPg.onPage(), "Could not access Passenger Info page");
		Thread.sleep(2000); // wait so you can see what happened

		driver.quit();
	}

	public void printTable(String header, WebElement table) {
		System.out.println("Printing : " + header);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (WebElement row : rows) {
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				System.out.println("\t Cell type: " + cell.getAttribute("type") + "   value: ["
						+ cell.getAttribute("value") + "]");
			}
		}

	}
}
