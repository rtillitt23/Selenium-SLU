package seleniumlabs.advuserinteractions;

import java.net.MalformedURLException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class AdvancedUserInteractions {
	public static void main(String[] args) throws InterruptedException, MalformedURLException {

		WebDriver driver = new ChromeDriver();
		driver.get("http://jqueryui.com/droppable/");
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		System.out.println("Focus in Window " + driver.getTitle() + " : " + driver.getWindowHandle());

		System.out.println("\nUsing Mousing to perform drag and drop");
		dragWithMouse(driver);

		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);

		System.out.println("\nRefreshed and Using dragNDrop to perform drag and drop");
		dragWithDragNDrop(driver);
		
		Thread.sleep(2000);
		driver.quit();
	}

	private static void dragWithMouse(WebDriver driver) {

		WebElement iFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iFrame); // switch WebDriver to this frame

		System.out.println("Now Focus in Window " + driver.getTitle() + " : " + driver.getWindowHandle());

		WebElement boxA = driver.findElement(By.id("draggable"));
		WebElement boxB = driver.findElement(By.id("droppable"));

		System.out.println("Before drag A at : " + boxA.getLocation() 
					      + "           B at : " + boxB.getLocation());

		Actions builder = new Actions(driver);
		Action mousing = builder
				.clickAndHold(boxA)
				.moveToElement(boxB)
				.release(boxA)
				.build();
		mousing.perform();

		System.out.println("After drag A at : " + boxA.getLocation()
				         + "           B at : " + boxB.getLocation());
	}


	private static void dragWithDragNDrop(WebDriver driver) {

		WebElement iFrame = driver.findElement(By.className("demo-frame"));
		driver.switchTo().frame(iFrame); // switch WebDriver to this frame

		System.out.println("Now Focus in Window " + driver.getTitle() + " : " + driver.getWindowHandle());

		WebElement boxA = driver.findElement(By.id("draggable"));
		WebElement boxB = driver.findElement(By.id("droppable"));

		System.out.println("Using Mousing to perform drag and drop");
		System.out.println("Before drag A at : " + boxA.getLocation() 
					+ "           B at : " + boxB.getLocation());

		Actions builder = new Actions(driver);
		Action dragNDrop = builder
				.dragAndDrop(boxA, boxB)
				.build();

		dragNDrop.perform();

		System.out.println("After drag A at : " + boxA.getLocation()
					+ "           B at : " + boxB.getLocation());
		
	}
}
