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
import util.Constants;

public class LoginTest extends MainLogger {
	private WebDriver driver;
	MainPage mainpage;
	LoginPage loginpage;
	DashboardPage dashboardpage;
	
	@BeforeMethod
	public void beforeTest(){
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Constants.URLMAIN);
		mainpage = new MainPage(driver);
		loginpage = new LoginPage(driver);
		dashboardpage = new DashboardPage(driver);
	}
	
	@Test(dataProvider="loginpage")
  	public void Successful(String idTest, String username, String password ) {
		LOGGER.info("Executing Test Case " + idTest);
		mainpage.clickOnSignOn();
		loginpage.SignIn(username, password);
		if (Integer.parseInt(idTest)==1) {
			AssertJUnit.assertTrue(dashboardpage.VerifySuccessfulLogin());
		}else {
			AssertJUnit.assertTrue(loginpage.VerifyErrorMessage());
		}
  	}
  	
  	@DataProvider(name="loginpage")
	public Object[][] sendDataMySqlCustomers() {
		try {
			LOGGER.info("The datadriver couldn't load correctly");
			MySqlDataManager mySqlDataManager = new MySqlDataManager();
			Object[][] arrData = mySqlDataManager.getMySqlTable("loginpage");
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
