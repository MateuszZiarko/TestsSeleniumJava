package rehulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rehulshettyacademy.AbscractComponents.AbscractComponent;

public class ProductCataloguePage extends AbscractComponent {

	WebDriver driver;

	public ProductCataloguePage(WebDriver driver) {
		// every child class has to give driver
		super(driver);
		// Initialisation // this class will be initialised before everything else
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// List<WebElement> products =
	// driver.findElements(By.cssSelector("div.col-lg-4"));
	// PageFactory
	@FindBy(css = "div.col-lg-4")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	By productsBy = By.cssSelector(".mb-3"); 
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");

	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) {
		
		WebElement product1 = getProductList().stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return product1;
	}
	
	public void addProductToCart(String productName) throws InterruptedException {
		
		WebElement product1 = getProductByName(productName);
		product1.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
}
