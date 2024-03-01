package GunjanSrivastavaAutomation.resoucres;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportNG {
	
	public static ExtentReports getReportObject() { //this method is declared static as it can be accessed/called with class name only 
		//and no object is required to call them
		
		String filePath = System.getProperty("user.dir") + "/reports/index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(filePath);
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Gunjan Srivastava");
		return extent;
	
	}

}
