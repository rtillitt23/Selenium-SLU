package seleniumdemos;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AjaxWithJQuery {

	public static void waitForAjaxControls(WebDriver driver) {
		int timeoutInSeconds = 10;
		try {
			if (driver instanceof JavascriptExecutor) {
				JavascriptExecutor jsDriver = (JavascriptExecutor)driver;
				for (int i = 0; i < timeoutInSeconds; i++) {
					Object numAjaxRequests = jsDriver.executeScript("return jQuery.active");
																	// return should be a number
					if (numAjaxRequests instanceof Long) {
						Long n = (Long)numAjaxRequests;
						System.out.println("Number of active jquery AJAX controls: " + n);
						if (n.longValue() == 0L)
							break;
					}
					Thread.sleep(1000);
				}
			} else {
				System.out.println("Web driver: " + driver + " can't run javascript.");
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

	public static void main(String[] args) {

		WebDriver driver =  new ChromeDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_ajax_callback");
		
		driver.switchTo().frame(driver.findElement(By.id("iframeResult")));

		WebDriverWait wait = new WebDriverWait(driver, 5);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(".//*[@id='demo']")));

		/* Read the text that appears in the AJAX text control. */
		WebElement ajaxControl = driver.findElement(By.xpath(".//*[@id='demo']"));
		String ajaxTextFirstPara = ajaxControl.getText().trim();

		/* Click on the AJAX button control. */
		driver.findElement(By.xpath("html/body/button")).click();

		/* Wait for the new content to appear in the AJAX text control. */
		By newAjaxcontrol = By.xpath(".//*[@id='demo']/p");
		Wait<WebDriver> newwait = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(5, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class);
		newwait.until(ExpectedConditions
				.presenceOfElementLocated(newAjaxcontrol));

		/*
		 * Wait for the second paragraph value to get visible in the AJAX text
		 * control.
		 */
		WebElement secondParagraph = driver.findElement(By
				.xpath(".//*[@id='demo']/p[2]"));
		wait.until(ExpectedConditions.visibilityOf(secondParagraph));

		/* Get the text from the first paragraph after the AJAX call. */
		String ajaxNewTextFirstPara = driver
				.findElement(By.xpath(".//*[@id='demo']/p[1]")).getText()
				.trim();

		/* Get the text from the second paragraph after the AJAX call. */
		String ajaxTextSecondPara = secondParagraph.getText().trim();
		String expectedTextInSecondPara = "AJAX is a technique for creating fast and dynamic web pages.";

		/* Verify the first paragraph text shouldn't match the new text. */
		Assert.assertNotEquals(ajaxTextFirstPara, ajaxNewTextFirstPara);

		/* Verify the second paragraph text must match with the new text. */
		Assert.assertEquals(ajaxTextSecondPara, expectedTextInSecondPara);
		waitForAjaxControls(driver);
		
		driver.quit();
	}

}
