package seleniumlabs.pom.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class FlightFinderPage  extends TopPage {
	static String PageTitle = "Find a Flight: Mercury Tours:";
	
	@FindBy(name = "tripType")
	List<WebElement> radiotripType;

	@FindBy(name = "servClass")
	List<WebElement> radioCategory;

	@FindBy(name = "passCount")
//	Select numPassengers;
	WebElement numPassengers;

	@FindBy(name = "fromPort")
//	Select fromPort;
	WebElement fromPort;

	@FindBy(name = "fromMonth")
//	Select fromMonth;
	WebElement fromMonth;

	@FindBy(name = "fromDay")
	//Select fromDate;
	WebElement fromDate;

	@FindBy(name = "toPort")
//	Select toPort;
	WebElement toPort;

	@FindBy(name = "toMonth")
//	Select toMonth;
	WebElement toMonth;

	@FindBy(name = "toDay")
//	Select toDate;
	WebElement toDate;
	
	@FindBy(name = "findFlights")
//	Select continueBut;
	WebElement continueBut;
	
	@FindBy(name = "airline")
//	Select airlineSel;
	WebElement airlineSel;
	
	public FlightFinderPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);      // initialize PageFactory
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}
	
	public static int RoundTrip = 0;
	public static int OneWay = 1;

	public void setTripType(int tripType) {
		radiotripType.get(tripType).click();
	}

	public static int EconClass = 0;
	public static int BizClass = 1;
	public static int FirstClass = 2;

	public void setClass(int srvClass) {
		radioCategory.get(srvClass).click();
	}
	
	public static int BlueSkiesAirlines = 1;
	public static int UnifiedAirlines = 2;
	public static int PangnaAirlines = 3;

	public void setAirline(int airline) {
		Select sel = new Select(airlineSel);
		sel.selectByIndex(airline);
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
		radiotripType.get(1).submit();
		return new FlightSelectorPage(driver);
	}
	// private helper methods, stay DRY
	//----------------------------------
	private void setNumberPassengers(int numPass) {
		Select sel = new Select(numPassengers);
		sel.selectByIndex(numPass-1);
		//numPassengers.selectByValue(numPass);
	}

	private void setDepartureCity(String city) {
		Select sel = new Select(fromPort);
		sel.selectByValue(city);
	}

	private void setDepartureDateTime(String month, String date) {
		Select sel1 = new Select(fromMonth);
		Select sel2 = new Select(fromDate);
		//Select sel2 = fromDate;
		sel1.selectByValue(month);
		sel2.selectByValue(date);
	}

	private void setReturnCity(String city) {
		Select sel = new Select(toPort);
		sel.selectByValue(city);
	}

	private void setReturneDateTime(String month, String date) {
		Select sel1 = new Select(toMonth);
		Select sel2 = new Select(toDate);
		sel1.selectByValue(month);
		sel2.selectByValue(date);		
	}

}
