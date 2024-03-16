package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import rehulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//product name
		String productName = "ZARA COAT 3";
		String country = "Austria";
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\mateu\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		long time = 4000;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage = new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("mz@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Lol12345678@");
		driver.findElement(By.id("login")).click();
		List<WebElement> products = driver.findElements(By.cssSelector("div.col-lg-4"));

		
		// can the product list and find "ZARA COAT 3"
		WebElement product1 = products.stream().filter(
				product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		// Click add to cart button
		product1.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		// wait for confirmation of added product to cart
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		// waiting for wait button to disappear 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		// clicking the cart 
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		// we are confirming if there is correct product added to cart
		Boolean match = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		// if there is any match it should return true, hence assertion
		Assert.assertTrue(match);
		// clicking checkout btn
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		Actions a = new Actions(driver); // using that we can deal with dropdowns
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), country).build().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//button[contains(@class,'ta-item')])"))).click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirmationMsg = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmationMsg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.quit();
	}

}
