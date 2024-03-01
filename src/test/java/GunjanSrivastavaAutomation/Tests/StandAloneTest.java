package GunjanSrivastavaAutomation.Tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.stream.Stream;


public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
		driver.get("https://rahulshettyacademy.com/client");

		//give username and pwd and login
		driver.findElement(By.id("userEmail")).sendKeys("gunjan@bedbath.com");
		driver.findElement(By.id("userPassword")).sendKeys("Bedbath@100");
		driver.findElement(By.id("login")).click();

		//given explicit wait for products to appear on page 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'mb-3')]")));
		List<WebElement> products = driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));
		
		String productName = "IPHONE 13 PRO";
		
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).findElement(By.tagName("b")).getText().toUpperCase().contains(productName)) {
				driver.findElements(By.xpath("//div[@class='card']//button[text()=' Add To Cart']")).get(i).click();
				break;
			}
		}
		
		//Using JAVA streams click add to cart for "ADIDAS ORIGINAL" product among all available products on page  
//		WebElement prodEle = products.stream().filter(s->s.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null); 
//		prodEle.findElement(By.xpath("//button[text()=' Add To Cart']")).click();
		//note- findfirst() means just get first one no matter how many results are returned and when no results are returned then return null
		
		//wait for loader to disappear
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		//wait for 'Added to Cart' toast container to appear
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();

		List<WebElement> productsInCart = driver.findElements(By.cssSelector("div.cart h3"));
		
		//Verifying that correct product added in the cart
		
//		WebElement productCart = productsInCart.stream().filter(prod->prod.getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
//		//productsInCart.stream().filter(prod->prod.getText().contains(productName)).forEach(prod1->System.out.println(prod1.getText()));
//		Assert.assertTrue(productCart.getText().equalsIgnoreCase(productName));
//		//System.out.println(productCart.getText().equalsIgnoreCase(productName));
		
		// Using anyMatch method to verify condition that correct product is added to cart
		Boolean flag = productsInCart.stream().anyMatch(prod->prod.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(flag);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@placeholder='Select Country']")));
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("Ind");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section/button")));

		List<WebElement> list = driver.findElements(By.xpath("//section/button"));
		for (WebElement we : list) {
			if (we.getText().equalsIgnoreCase("India")) {
				we.click();
			    break;
			}    
		}
		
		driver.findElement(By.xpath("//a[text()='Place Order ']")).click();
		
		String text = driver.findElement(By.xpath("//h1[text()=' Thankyou for the order. ']")).getText().trim();
		Assert.assertTrue(text.contains("THANKYOU FOR THE ORDER"));
	
		driver.close();
	}
}
