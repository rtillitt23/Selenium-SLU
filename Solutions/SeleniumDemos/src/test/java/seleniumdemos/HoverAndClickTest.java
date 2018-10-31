/*We can perform hover action of mouse cursor using moveToElement(element) method.
 * moveToElement(element) is method of Action class.
 */
package seleniumdemos;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HoverAndClickTest {
	WebDriver driver;
	Actions builder;

	@Test
	public void hoverTest() throws InterruptedException {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo/trigger-hover.html");

		WebElement hoverMeBtn = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));

		builder = new Actions(driver);
		System.out.println(hoverMeBtn.getText());

		builder.moveToElement(hoverMeBtn).pause(2000);
		builder.build().perform();


		WebElement delete = driver.findElement(By.xpath("html/body/ul/li[5]"));
		Action act = builder
				.moveToElement(delete)
				.pause(1000)
				.click(delete)
				.build();
		
		act.perform();						//Do It!
		
		Thread.sleep(1000);

		Alert alert = driver.switchTo().alert();
		alert.accept();
		Thread.sleep(1000);
	}

	@BeforeMethod
	public void beforeMethod() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@AfterMethod
	public void afterMethod() {
		driver.quit();
	}

}
