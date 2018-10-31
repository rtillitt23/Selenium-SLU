package seleniumlabs.webelements;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MercuryTours {
	public static final String appURL = "http://newtours.demoaut.com/";
	public static final String flightFindURL = "http://newtours.demoaut.com/mercuryreservation.php";

	@Test
	public void bookFlight() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(appURL);              
                                         // assert we are in the right place
		Assert.assertEquals("Welcome: Mercury Tours", driver.getTitle());
		Assert.assertEquals(appURL, driver.getCurrentUrl());
		
		// One Mercury Tours Home Page
		//===============================
		WebElement name = driver.findElement(By.name("userName"));
		WebElement passwrd = driver.findElement(By.name("password"));
		// WebElement signIn = driver.findElement(By.name("login"));

		name.sendKeys("mercury");
		passwrd.sendKeys("mercury");
		name.submit();
		// signIn.click();               // use a button... the hard way  :~(

		Assert.assertTrue(driver.getCurrentUrl().startsWith(flightFindURL));
		Assert.assertEquals(driver.getTitle(), "Find a Flight: Mercury Tours:");

		// On Flight Finder Page
		//===============================

		Thread.sleep(3000);
		driver.quit();
	}
	
	@Test
	public void test2()   {
		System.out.println("Running Test 2!");
	}

	@Test
	public void test3()   {
		System.out.println("Running Test 3!");
	}

	@Test
	public void test4()   {
		System.out.println("Running Test 4!");
	}
}
