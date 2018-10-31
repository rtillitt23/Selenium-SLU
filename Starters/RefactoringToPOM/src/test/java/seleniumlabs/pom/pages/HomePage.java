package seleniumlabs.pom.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;


public class HomePage extends TopPage {
	static String Url = "http://newtours.demoaut.com/";
	static String HomePageTitle = "Welcome: Mercury Tours";
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean loadPage() {
		driver.manage().window().maximize();
		driver.get(Url);
		return onHomePage();
	}

	public boolean onHomePage()  {
		return driver.getTitle().equals(HomePageTitle);
	}
	
	public FlightFinderPage login(String userID, String pwd) {
		WebElement userTextInput = driver.findElement(By.name("userName"));
		WebElement passwdTextInput = driver.findElement(By.name("password"));
		userTextInput.sendKeys(userID);
		passwdTextInput.sendKeys(pwd);	
		
		userTextInput.submit();
		return new FlightFinderPage(driver);
	}

}
