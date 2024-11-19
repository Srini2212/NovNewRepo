package packagenew;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class LetCodes {

	public static void main(String args[]) throws IOException {
		
		ExtentReports extentReports = new ExtentReports();
		ExtentSparkReporter eSparkReporter = new ExtentSparkReporter("LetsCodeExtentReport.html");
		extentReports.attachReporter(eSparkReporter);
		ExtentTest testCase = extentReports.createTest("Open Demo QA and Fill the text");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");

		WebDriver driver = WebDriverManager.chromedriver().capabilities(options).create();
		driver.manage().window().maximize();
		
		testCase.log(Status.INFO, "WebPage loaded");

		driver.get("https://demoqa.com/");

		WebElement formsButton= driver.findElement(By.xpath("//h5[text()='Elements']"));
		formsButton.click();

		WebElement TextBox= driver.findElement(By.xpath("//span[text()='Text Box']"));
		TextBox.click();

		WebElement UserName= driver.findElement(By.id("userName"));
		UserName.sendKeys("Testing Main");

		WebElement emailText= driver.findElement(By.id("userEmail"));
		emailText.sendKeys("testMain@gmail.com");

		WebElement submitButton= driver.findElement(By.id("submit"));
		submitButton.click();
		
		testCase.log(Status.INFO, "Submit button clicked");

		WebElement nameVerify = driver.findElement(By.xpath("//div[@class='border col-md-12 col-sm-12']/p[@id='name']"));
		String namePassed= nameVerify.getText();
		System.out.println(namePassed);

		WebElement emailVerify = driver.findElement(By.xpath("//div[@class='border col-md-12 col-sm-12']/p[@id='email']"));
		String emailPassed= emailVerify.getText();
		System.out.println(emailPassed);
		
		
		TakesScreenshot screenShot = (TakesScreenshot) driver;

		File screenshotFile = screenShot.getScreenshotAs(OutputType.FILE);

		File file = new File("methodMain.png");

		FileHandler.copy(screenshotFile, file);

		testCase.addScreenCaptureFromPath("methodMain.png");
		
		testCase.log(Status.INFO, "ScreenShot captured");
		
		
		driver.close();
		extentReports.flush();

		/*
		// Keeps the browser open indefinitely
		try {
			Thread.sleep(Long.MAX_VALUE); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 */
		


	}

}
