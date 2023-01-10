package mavenproject;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class ErrorValidations extends BaseTest{
	

	@Test
	public void loginErrorValidations() throws Exception
	{
		landingpage.login("sushil@gmail.com", "Sing@123");
		Assert.assertEquals("Incorrect email@ or password.", landingpage.getErrorMessage());
		
	}
	
	@Test
	public void submitOrderValidation() throws Exception
	{
		
		String productName = "ZARA COAT 3";
		ProductCatalogue productcatalogue = landingpage.login("sushil2@gmail.com", "Sushil@123");
		
		List<WebElement>products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay("Zara");
		Assert.assertFalse(match);
		
	}

	

}
