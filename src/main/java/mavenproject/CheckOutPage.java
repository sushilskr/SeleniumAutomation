package mavenproject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement country;
	
	@FindBy(css="button[class*='ng-star-inserted']:last-child")
	WebElement selectCountry;
	
	@FindBy(css=".btnn.action__submit.ng-star-inserted")
	WebElement submit;
	
	By results = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		
		Actions a = new Actions(driver);
		a.sendKeys(country,countryName).build().perform();
		waitForElementToAppear(results);
		selectCountry.click();
	}
	
	public ConfirmationPage submitOrder() throws Exception {
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		Thread.sleep(2000);
		submit.click();
		return new ConfirmationPage(driver);
	}
	

}
