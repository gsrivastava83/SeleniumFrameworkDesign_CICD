package GunjanSrivastavaAutomation.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import GunjanSrivastavaAutomation.pageObjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage;

	public WebDriver initializeDriver() throws IOException {

		// Properties class can read global properties and can decide at runtime on
		// which browser test will run.
		// Properties class will parse .properties file and extract all global parameter
		// values.
		Properties prop = new Properties(); // object creation for Properties class
		// FileInputStream object 'fis' will convert file at given location into
		// FileInputStream since load() expects file in InputStream
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "/src/main/java/GunjanSrivastavaAutomation/resoucres/GlobalData.properties");
		prop.load(fis); // load GlobalData.properties file which will be parsed and all global data can
						// be extracted.
						// load method is expecting file in InputStream
		//String browserName = prop.getProperty("browser"); // now extracting browser property
		
		//use below line of code when browser info is given through terminal and not through GlobalData.properties file. 
		//If browser info is passed through terminal then execute System.getProperty("Browser") statement after ? otherwise run 
		//prop.getProperty("browser") statement 
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");

		if (browserName.contains("chrome")) {
//			WebDriverManager.chromedriver().setup();
//			driver = new ChromeDriver();
			
			//when running test in headless mode
			ChromeOptions options = new ChromeOptions();//creating chrome otions class for running test in headless mode.  
			WebDriverManager.chromedriver().setup();
			//if browserName contains headless then execute in headless mode. If it will not have headless then 
			//options.addArguments("headless") will not set and will be empty and chrome will run in headed mode
			if(browserName.contains("headless")) { 
			options.addArguments("headless"); //adding an argument headless using this options object
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); //when test is run in headless mode run in fullscreen browser
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"/Users/gunjansrivastava/Documents/Automation/BrowserDrivers exes/geckodriver");
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver", "edge browser path");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		return driver;

	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {

		driver = initializeDriver();
		landingPage = new LandingPage(driver); // creating an object of LandingPage
		landingPage.goTo("https://rahulshettyacademy.com/client"); // navigating to url;
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void closeDriver() {
		driver.close();
	}

	public List<HashMap<String, String>> getJsonDataToString(String filePath) throws IOException {

		// reading Json to String and storing in String variable. readFileToString takes one of the arguments as path 
		// of Json file that has to be scanned and converted into String
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);

		// converting String to HashMap using Jackson databind
		ObjectMapper mapper = new ObjectMapper(); // Creating ObjectMapper object 'mapper'

		// using object 'mapper', its readValue() is called. The first argument in it is
		// String that we want to convert
		// and 2nd argument is how we want to convert that string i.e convert all
		// datasets into separate hashmaps and then send them together in List
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;
	}

    public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
    	
    //Casting driver object to take screenshot. In getScreenshotAs method we are telling the webdriver script
    //to get the output in file format which is stored in src object of type File
    			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    			// giving destination File location
    			File destn = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
    			// copying the file from src object to our machine
    			FileUtils.copyFile(src, destn);
    			return System.getProperty("user.dir") + "/reports/" + testCaseName + ".png";
    }
	
}
