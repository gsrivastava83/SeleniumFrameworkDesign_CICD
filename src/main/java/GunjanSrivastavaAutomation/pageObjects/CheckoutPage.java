package GunjanSrivastavaAutomation.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {

	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		// initializing current class driver
		this.driver = driver;
		// initializing PageFactory
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//input[@placeholder='Select Country']")
	WebElement selectCountryDropdown;
	
	@FindBy(xpath = "//section/button")
	List<WebElement> countryListSection;
	
	@FindBy(xpath = "//a[text()='Place Order ']")
	WebElement PlaceOrderButton;
	
	By selectCountryDropdownBy = By.xpath("//input[@placeholder='Select Country']");
	By countryListSectionBy = By.xpath("//section/button");
	
	
    public void selectCountry(String country) {
    	waitingToAppear(selectCountryDropdownBy);
    	selectCountryDropdown.sendKeys(country);
    	waitingToAppear(countryListSectionBy);
    	
		for (WebElement contry : countryListSection) {
			if (contry.getText().equalsIgnoreCase("India")) {
				contry.click();
			    break;
			}    
		}
    }
    
    public ConfirmationPage placeOrder() {
    	PlaceOrderButton.click();
    	ConfirmationPage confirmationPage = new ConfirmationPage(driver);
    	return confirmationPage;
    }
    
    
}
