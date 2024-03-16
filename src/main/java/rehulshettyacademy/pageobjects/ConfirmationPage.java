package rehulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;
import rehulshettyacademy.AbscractComponents.AbscractComponent;

public class ConfirmationPage extends AbscractComponent {
	
	WebDriver driver;
	
	public ConfirmationPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		// every child class has to give driver
		super(driver);
		// Initialisation // this class will be initialised before everything else
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	public String getConfirmationMessage()
	{
		//CheckoutPage cp = new CheckoutPage(driver);	
		return confirmationMessage.getText();
	}

	
	
}
