package GunjanSrivastavaAutomation.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import GunjanSrivastavaAutomation.TestComponents.BaseTest;
import GunjanSrivastavaAutomation.pageObjects.CartPage;
import GunjanSrivastavaAutomation.pageObjects.CheckoutPage;
import GunjanSrivastavaAutomation.pageObjects.ConfirmationPage;
import GunjanSrivastavaAutomation.pageObjects.OrdersPage;
import GunjanSrivastavaAutomation.pageObjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{

	@Test(dataProvider = "getData", groups={"Purchase"})
	public void submitOrder(HashMap<String, String> map) throws IOException, InterruptedException {
		
		//give username and pwd and login into an application
		ProductCatalog productCatalog = landingPage.login(map.get("username"),map.get("password"));

		//adding product to cart
    	//List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addproductToCart(map.get("productName"));
		
		//click on Cart icon
		CartPage cartPage = productCatalog.clickonCartIcon(); //accessing parent class method thru child class as child class has access 
		                                  //to all parent class members
		
		//Verifying the product added in the cart
		Boolean flag = cartPage.verifyProductAddedInCart(map.get("productName"));
		Assert.assertTrue(flag);
		
		// navigating to checkout page
		CheckoutPage checkoutPage = cartPage.clickOnCheckoutButton();
		
		//selectCountry
		String country = "Ind";
		checkoutPage.selectCountry(country);
		
		//placing the order
		ConfirmationPage confirmationPage = checkoutPage.placeOrder();
		
		//verifying the confirmation text
		String confirmationText = confirmationPage.getOrderConfirmationText();
		Assert.assertTrue(confirmationText.contains("THANKYOU FOR THE ORDER"));
		//Thread.sleep(2000);
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void verifyOrderHistory() throws InterruptedException {
		String productName = "ZARA COAT 3"; 
		landingPage.login("gunjan@bedbath.com","Bedbath@100");
		OrdersPage ordersPage = landingPage.clickOnMyOrdersIcon(); //accessing parent class method thru child class as child class has access 
                                                                   //to all parent class members
		Boolean flag = ordersPage.verifyProductinOrderHistoryPage(productName);
		Assert.assertTrue(flag);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
//		Object[][] data = new Object[2][3];
//		data[0][0]= "gunjan@bedbath.com";
//		data[0][1]= "Bedbath@100";
//		data[0][2]= "I PHONE";
//		data[1][0]= "anshika@gmail.com";
//		data[1][1]= "Iamking@000";
//		data[1][2]= "I PHONE";
		
		//OR 2 dimensional object array can also be declared and initialized as below
	    
//		Object[][] data = {{"gunjan@bedbath.com","Bedbath@100","I PHONE"},{"anshika@gmail.com","Iamking@000","I PHONE"}};
//		return data;
		
		//Providing data through HashMap
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("username", "gunjan@bedbath.com");
//		map.put("password", "Bedbath@100");
//		map.put("productName", "I PHONE");
//
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("username", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "I PHONE");
//		
//		Object[][] data = {{map},{map1}}; //array of different data sets
//		return data;
		
		
// /Users/gunjansrivastava/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/GunjanSrivastavaAutomation/Data/PurchaseOrder.json
// System.getProperty("user.dir" + "/src/test/java/GunjanSrivastavaAutomation/Data/PurchaseOrder.json"		
		//Implementing data through Json file
		// sending Json file path while calling method getJsonDataToString()
		List<HashMap<String, String>> data = getJsonDataToString("/Users/gunjansrivastava/eclipse-workspace/SeleniumFrameworkDesign/src/test/java/GunjanSrivastavaAutomation/Data/PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
		
	}	
	
}
