package seleniumdemos;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumWaits {

	private WebDriver driver;

	private void implicitWait() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://www.google.com");  

		WebElement input = driver.findElement(By.name("q"));
		input.sendKeys("selenium");
		WebElement search = driver.findElement(By.className("lsb "));
		search.click();

		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		driver.close(); // close browser window

	}

	public void fluentWait() throws InterruptedException {
		driver = new FirefoxDriver();

		driver.get("http://www.google.com"); // open browser and navigate
												// do a Google search
		WebElement input = driver.findElement(By.name("q"));
		// WebElement search = driver.findElement(By.name("btnK"));

		input.sendKeys("selenium"); // block visibility to search button

		WebElement search = driver.findElement(By.name("btnK"));
		Wait<WebElement> wait = new FluentWait<WebElement>(search)
				.pollingEvery(1, TimeUnit.SECONDS)
				.withTimeout(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);

		wait.until(new Function<WebElement, Boolean>() {
			public Boolean apply(WebElement webelem) {
				try {
					webelem.click();
					return true;
				} catch (WebDriverException ex) {
					System.out.println(ex.getMessage());
					return false;
				}
			}
		});

		// System.out.println("Search button visible :" + search.isDisplayed() +
		// " <" + search + ">");

		// search.click(); // button covered by input drop down

		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(5000); // wait so you can see what happened

		driver.close(); // close browser window
	}
	
	private void explicitWait() {
		driver = new FirefoxDriver();
		driver.get("http://www.google.com");  

		WebDriverWait wait = new WebDriverWait(driver, 10);   // timeout in sec

		WebElement input = driver.findElement(By.name("q"));
		input.sendKeys("selenium");
		
		WebElement search = wait
				.until(ExpectedConditions.presenceOfElementLocated(By.className("lsb ")));
		search.click();

		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		driver.close(); // close browser window

	}



	public static void main(String[] args) throws InterruptedException {
		SeleniumWaits selwait = new SeleniumWaits();
		selwait.implicitWait();
		//selwait.fluentWait();
		//selwait.explicitWait();
	}

}
