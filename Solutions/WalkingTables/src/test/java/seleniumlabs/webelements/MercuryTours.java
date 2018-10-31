package seleniumlabs.webelements;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import seleniumlabs.testutils.TableTestUtilities;

public class MercuryTours {
	public static final String appURL = "http://newtours.demoaut.com/";

	@Test
	public void bookFlight() throws InterruptedException {
		WebDriver driver = new ChromeDriver();

		driver.get(appURL);
		Assert.assertEquals("Welcome: Mercury Tours", driver.getTitle());

		// One Mercury Tours Home Page
		// ===============================
		// --------------------------------
		// Code for Walking Tip Table
		//
		//		List<WebElement> ts = driver.findElements(By.tagName("table"));
		//		WebElement tipTable = null;
		//		for (WebElement tab : ts) {
		//			if (tab.getAttribute("width").equals("277")) {
		//				tipTable = tab;
		//				break;
		//			}
		//		}
		//		Assert.assertNotNull(tipTable, "Couldn't find tip table");
		//		System.out.println("Tips Table Cells: ");
		//		List<WebElement> rs = tipTable.findElements(By.tagName("tr"));
		//		int j = 1;
		//		for (WebElement row : rs) {
		//			System.out.println(" Row-" + j++);
		//			List<WebElement> cells = row.findElements(By.tagName("td"));
		//			for (WebElement cell : cells) {
		//				System.out.println("\t Cell width: " + cell.getAttribute("width") + " text: [" + cell.getText() + "]");
		//			}
		//		}

		// End Code for Walking Tip Table
		// --------------------------------
		WebElement name = driver.findElement(By.name("userName"));
		WebElement passwrd = driver.findElement(By.name("password"));
		// WebElement signIn = driver.findElement(By.name("login"));

		name.sendKeys("mercury");
		passwrd.sendKeys("mercury");
		name.submit();
		// signIn.click();

		Assert.assertEquals("Find a Flight: Mercury Tours:", driver.getTitle());

		// One Flight Finder Page
		// ===============================
		// locate all our WebElements first
		List<WebElement> radiotripType = driver.findElements(By.name("tripType"));
		Select numPassengers = new Select(driver.findElement(By.name("passCount")));
		Select fromPort = new Select(driver.findElement(By.name("fromPort")));
		Select fromMonth = new Select(driver.findElement(By.name("fromMonth")));
		Select fromDate = new Select(driver.findElement(By.name("fromDay")));
		Select toPort = new Select(driver.findElement(By.name("toPort")));
		Select toMonth = new Select(driver.findElement(By.name("toMonth")));
		Select toDate = new Select(driver.findElement(By.name("toDay")));
		List<WebElement> radioCategory = driver.findElements(By.name("servClass"));
		Select airline = new Select(driver.findElement(By.name("airline")));

		// use WebElements to manipulate the page
		if (radiotripType.get(1).isSelected()) { // if 1-way trip selected
			radiotripType.get(0).click();        // want round trip
		} else {
			radiotripType.get(0).click();
		}
		numPassengers.selectByValue("2");       // 2 passengers
		fromPort.selectByValue("Frankfurt");    // leaving from Frankfurt on Nov
												// 20th
		fromMonth.selectByValue("11");
		fromDate.selectByValue("20");
		toPort.selectByValue("Paris");          // arriving in Paris Dec 1st
		toMonth.selectByValue("12");
		toDate.selectByValue("1");
		if (radioCategory.get(0).isSelected() || radioCategory.get(1).isSelected()) {
			radioCategory.get(2).click();       // I want to fly first class
		} else {
			radioCategory.get(2).click();
		}
		Assert.assertTrue(radioCategory.get(2).isSelected());

		airline.selectByIndex(2);

		// Thread.sleep(2000); // wait so you can see what happened
		radioCategory.get(0).submit();          // on to flight finder page

		Assert.assertEquals("Select a Flight: Mercury Tours", driver.getTitle());

		// One Mercury Tours Flight Selector
		// ==================================
		// find form with name='results' which holds our flight selection tables
		//
		WebElement form = driver.findElement(By.name("results"));
		Assert.assertNotNull(form, "Couldn't find form");
                                                // print the tables so we can see what we're working with
		List<WebElement> tables = form.findElements(By.tagName("table"));
		System.out.println("All the table in the Form named Result ");
		int i = 0;
		HashMap<WebElement, Boolean> seenTables = new HashMap<>();
		for (WebElement welm : tables) {
			TableTestUtilities.printTable("Form Results Table " + (i++) + ": ", 
					                       welm, seenTables, "   ");
		}
		
		// work on our departure flight, use XPath
		//
		List<WebElement> depFlights = driver.findElements(By.xpath("//input[@name='outFlight']"));
		Assert.assertNotNull(depFlights, "Couldn't find list of departing flights");

		for (WebElement cell : depFlights) {
			if (cell.getAttribute("type").equals("radio") && cell.getAttribute("value").contains("Unified Airlines")) {
				System.out.println(
						"\nBooking Departure - Cell type: " + cell.getAttribute("type") + "   value: [" + cell.getAttribute("value") + "]");
				cell.click();                   // select the flight
				break;
			}
		}                                
		// work on our return flight, use XPath
		//
		WebElement uaFlight = driver.findElement(By.xpath("//input[@name='inFlight'][contains(@value, 'Unified Airlines')]"));
		uaFlight.click();
//		List<WebElement> retFlights = driver.findElements(By.xpath("//input[@name='inFlight']"));
//		Assert.assertNotNull(retFlights, "Couldn't find list of returning flights");
//
//		for (WebElement cell : retFlights) {
//
//			if (cell.getAttribute("type").equals("radio") && cell.getAttribute("value").contains("Unified Airlines")) {
//				System.out.println(
//						"Booking Return - Cell type: " + cell.getAttribute("type") + "   value: [" + cell.getAttribute("value") + "]");
//				cell.click();                   // select the flight
//				break;
//			}
//		}
		Thread.sleep(1000);
		form.submit();                          // submit the form
		Assert.assertEquals("Book a Flight: Mercury Tours", driver.getTitle());

		Thread.sleep(3000);
		driver.quit();
	}

}
