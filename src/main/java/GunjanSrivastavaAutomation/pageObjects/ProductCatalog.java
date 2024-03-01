package GunjanSrivastavaAutomation.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	public ProductCatalog(WebDriver driver) {
		super(driver);
		// initializing current class driver
		this.driver = driver;
		// initializing PageFactory
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//div[contains(@class,'mb-3')]")
	List<WebElement> products;
	
	@FindBy(xpath = "//div[@class='card']//button[text()=' Add To Cart']")
	List<WebElement> addToCartButtons;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.xpath("//div[contains(@class,'mb-3')]");
	By toastContainer = By.id("toast-container");

	
	public List<WebElement> getProductsList() {
		waitingToAppear(productsBy);
		return products;
	}

	public void addproductToCart(String productName) throws InterruptedException {
		for (int i = 0; i < products.size(); i++) {
			if (products.get(i).findElement(By.tagName("b")).getText().toUpperCase().contains(productName)) {
				addToCartButtons.get(i).click();
				break;
			}
		}
		waitingForInvisibility(spinner);
		waitingToAppear(toastContainer);
	}

}
