package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;
import rehulshettyacademy.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage landingPage; // we are making this method public

	public WebDriver initializeDriver() throws IOException {

		// properties class
		Properties prop = new Properties();
		// not having hard coding local path
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")
				+ "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser") // java ternary
																									// operator
				: prop.getProperty("browser");
		// if there is no browser given from terminal it will use browser from global
		// data

		if (browserName.contains("chrome")) {
			// headless mode
			ChromeOptions options = new ChromeOptions();
			WebDriverManager.chromedriver().setup();
			// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\mateu\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); // avoid flakyness in headless mode

		} else if (browserName.contains("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			WebDriverManager.firefoxdriver().setup();
			// BaseTest.setProperty("webdriver.gecko.driver",
			// "C:\\Users\\mateu\\Downloads\\geckodriver-v0.34.0-win64\\geckodriver.exe");
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new FirefoxDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); // avoid flakyness in headless mode
		} else if (browserName.contains("edge")) {
			EdgeOptions options = new EdgeOptions();
			WebDriverManager.edgedriver().setup();
			// System.setProperty("webdriver.edge.driver",
			// "C:\\Users\\mateu\\Downloads\\edgedriver_win64\\msedgedriver.exe");
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = new EdgeDriver(options);
			driver.manage().window().setSize(new Dimension(1440, 900)); // avoid flakyness in headless mode
		}
		long time = 5000;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.manage().window().maximize();
		return driver;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplocation() throws IOException {

		driver = initializeDriver();
		landingPage = new LandingPage(driver);
		// go to site
		landingPage.goTo("https://rahulshettyacademy.com/client");
		return landingPage;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// String to HashMap- Jackson Datbind

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

		// {map, map}
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException { // taking screenshots

		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
	}

}
