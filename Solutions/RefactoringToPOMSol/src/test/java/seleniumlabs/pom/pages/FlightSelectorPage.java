package seleniumlabs.pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FlightSelectorPage extends TopPage {
	static String PageTitle = "Select a Flight: Mercury Tours";

	public FlightSelectorPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}

	public static String UnifiedAirlines = "Unified Airlines";
	public static String PangnaAirlines = "PangnaAirlines";
	public static String BlueSkiesAirlines = "BlueSkiesAirlines";
	
	public void selectDepartureAirline(String airline) {
		WebElement form = driver.findElement(By.name("results"));
		if (form == null)
			throw new StaleElementReferenceException("FlightSelector Form");

		List<WebElement> tables = form.findElements(By.tagName("table"));
		WebElement departures = tables.get(0);
		if (departures == null)
			throw new StaleElementReferenceException("FlightSelector Departures Table");

		selectAirline(departures, airline, "outFlight");
	}

	public void selectReturnAirline(String airline) {
		WebElement form = driver.findElement(By.name("results"));
		if (form == null)
			throw new StaleElementReferenceException("FlightSelector Form");

		List<WebElement> tables = form.findElements(By.tagName("table"));
		WebElement returns = tables.get(2);

		if (returns == null)
			throw new StaleElementReferenceException("FlightSelector Returns Table");

		selectAirline(returns, airline, "inFlight");
	}

	private void selectAirline(WebElement flighLst, String airline, String flightType) {
		List<WebElement> depFlight = flighLst.findElements(By.name(flightType));
		if (depFlight == null)
			throw new StaleElementReferenceException("FlightSelector list of flights");

		System.out.println("\t Booking Departure! ");
		for (WebElement cell : depFlight) {
				if (cell.getAttribute("type").equals("radio") &&
					cell.getAttribute("value").contains(airline))  {
				cell.click();            // select the flight
				break;
			}
		}
	}

	public PassengerInformationPage sumbitFlightSelections() {
		WebElement form = driver.findElement(By.name("results"));
		form.submit();
		return new PassengerInformationPage(driver);
	}

}
