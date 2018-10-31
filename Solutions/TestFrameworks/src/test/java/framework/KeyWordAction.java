package framework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class KeyWordAction {
	static WebDriver driver;

	public void openBrowser(String browserName) {
		System.out.printf("openBrowser():  Browser: %s \n", browserName);
		browserName = browserName.toLowerCase();

		try {
			switch (browserName) {
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "ie":
				driver = new InternetExplorerDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.err.println("Given Unknown Driver: " + browserName + ", using Chrome");
				driver = new ChromeDriver();
			}
		} catch (WebDriverException e) {
			System.err.println(e.getMessage());
			throw e;
		}
	}

	public void goToURL(String URL) {
		System.out.printf("goToURL():  URL: %s \n", URL);
		driver.get(URL);
	}

	private By buildBy(String locatorType, String value) {
		By by;
		switch (locatorType) {
		case "id":
			by = By.id(value);
			break;
		case "name":
			by = By.name(value);
			break;
		case "xpath":
			by = By.xpath(value);
			break;
		case "css":
			by = By.cssSelector(value);
			break;
		case "linkText":
			by = By.linkText(value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(value);
			break;
		default:
			throw new IllegalArgumentException("Given Unknown Locator: " + locatorType + " with value: " + value);
		}
		return by;
	}

	public void enterText(String locType, String locVal, String text) {
		System.out.printf("enterText(): Locator Type:%s Locator Value: %s Test Data: %s\n", locType, locVal, text);
		try {
			By locator = buildBy(locType, locVal);
			WebElement element = driver.findElement(locator);
			element.sendKeys(text);
		} catch (NoSuchElementException e) {
			System.err.format("enterText :" + e);
			System.err.println("Given Locator: " + locType + " with value: " + locVal + " and text to enter: " + text);
			throw e;
		}
	}

	public void clickOnElement(String locType, String locVal) {
		System.out.printf("clickOnElement(): Locator Type:%s Locator Value: %s\n", locType, locVal);
		try {
			By locator = buildBy(locType, locVal);
			WebElement element = driver.findElement(locator);
			element.click();
		} catch (NoSuchElementException e) {
			System.err.format("clickOnElement: " + e);
			System.err.println("Given Locator: " + locType + " with value: " + locVal);
			throw e;
		}
	}

	public void clickOnRadio(String locType, String locVal, String data) {
		System.out.printf("clickOnRadio(): Locator Type:%s Locator Value: %s Test Data: %s\n", locType, locVal, data);
		int selection = new Integer(data);
		try {
			By locator = buildBy(locType, locVal);
			List<WebElement> rButtons = driver.findElements(locator);
			rButtons.get(selection).click();
		} catch (NoSuchElementException e) {
			System.err.format("clickOnRadio: " + e);
			System.err.format("No Radio Buttons Found to click" + e);
			System.err.println("Given Locator: " + locType + " with value: " + locVal);
			throw e;
		}	
	}

	public void selectValue(String locType, String locVal, String value) {
		System.out.printf("clickOnRadio(): Locator Type:%s Locator Value: %s Sel Value: %s\n", locType, locVal, value);
		try {
			By locator = buildBy(locType, locVal);
			Select select = new Select(driver.findElement(locator));
			select.selectByValue(value);
		} catch (NoSuchElementException e) {
			System.err.format("clickOnRadio: " + e);
			System.err.format("No Radio Buttons Found to click" + e);
			System.err.println("Given Locator: " + locType + " with value: " + locVal);
			throw e;
		}	
	}

	public void selectIndex(String locType, String locVal, String index) {
		System.out.printf("clickOnRadio(): Locator Type:%s Locator Value: %s Sel Idx: %s\n", locType, locVal, index);
		int idx = new Integer(index);
		try {
			By locator = buildBy(locType, locVal);
			Select select = new Select(driver.findElement(locator));
			select.selectByIndex(idx);
		} catch (NoSuchElementException e) {
			System.err.format("clickOnRadio: " + e);
			System.err.format("No Radio Buttons Found to click" + e);
			System.err.println("Given Locator: " + locType + " with value: " + locVal);
			throw e;
		}	
	}

	public void closeBrowser() {
		System.out.println("closeBrowser():");
		driver.quit();
	}

	public void assertEquals(String actual, String verifyData, String msg) {
		System.out.printf("assertTrue(): actual: %s verify: %s\n", actual, verifyData);
		actual = actual.toLowerCase();

		switch (actual) {
		case "pagetitle":
			actual = driver.getTitle();
			break;
		default:
			System.err.println("Given Unknown Actual Type: " + actual);
		}
		Assert.assertEquals(actual, verifyData, msg);
	}

}