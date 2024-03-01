package GunjanSrivastavaAutomation.TestComponents;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import GunjanSrivastavaAutomation.resoucres.ExtentReportNG;

public class Listeners extends BaseTest implements ITestListener {

	ExtentTest test;
	ExtentReports extent = ExtentReportNG.getReportObject(); // getReportObject() in ExtentReportNG class is static
	// method and therefore it can be called here with class name only and no object is required to call it
	ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>(); // Thread safe
	

	// extent.createTest() is added at the start of the test. Therefore, in
	// Listeners it is added in onTestStart()
	@Override
	public void onTestStart(ITestResult result) { // result object contains all info about test execution results and
													// about test method
		test = extent.createTest(result.getMethod().getMethodName()); // therefore, using result obj we can get method
																		// info
		// createTest will create an obj of type ExtentTest which will be unique to the
		// test and is responsible for listening and report
		// all the results back to the extent report. This obj will be used for further
		// steps.
		extentTest.set(test); //it will give unique thread id to the test 
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test PASSED"); // logging an info if reaches this block that test is Passed

	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable()); // it will print the error message with result.getThrowable()
		
		//getting the driver by getting the class where the actual method is present and then from that class getting an info about 
		// driver. Fielda are associated with Class and not method.
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		// On test failure take screenshot and attach it to the Extent report
		String path = null;
		try {
			path = getScreenshot(result.getMethod().getMethodName(), driver); //call getScreenshot() present in BaseTest class 
			                                                                  //passing testCaseName and driver as parameters
		} catch (IOException e) {
			e.printStackTrace();
		}
		//adding screenshot captured on machine to ExtentReports
		extentTest.get().addScreenCaptureFromPath(path, result.getMethod().getMethodName());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush(); // once test execution done it will make sure reports are generated
	}

}
