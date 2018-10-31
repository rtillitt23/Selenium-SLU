package seleniumlabs.webdriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverTests {

	private static void printCookies(Options opt) {
		Set<Cookie> cookies = opt.getCookies();      // retrieve website cookies
		System.out.println("Cookie on this website include:");
		for (Cookie c : cookies) {
			System.out.println("\t " + c);
		}
		System.out.println("\n");
	}

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		// WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();

		driver.get("http://puppies.herokuapp.com/"); // open browser and
													 // navigate
		System.out.println("Displaying page " + driver.getTitle() + " : " + driver.getCurrentUrl());

		//  Use the Options Reference
		// -------------------------------
		WebDriver.Options opt = driver.manage();    // get Option reference
													// add cookie to site
		Cookie cook = new Cookie("Brigitte", "Birze");
		opt.addCookie(cook);
		printCookies(opt);

		System.out.println("Retreiving Just my added cookie: " + opt.getCookieNamed("Brigitte") + "\n");

		opt.deleteCookieNamed("Brigitte");
		printCookies(opt);

		//  Use the Navigation Reference
		// -------------------------------
		WebDriver.Navigation nav = driver.navigate();
		nav.to("http://www.thetestroom.com/webapp/");
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);

		URL url = new URL("http://newtours.demoaut.com/");
		nav.to(url);
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);

		nav.to("http://seleniumhq.github.io/selenium/docs/api/java/");
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);

		nav.back(); // mecury tours
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.back(); // zoo adoption
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.back(); // Sally's Puppies
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.back(); // blank screen
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.back(); // blank screen
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);

		nav.forward(); // Sally's Puppies
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.forward(); // zoo adoption
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.forward(); // mecury tours
		Thread.sleep(1000);
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		nav.forward(); // Selenium JavaDoc
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.forward(); // Selenium JavaDoc
		System.out.println("At URL: " + driver.getTitle() + driver.getCurrentUrl());
		Thread.sleep(1000);
		nav.forward(); // Still at Selenium JavaDoc

		// Thread.sleep(5000);
		//driver.close();
		driver.quit();
	}
}
