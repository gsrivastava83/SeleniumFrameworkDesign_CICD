package GunjanSrivastavaAutomation.stepDefinition;

import java.io.IOException;

import org.testng.Assert;

import GunjanSrivastavaAutomation.TestComponents.BaseTest;
import GunjanSrivastavaAutomation.pageObjects.CartPage;
import GunjanSrivastavaAutomation.pageObjects.CheckoutPage;
import GunjanSrivastavaAutomation.pageObjects.ConfirmationPage;
import GunjanSrivastavaAutomation.pageObjects.LandingPage;
import GunjanSrivastavaAutomation.pageObjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class stepDefinition extends BaseTest{
	
	LandingPage landingPage;
	ProductCatalog productCatalog;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	ConfirmationPage confirmationPage;
	
	@Given("User is on ecommerce website")
	public void user_is_on_ecommerce_website() throws IOException {
		landingPage = launchApplication();
	}
	
	
	@When ("^user login to the ecommerce website with username (.+) and password (.+)$") //put regular expression (.+) 
	//for parameters where value is coming at runtime and also ass carot and $ symbols
	public void user_login_to_website(String username, String password) {
		productCatalog = landingPage.login(username, password);
	}
	
	@Then ("^user adds a product (.+) in cart$")
	public void user_adds_product_in_cart(String productName) throws InterruptedException {
	//	List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addproductToCart(productName);
	}
	
	@When ("user places the order on website")
	public void user_places_order() throws InterruptedException {
		checkoutPage = cartPage.clickOnCheckoutButton();
		
		String country = "Ind";
		checkoutPage.selectCountry(country);
		
		confirmationPage = checkoutPage.placeOrder();
	}
	
	@Then ("user verifies the confirmation message {string} on the website") //when data is present in step itself 
	//then use {string} to notify that string value will be passed at this place
	public void user_verifies_confirmationMessage(String string) {
		String confirmationText = confirmationPage.getOrderConfirmationText();
		Assert.assertTrue(confirmationText.contains(string));
		driver.close();
	}
	
	@Then ("I verify the error message {string} displayed")
	public void verify_error_message(String string) {
		String errorMessage = landingPage.getLoginErrorMessage();
		Assert.assertEquals(errorMessage, string);
	}

	@Then ("^user verifies product (.+) in cart$")
	public void user_verifies_product_in_cart(String productName) {
		cartPage = productCatalog.clickonCartIcon();
		Boolean flag = cartPage.verifyProductAddedInCart(productName);
		Assert.assertTrue(flag);
	}
}
