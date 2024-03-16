package rehulshettyacademy.pageobjects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rehulshettyacademy.AbscractComponents.AbscractComponent;





public class LandingPage extends AbscractComponent{

	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		
		// you can send variables from child class to a parent class
		super(driver);
		
		// Initialisation // this class will be initialised before everything else
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//WebElement userEmail = driver.findElement(By.id("userEmail")).sendKeys("mz@gmail.com");
	//WebElement userPassword = driver.findElement(By.id("userPassword")).sendKeys("Lol12345678@");
	//driver.findElement(By.id("login")).click();
	
	// PageFactory
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitButton;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMsg;
	
	public ProductCataloguePage loginApplication(String email, String password) {
		
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitButton.click();
		ProductCataloguePage productCatalogue = new ProductCataloguePage(driver);
		return productCatalogue;
	}
	
	public void goTo(String site)
	{
		
		driver.get(site);
	}
	
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		return errorMsg.getText();
	}
	
}
