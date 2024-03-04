package GunjanSrivastavaAutomation.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import GunjanSrivastavaAutomation.TestComponents.BaseTest;
import GunjanSrivastavaAutomation.pageObjects.CartPage;
import GunjanSrivastavaAutomation.pageObjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=GunjanSrivastavaAutomation.TestComponents.Retry.class)
	public void validatingLoginErrors() throws IOException, InterruptedException {

		landingPage.login("gunjan@bedbath.com", "Bedbath100"); //give wrong username or password
		String errorMessage = landingPage.getLoginErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email and password."); 
	}

	@Test
	public void productErrorValidation() throws InterruptedException {

		ProductCatalog productCatalog = landingPage.login("gunjan@bedbath.com", "Bedbath@100");

		String productName = "IPHONE 13 PRO"; 
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addproductToCart(productName);

		CartPage cartPage = productCatalog.clickonCartIcon(); 
		Boolean flag = cartPage.verifyProductAddedInCart("IPHONE 13 PRO");  
		Assert.assertTrue(flag);
	}
	
}
