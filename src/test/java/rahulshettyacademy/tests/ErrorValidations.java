package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rehulshettyacademy.pageobjects.CartPage;
import rehulshettyacademy.pageobjects.CheckoutPage;
import rehulshettyacademy.pageobjects.ConfirmationPage;
import rehulshettyacademy.pageobjects.ProductCataloguePage;

public class ErrorValidations extends BaseTest {

	@Test
	public void ErrorValidation() throws IOException, InterruptedException {

		// login to application
		ProductCataloguePage productCatalogue = landingPage.loginApplication("ali@gmail.com", "8fdsfdsfsdfs@");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMsg());
	}

	@Test
	public void ProductErrorValidation() throws IOException, InterruptedException {
		// product name
		String productName = "ZARA COAT 3";
		String country = "Austria";

		// login to application
		ProductCataloguePage productCatalogue = landingPage.loginApplication("mz@gmail.com", "Lol12345678@");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}
}
