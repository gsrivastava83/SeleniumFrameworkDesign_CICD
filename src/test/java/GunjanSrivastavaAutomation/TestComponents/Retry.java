package GunjanSrivastavaAutomation.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{ //Retry class implements IRetryAnalyzer
	
	 int count= 0;
     int maxTry=1;

	@Override
	public boolean retry(ITestResult result) { //this method should return true for re-run of test
        		
        if(count<maxTry) {
        	count=count+1;
        	return true;
        }
		return false;
	}
}
