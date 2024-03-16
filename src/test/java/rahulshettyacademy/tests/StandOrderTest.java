package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rehulshettyacademy.pageobjects.CartPage;
import rehulshettyacademy.pageobjects.CheckoutPage;
import rehulshettyacademy.pageobjects.ConfirmationPage;
import rehulshettyacademy.pageobjects.OrderPage;
import rehulshettyacademy.pageobjects.ProductCataloguePage;

public class StandOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "Purchase" }) // we are attaching data from data provider, two arrays of
																// different data
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException { // we have to
																										// catch the
																										// data here
		// product name

		String country = "Austria";

		// login to application
		ProductCataloguePage productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(country);
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmationMsg = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	// To verify if zara coat 3 order is places in orders page

	@Test(dependsOnMethods = { "submitOrder" })
	public void orderHistoryTest() {

		ProductCataloguePage productCatalogue = landingPage.loginApplication("mz@gmail.com", "Lol12345678@");
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		// hash map
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("email", "mz@gmail.com");
//		map.put("password", "Lol12345678@");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1.put("email", "mz1@gmail.com");
//		map1.put("password", "Lol12345678@");
//		map1.put("productName", "ADIDAS ORIGINAL");

		// we are creating hash map from json
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } }; // Object is generic data type, accept every datatype

	}
	
	
	
}
