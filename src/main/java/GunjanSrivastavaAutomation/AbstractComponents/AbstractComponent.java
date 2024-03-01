package GunjanSrivastavaAutomation.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import GunjanSrivastavaAutomation.pageObjects.CartPage;
import GunjanSrivastavaAutomation.pageObjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	WebDriverWait wait;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//button[contains(@routerlink,'cart')]")
	WebElement cartIcon;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement myOrders;
	
	public void waitingToAppear(By findBy) {
	wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitingforElementToAppear(WebElement errorMessage) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(errorMessage));
	}
	
	public void waitingForInvisibility(WebElement spinner) throws InterruptedException {
		Thread.sleep(2000);
		//Application issue on the site for spinner, so commented below code and added Thread.sleep
//		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(spinner));
	}
	
	public CartPage clickonCartIcon() {
		cartIcon.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage clickOnMyOrdersIcon() {
		myOrders.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
	
}
