package GunjanSrivastavaAutomation.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		//initializing current class driver
		this.driver = driver;
		//initializing PageFactory
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement login;
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public void goTo(String url) {
		driver.get(url);
	}
	
	public ProductCatalog login(String username, String password) {
		userEmail.sendKeys(username);
		userPassword.sendKeys(password);
		login.click();
		ProductCatalog productCatalog = new ProductCatalog(driver);
		return productCatalog;
	}
	
	public String getLoginErrorMessage() {
		waitingforElementToAppear(errorMessage);
		return errorMessage.getText();
	}

}
