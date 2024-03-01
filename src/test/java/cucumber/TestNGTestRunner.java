package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions (tags= "@PlaceOrder", features = "src/test/java/cucumber", glue = "GunjanSrivastavaAutomation.stepDefinition", 
monochrome = true, plugin = {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{ //TestNGRunner has to extend  AbstractTestNGCucumberTests but 
	//for JUnit it is in-built and therefore no need to extend for JUnit
	
}
