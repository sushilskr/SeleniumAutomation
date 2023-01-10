package mavenproject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tr th:nth-child(1)")
	private List<WebElement> orderIDs;
	
	public Boolean getOrderIdConfirmation(String OrderID) {
		Boolean orderVerify = orderIDs.stream().anyMatch(order->order.getText().equalsIgnoreCase(OrderID));
		return orderVerify; 
	}
	
	
}
