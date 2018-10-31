package seleniumlabs.pom.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends TopPage {
	static String Url = "http://newtours.demoaut.com/";
	static String HomePageTitle = "Welcome: Mercury Tours";
	
	@FindBy(name = "userName")
	private WebElement userTextInput;
	
	@FindBy(name = "password")
	private WebElement passwdTextInput;
	
	public HomePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}

	public boolean loadPage() {
		//driver.manage().window().maximize();
		driver.get(Url);
		return onPage();
	}

	public boolean onPage()  {
		return driver.getTitle().equals(HomePageTitle);
	}
	
	public FlightFinderPage login(String userID, String pwd) {
		userTextInput.sendKeys(userID);
		passwdTextInput.sendKeys(pwd);	
		
		userTextInput.submit();
		return new FlightFinderPage(driver);
	}

}
