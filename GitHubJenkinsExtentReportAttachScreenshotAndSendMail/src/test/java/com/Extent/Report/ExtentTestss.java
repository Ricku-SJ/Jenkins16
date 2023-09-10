package com.Extent.Report;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.testng.Reporter;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class ExtentTestss extends ExtentEx{
@Test
public void create()
{
	Reporter.log("Create",true);
	ExtentTest test=extent.createTest("This a login test");
	test.log(Status.PASS, "This executed properly")
	.pass("This accept all inputs")
	.info("This is a info msg");
}
@Test
public void modify() throws IOException
{
	Reporter.log("Modify",true);
	ExtentTest test=extent.createTest("This a login test");
	test.log(Status.FAIL, "This didn'texecute properly")
	.fail("This didn't accept all inputs")
	.info("This is a info msg");
	//Desktop.getDesktop().browse(new File("MyExtentReport.html").toURI());
}
}
