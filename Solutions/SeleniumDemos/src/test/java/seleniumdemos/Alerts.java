package seleniumdemos;

import org.apache.http.auth.UsernamePasswordCredentials;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Alerts {
	
	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		driver.get("file:///C:/SeleniumLabs/Solutions/SeleniumDemos/popup.html");   
		                                      
		WebElement alertBut = driver.findElement(By.id("tryBut"));
		alertBut.click();    

		Alert alert = driver.switchTo().alert();
		System.out.println("Alert Text = " + alert.getText() + ", hit Accept!");
		Thread.sleep(1000);                // wait so you can see what happened
		alert.accept();
		
		alertBut.click();    
		alert = driver.switchTo().alert();
		System.out.println("Alert Text = " + alert.getText() + ", hit Dismiss!");
		Thread.sleep(2000);                    // wait so you can see what happened
		alert.dismiss();	

		Thread.sleep(2000);                    // wait so you can see what happened
		
		driver.close();                        // close browser window
		
	}
}

