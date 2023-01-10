package mavenproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest{
	
	String orderID;
	//String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws Exception
	{
		
		
		ProductCatalogue productcatalogue = landingpage.login(input.get("email"),input.get("password"));
		
		List<WebElement>products=productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));
		CartPage cartpage = productcatalogue.goToCartPage();
		Boolean match = cartpage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goToCheckOut();
		checkoutpage.selectCountry("India");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String orderConfiramation = confirmationpage.getConfirmationMessage();
		Assert.assertEquals(orderConfiramation, "THANKYOU FOR THE ORDER.");
		String orderString = confirmationpage.getOrderID();
		orderID = orderString.substring(2, 26);
		System.out.println(orderID);
		
	}
	
	//Test : Validate if the order given is available in the order history with same order ID
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest() {
		System.out.println(orderID);
		ProductCatalogue productcatalogue = landingpage.login("sushil@gmail.com", "Singhwar@123");
		OrderPage orderPage = productcatalogue.goToOrderPage();
		System.out.println(orderPage.getOrderIdConfirmation(orderID));
		Assert.assertFalse(orderPage.getOrderIdConfirmation(orderID));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
}




//HashMap<String, String> map=new HashMap<String, String>();
//map.put("email", "sushil@gmail.com");
//map.put("password", "Singhwar@123");
//map.put("productName", "ZARA COAT 3");
//
//HashMap<String, String> map2=new HashMap<String, String>();
//map2.put("email", "sushil2@gmail.com");
//map2.put("password", "Sushil@123");
//map2.put("productName", "ADIDAS ORIGINAL");