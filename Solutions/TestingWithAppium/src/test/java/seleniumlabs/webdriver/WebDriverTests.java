package seleniumlabs.webdriver;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

//Create RemoteWebDriver instance and connect to the Appium server
//It will launch the Calculator App in Android Device using the configurations
//specified in Desired Capabilities

public class WebDriverTests  {
    private WebDriver driver;
    //private AndroidDriver<WebElement> sdriver;

    @BeforeClass
    public void setUp() throws IOException {
    	//globalSetup();
    	DesiredCapabilities capabilities = new DesiredCapabilities();
        
    	capabilities.setCapability("deviceName", "Em Nexus S API 25");
        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("automationName","UiAutomator2");
        
        // You cannot name both browserName and appPackage/appActivity capabilities
        //   use browserName if you are running a web app on the mobile platform
        //   use appPackage/appActivity if you are running a native or hybrid app
        //
        //capabilities.setCapability("appPackage","com.android.chrome"); 
        //capabilities.setCapability("appActivity","com.google.android.apps.chrome.Main"); 
        capabilities.setCapability("browserName", "Chrome");
    
        driver = new AppiumDriver<WebElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        //driver = new AndroidDriver<WebElement>(getServiceUrl(), capabilities);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

    @Test()
    public void testCreateWebSession() throws URISyntaxException, InterruptedException, MalformedURLException {
        driver.get(new URI("http://www.google.com").toString());
        String title = driver.getTitle();
        Assert.assertEquals(title, "Google");
        
    	WebDriver.Navigation nav = driver.navigate();
    	nav.to("http://seleniumhq.github.io/selenium/docs/api/java/");
    	System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());

    	URL url = new URL("http://newtours.demoaut.com/");
    	nav.to(url);
    	System.out.println("Displaying page " + driver.getTitle() + driver.getCurrentUrl());
    }
}
