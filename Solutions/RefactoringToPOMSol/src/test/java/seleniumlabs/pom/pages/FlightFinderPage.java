package seleniumlabs.pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPage extends TopPage {
	static String PageTitle = "Find a Flight: Mercury Tours:";

	public FlightFinderPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage() {
		return driver.getTitle().equals(PageTitle);
	}

	public static int RoundTrip = 0;
	public static int OneWay = 1;

	public void setTripType(int tripType) {
		List<WebElement> radiotripType = driver.findElements(By.name("tripType"));
		radiotripType.get(tripType).click();
	}

	public static int EconClass = 0;
	public static int BizClass = 1;
	public static int FirstClass = 2;

	public void setClass(int srvClass) {
		List<WebElement> radioCategory = driver.findElements(By.name("servClass"));
		radioCategory.get(srvClass).click();
	}

	public static int BlueSkiesAirlines = 1;
	public static int UnifiedAirlines = 2;
	public static int PangnaAirlines = 3;

	public void setAirline(int airline) {
		Select airlineSel = new Select(driver.findElement(By.name("airline")));
		airlineSel.selectByIndex(airline);
	}

	public void setFlight(int numPass, String origin, String dest, 
						  String depMonth, String depDate, String retMonth, String retDate) {
		setNumberPassengers(numPass);
		setDepartureCity(origin); 
		setReturnCity(dest);
		setDepartureDateTime(depMonth, depDate);
		setReturneDateTime(retMonth, retDate);
	}
	
	public FlightSelectorPage sumbitFlightCriteria() {
		List<WebElement> radiotripType = driver.findElements(By.name("tripType"));
		radiotripType.get(1).submit();
		return new FlightSelectorPage(driver);
	}

	
	// private helper methods, stay DRY
	//----------------------------------
	private void setNumberPassengers(int numPass) {
		Select numPassengers = new Select(driver.findElement(By.name("passCount")));
		numPassengers.selectByIndex(numPass-1);
		//numPassengers.selectByValue(numPass);
	}

	private void setDepartureCity(String city) {
		Select fromPort = new Select(driver.findElement(By.name("fromPort")));
		fromPort.selectByValue(city);
	}

	private void setDepartureDateTime(String month, String date) {
		Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
		Select fromDate = new Select(driver.findElement(By.name("fromDay")));
		fromMonth.selectByValue(month);
		fromDate.selectByValue(date);
	}

	private void setReturnCity(String city) {
		Select toPort = new Select(driver.findElement(By.name("toPort")));
		toPort.selectByValue(city);
	}

	private void setReturneDateTime(String month, String date) {
		Select toMonth = new Select(driver.findElement(By.name("toMonth")));
		Select toDate = new Select(driver.findElement(By.name("toDay")));
		toMonth.selectByValue(month);
		toDate.selectByValue(date);
	}

}
