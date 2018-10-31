package seleniumlabs.waits;

import java.net.MalformedURLException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitTesting {
	static private String dir = "file:///C:/SeleniumLabs/Starters/";
	static private String htmlFile = dir + "WaitAlerts.html";

	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		WebDriver driver = new ChromeDriver();
		
		// Explicit Wait: Can specify the Condition to Wait on
		WebDriverWait wait = new WebDriverWait(driver, 10);
		
		driver.get(htmlFile);
		System.out.println("Displaying page " + driver.getTitle());

		driver.findElement(By.id("needWait")).click();			// Open Alert
									
		wait.until(ExpectedConditions.alertIsPresent());		// wait for Alert Popup
		
		Alert alert = driver.switchTo().alert();

		alert.accept();

		Thread.sleep(3000);
		driver.quit();
	}
}
