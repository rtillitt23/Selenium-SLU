package seleniumlabs.webdriver;



import java.net.MalformedURLException;

import java.net.URL;

import java.util.Set;


import org.openqa.selenium.Cookie;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebDriver.Options;

import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.Test;



public class WebDriverTests {

	
private static void printCookies(Options opt) {

		Set<Cookie> cookies = opt.getCookies();      // retrieve website cookies
		System.out.println("Cookie on this website include:");
		for (Cookie c : cookies) {
			System.out.println("\t " + c);
		}
		System.out.println("\n");
	}

	@Test()
	public static void testWebDriver() throws InterruptedException, MalformedURLException {
	//WebDriver driver = new FirefoxDriver();
	WebDriver driver = new ChromeDriver();
	
	driver.get("http://puppies.herokuapp.com/");   // open browser and navigate
	System.out.println("Displaying page " + driver.getTitle() + "  URL: " + driver.getCurrentUrl());
	
	// Play with WebDriver.Options
	//--------------------------------
	WebDriver.Options options = driver.manage();
													// add cookie to site
	Cookie cook = new Cookie("Brigitte", "Birze");
	options.addCookie(cook);
	printCookies(options);
	
	System.out.println("Just my Cookie: " + options.getCookieNamed("Brigitte"));
	
	options.deleteCookie(cook);
	printCookies(options);
	
	// Play with WebDriver.Navigation
	//--------------------------------
	WebDriver.Navigation nav = driver.navigate();
	nav.to("http://www.thetestroom.com/webapp/");
	System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);

	URL url = new URL("http://newtours.demoaut.com/");
	nav.to(url);
	System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);

	nav.to("http://seleniumhq.github.io/selenium/docs/api/java/");
	System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);

	nav.back(); // mecury tours
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.back(); // zoo adoption
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.back(); // Sally's Puppies
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.back(); // blank screen
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.back(); // blank screen
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);

	nav.forward(); // Sally's Puppies
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.forward(); // zoo adoption
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.forward(); // mecury tours
	Thread.sleep(100);
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	nav.forward(); // Selenium JavaDoc
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.forward(); // Selenium JavaDoc
	System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
	Thread.sleep(100);
	nav.forward(); // Still at Selenium JavaDoc

	// Thread.sleep(5000);
	// driver.close();
	driver.quit();
	}
}
