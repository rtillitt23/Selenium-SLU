package seleniumlabs.pom.pages;

import org.openqa.selenium.WebDriver;

public class PassengerInformationPage extends TopPage {
	static String PageTitle = "Book a Flight: Mercury Tours";

	public PassengerInformationPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}

}
