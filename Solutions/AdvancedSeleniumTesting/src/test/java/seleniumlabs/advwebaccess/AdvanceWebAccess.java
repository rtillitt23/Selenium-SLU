package seleniumlabs.advwebaccess;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AdvanceWebAccess {

	public static void main(String[] args) throws InterruptedException, IOException {
		WebDriver driver = new ChromeDriver();

		driver.get("http://www.jqueryscript.net/demo/Feature-rich-Custom-jQuery-Context-Menu-Plugin-contextMenu/");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());

		contextMenu(driver, wait);

		driver.quit();
	}

	private static void takeScreenShot(WebDriver driver, String fName) throws IOException {

		File scrshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrshotFile, new File(fName));
	}

	
	private static void contextMenu(WebDriver driver, WebDriverWait wait) throws InterruptedException, IOException {
		
		WebElement hotRClickArea = driver.findElement(By.className("context-menu-one"));
		wait.until(ExpectedConditions.elementToBeClickable(hotRClickArea));
		
		takeScreenShot(driver, "BeforeContext.png");
		
		Action rCtxtMenu = new Actions(driver)
				.contextClick(hotRClickArea)
				.build();
		rCtxtMenu.perform();

		takeScreenShot(driver, "DuringContext.png");

		rCtxtMenu = new Actions(driver)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.sendKeys(Keys.ARROW_DOWN)
				.build();
		rCtxtMenu.perform();
		
		takeScreenShot(driver, "SelectionSet.png");
		Thread.sleep(1000); // wait so can see what happened

		rCtxtMenu = new Actions(driver)
				.sendKeys(Keys.ENTER)
				.build();
		rCtxtMenu.perform();
		
		Thread.sleep(1000); // wait so can see what happened
		
		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Says: " + alert.getText());
		alert.accept();
		takeScreenShot(driver, "AfterContext.png");
	}
}
