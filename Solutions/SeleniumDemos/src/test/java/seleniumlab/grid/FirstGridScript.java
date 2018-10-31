package seleniumlab.grid;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirstGridScript {

	public static void main(String[] args) throws InterruptedException, MalformedURLException {
		URL myHub = new URL("http://localhost:4444/wd/hub");
		
		DesiredCapabilities capability = DesiredCapabilities.firefox();
		capability.setBrowserName("firefox");
		capability.setPlatform(Platform.WINDOWS);
		
		WebDriver driver = new RemoteWebDriver(myHub, capability);

		driver.get("http://awful-valentine.com/");         // open browser and navigate
		System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
		
		WebElement info = driver.findElement(By.linkText("More Info"));
		info.click();
		System.out.println("Now Displaying page " + driver.getTitle() + driver.getCurrentUrl());

		driver.close();
	}
}
