package mavenproject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement email;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public ProductCatalogue login(String userID, String pass) {
		email.sendKeys(userID);
		password.sendKeys(pass);
		loginButton.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	

}
