package GunjanSrivastavaAutomation.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GunjanSrivastavaAutomation.AbstractComponents.AbstractComponent;

public class OrdersPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//table//descendant::tr/td[2]")
	List<WebElement> names;
	
	
	public List<WebElement> getProductNamesListOrderHistoryPage() {
		return names;
	}
	
	
	public Boolean verifyProductinOrderHistoryPage(String productName) {
		return names.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
	}
	
}
