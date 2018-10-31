package seleniumlabs.pom.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FlightFinderPage  extends TopPage {
	static String PageTitle = "Find a Flight: Mercury Tours:";

	
	public FlightFinderPage(WebDriver driver) {
		super(driver);
	}

	public boolean onPage()  {
		return driver.getTitle().equals(PageTitle);
	}

}
