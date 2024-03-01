package GunjanSrivastavaAutomation.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		// initializing current class driver
		this.driver = driver;
		// initializing PageFactory
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = "div.cart h3")
	List<WebElement> productsInCart;
	
	@FindBy(xpath = "//button[text()='Checkout']")
	WebElement checkoutButton;
	

	public Boolean verifyProductAddedInCart(String productName) {
		Boolean flag = productsInCart.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
		return flag;
	}
	
	public CheckoutPage clickOnCheckoutButton() throws InterruptedException {
		checkoutButton.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
	
}
