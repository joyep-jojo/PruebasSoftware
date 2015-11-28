package test;

import java.util.concurrent.TimeUnit;

import logger.MainLogger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import dataDriven.MySqlDataManager;
import pageObject.DashboardPage;
import pageObject.LoginPage;
import pageObject.MainPage;
import pageObject.RegisterPage;
import util.Constants;

public class RegisterTest extends MainLogger{
	private WebDriver driver;
	MainPage mainpage;
	LoginPage loginpage;
	RegisterPage registerpage;
	
	@BeforeMethod
	public void beforeTest(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Constants.URLMAIN);
		mainpage = new MainPage(driver);
		loginpage = new LoginPage(driver);
		registerpage = new RegisterPage(driver);
	}

	@Test(dataProvider="registerpage")
  	public void Test(String idTestCase,
  						   String username,
  						   String password,
  						   String confirmPassword,
  						   String firstName,
  						   String LastName,
  						   String email,
  						   String language,
  						   String country,
  						   String company,
  						   String website) {
		LOGGER.info("Executing TestCase " +idTestCase);
		mainpage.ClickOnRegister();
		registerpage.InsertNewUser(username,
								   password,
								   confirmPassword,
								   firstName,
								   LastName,
								   email,
								   language,
								   country,
								   company,
								   website);
		if (username.equalsIgnoreCase("novalido")) {
			registerpage.VerifyErrorMessage();
		}else if (username.equalsIgnoreCase("novalido")) {
			registerpage.VerifyErrorMessage();
		}else if (username.equalsIgnoreCase("novalido")) {
			registerpage.VerifyErrorMessage();
		}else if (username.equalsIgnoreCase("novalido")) {
			registerpage.VerifyErrorMessage();
		}
  	}
	
	@DataProvider(name="registerpage")
	public Object[][] sendDataMySqlCustomers() {
		try {
			LOGGER.info("The datadriver couldn't load correctly");
			MySqlDataManager mySqlDataManager = new MySqlDataManager();
			Object[][] arrData = mySqlDataManager.getMySqlTable("registerpage");
			return arrData;
		} catch (Exception e) {
			LOGGER.severe("An error in sendDataMySqlCustomers method happens");
			return null;
		}
	}
	
	@AfterMethod
  	public void afterTest(){
  		try {
  	  		LOGGER.info("Quiting of the driver");
  	  		driver.quit();
		} catch (Exception e) {
			LOGGER.severe("An error happens trying to quitting the driver");
		}
  	}
}
