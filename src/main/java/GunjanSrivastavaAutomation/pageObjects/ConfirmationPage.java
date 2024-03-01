package GunjanSrivastavaAutomation.pageObjects;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent {

	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		// initializing current class driver
		this.driver = driver;
		// initializing PageFactory
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//h1[text()=' Thankyou for the order. ']")
	WebElement orderConfirmationText;
	
	public String getOrderConfirmationText() {
		String text = orderConfirmationText.getText().trim();
		return text;
	}
    
}
