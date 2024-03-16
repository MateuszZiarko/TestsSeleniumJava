package rehulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import rehulshettyacademy.AbscractComponents.AbscractComponent;

public class CartPage extends AbscractComponent {

	WebDriver driver;

	public CartPage(WebDriver driver) {
		// every child class has to give driver
		super(driver);
		// Initialisation // this class will be initialised before everything else
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".totalRow button")
	WebElement checkoutEle;

	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;

	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return match;

	}
	
	public CheckoutPage goToCheckout() {
		checkoutEle.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
		

	}

	
}
