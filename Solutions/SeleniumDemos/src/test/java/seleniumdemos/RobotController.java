/*We can perform hover action of mouse cursor using moveToElement(element) method.
 * moveToElement(element) is method of Action class.
 */
package seleniumdemos;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RobotController {
	WebDriver driver;
	Robot robot;

	@Test
	public void uploadFileTest() throws InterruptedException, AWTException, IOException {
		
		// create file to upload and get it's absolute file name
		//
		File file = new File("FileToUpload.png");
		Path path = Paths.get(file.getAbsolutePath());
		if (Files.exists(path) == false)  {
			path = Files.createFile(path);
			System.out.println("Created file, path is : " + path);
		}
		Assert.assertNotNull(Files.exists(path));

		// then copy the file name to the O/S Clipboard using AWT library
		//
		StringSelection absFName = new StringSelection(path.toString());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(absFName, null);
		
		// Selenium Part: browse to page and click on Upload File button
		//
		driver.get("http://the-internet.herokuapp.com/upload");

		WebElement chooseFile = driver.findElement(By.id("file-upload"));
		chooseFile.click();                 // open the OS file upload window
		Thread.sleep(2000);                 // wait for windows to open
		
		// Need to wait for window to open because Robot is 
		// created in window that has focus
		robot = new Robot();                // throws AWTException if OS won't allow control
		robot.setAutoDelay(40);             // wait 40 mlsec after each event
		robot.setAutoWaitForIdle(true);     // wait for empty event queue before generating events

		// Window opens with focus in File Name textbox
		// use <cntrl>-v to paste file name in File Name textbox
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.delay(4000);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		robot.keyPress(KeyEvent.VK_ENTER);	// enter pushes Open button
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.delay(3000);	                // wait for windows to close
         
		// Back in Selenium, push Upload button 
		// and ensure result screen has our uploaded filename listed
		//
		WebElement uploadFile = driver.findElement(By.id("file-submit"));
		uploadFile.click();                 // upload the selected file 
		
		WebElement result = driver.findElement(By.id("uploaded-files"));
		Assert.assertEquals(result.getText(), path.getFileName().toString(), 
							"Result didn't match file name");
		Thread.sleep(3000);
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
