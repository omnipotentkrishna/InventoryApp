package com.inventory.config;

//import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.asserts.SoftAssert;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class BaseWebDriver {
	
	public static WebDriver driver;
	public static Properties prop;
	public static String userName;
	public static String password;
	public static SoftAssert softAssertion;

	private static final Logger logger = LogManager.getLogger(BaseWebDriver.class);


	public BaseWebDriver(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ "/testdata.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void initialization(){
		String browserName = prop.getProperty("browser");
		//PropertyConfigurator.configure("Log4j.properties");
		logger.info("initialization() method:");
		softAssertion = new SoftAssert();

		logger.info("Configured browser is :"+browserName);
		if(browserName.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");	
			driver = new ChromeDriver(); 
		}
		else if(browserName.equals("FF")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");	
			driver = new FirefoxDriver(); 
		}else if(browserName.equals("IE")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
			driver = new FirefoxDriver();
		}else{
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		userName = prop.getProperty("userName");
		password = prop.getProperty("password");

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("baseURL"));
		
	}

	public void tearDown() {
		driver.close();
	}

}
