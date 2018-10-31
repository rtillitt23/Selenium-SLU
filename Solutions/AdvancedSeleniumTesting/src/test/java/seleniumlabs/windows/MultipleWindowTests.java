package seleniumlabs.windows;

import java.net.MalformedURLException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MultipleWindowTests {
	
	
	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		WebDriver driver = new ChromeDriver();
		
		handleAlerts(driver);					// Part 2: Alerts
		//switchWindows(driver);				// Part 3: Multiple WIndows

		driver.quit();
	}

	private static void handleAlerts(WebDriver driver) throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/javascript_alerts");
		System.out.println("Handle Alerts at: " + driver.getTitle());

		List<WebElement> webels = driver.findElements(By.tagName("button"));
		Alert alert = null;
		
		System.out.println("Alert Buttons: ");
		for (WebElement el : webels)  {
			String alertType = el.getAttribute("onClick");
			System.out.println("\t" + alertType);
			el.click();
			alert = driver.switchTo().alert();
			Thread.sleep(500);
			
			switch(alertType)  {
			case "jsAlert()":						// handle simple alert
				alert.accept();						// click alert OK 
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			case "jsConfirm()":						// handle confirmation alert
				alert.dismiss();					// click on cancel, accept() hits ok
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			case "jsPrompt()":						// handle prompt alert
				alert.sendKeys("Filling In Text Box!");
				alert.accept();
				System.out.println("\t\t Results: " + driver.findElement(By.id("result")).getText());
				break;
			default:
				System.err.println("\t No Case for button element: " + el.getAttribute("onClick"));
			}
		}
	}

	private static void switchWindows(WebDriver driver) throws InterruptedException {

		driver.get("http://toolsqa.wpengine.com/automation-practice-switch-windows/");
		System.out.println("\nHandle Multiple Windows at: " + driver.getTitle());

		String parentWin = driver.getWindowHandle();			// save parent window id
		System.out.println("Parent Window ID: " + parentWin);

		driver.findElement(By.id("button1")).click();			// Open new window

		Set<String> winIds = driver.getWindowHandles();
		System.out.println("Window Handles After Click: ");
		for (String handle1 : winIds) {
			driver.switchTo().window(handle1);
			System.out.println("\t" + handle1 + " - " + driver.getTitle());
		}		
		String newWin = winIds.toArray(new String[0])[1];		// parent window is index 0

		driver.switchTo().window(newWin);						// switch to new window
		// driver.switchTo().window(parentWin);                 // another way since we saved a handle to parent window
		System.out.println("In New Window: " + driver.getWindowHandle() + " - " + driver.getTitle());
		Thread.sleep(2000);										// so you can see what happended
		
		driver.switchTo().window(parentWin);					// switch back to parent
		System.out.println("Back in Parent Window: " + driver.getWindowHandle() + " - " + driver.getTitle());
	}
}
