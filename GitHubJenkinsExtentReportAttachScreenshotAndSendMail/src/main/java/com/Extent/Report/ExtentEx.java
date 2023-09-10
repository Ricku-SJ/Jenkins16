package com.Extent.Report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ExtentEx {
	static WebDriver driver;
	ExtentReports extent=new ExtentReports();
	ExtentSparkReporter spark=new ExtentSparkReporter("MyExtentReporter.html");
	@BeforeClass
	public void open()
	{
		extent.attachReporter(spark);
		Reporter.log("Open",true);
		if(System.getProperty("browser").equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else if(System.getProperty("browser").equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
		}
	}
	@BeforeMethod
	public void login() throws IOException
	{
		Reporter.log("Login",true);
		driver.get(System.getProperty("url"));
	String Title = driver.getTitle();
		String base64Code=captureScreenshot();
		String path=captureScreenshot(Title+".png");
		
		extent
		.createTest("This is a Screenshot for Test1 Create Method"," Capture Screenshot for Test level")
		.info("This is a info msg")
		.pass(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,Title+" Homepage").build())
		.pass("This is a info msg",MediaEntityBuilder.createScreenCaptureFromPath(path, Title+" Homepage").build());
		extent
		.createTest("This is a screenshot for Test2 Modified Method","capture screenshot for log level")
		.info("This is a info msg")
		.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code,Title+" Homepage").build())
	.fail(MediaEntityBuilder.createScreenCaptureFromPath(path,Title+" Homepage").build());
	}
	@AfterMethod
	public void logout()
	{
		Reporter.log("Logout",true);
	}
	@AfterClass
	public void close() throws IOException
	{
		Reporter.log("Close",true);
		extent.flush();
		
	}
	public static String captureScreenshot()
	{
		TakesScreenshot t=(TakesScreenshot)driver;
		String base64Code = t.getScreenshotAs(OutputType.BASE64);
		return base64Code;
	}
	public static String captureScreenshot(String Filename) throws IOException
	{
		TakesScreenshot t=(TakesScreenshot)driver;
		File src = t.getScreenshotAs(OutputType.FILE);
		File f=new File("./Screenshot/"+Filename);
		FileUtils.copyFile(src, f);
		return f.getAbsolutePath();		
	}
}
