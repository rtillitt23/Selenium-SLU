package seleniumlabs.webdriver;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.chrome.ChromeDriver;


public class WebDriverTests {

	private static void printCookies(Options opt) {
		Set<Cookie> cookies = opt.getCookies();
		System.out.println("Cookie on this website include: ");
		for (Cookie c : cookies) {
				System.out.println("\t " + c);
		}
		System.out.println("\n");
	}
	
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		//WebDriver driver = new InternetExplorerDriver();
		//WebDriver driver = new EdgeDriver();
		//WebDriver driver = getDriver();
		
		driver.get("http://puppies.herokuapp.com");         // open browser and navigate
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		
		//Thread.sleep(5000);                             // wait so you can see what happened
		
		WebDriver.Options opt = driver.manage();
				
		Cookie cook = new Cookie("Reggie", "Tillitt");
		opt.addCookie(cook);
		printCookies(opt); 
		
		System.out.println("Retrieving just my added cookie: " + opt.getCookieNamed("Reggie") + "\n");
		
		opt.deleteCookieNamed("Reggie");
		printCookies(opt); 
		
		WebDriver.Navigation nav = driver.navigate(); 
		nav.to("http://www.thetestroom.com/webapp/");
		System.out.println("New website 1" + driver.getCurrentUrl()); 
		Thread.sleep(5000);
		
		nav.to("Http://seleniumhq.github.io/selenium/docs/api/java/");
		System.out.println("New website 2 " + driver.getCurrentUrl());
		Thread.sleep(5000);
			
		nav.to("http://newtours.demoaut.com");
		System.out.println("New website 3 " + driver.getCurrentUrl());
		Thread.sleep(5000);
		
		nav.back();
		System.out.println("Back URL 1: " +driver.getCurrentUrl());
		Thread.sleep(5000);
		
		nav.back();
		System.out.println("Back URL 2: " +driver.getCurrentUrl());
		Thread.sleep(5000);
		
		nav.back();
		System.out.println("Back URL 3: " +driver.getCurrentUrl());
		Thread.sleep(5000);
		
		getScreenshotAs(OutputType<X> target)
		
		
		driver.quit();
		
	}

}
