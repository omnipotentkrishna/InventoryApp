package com.inventory.framework;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class CofigWebDriver {
	private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(CofigWebDriver.class);
	private static CofigWebDriver instanceOfCofigWebDriver=null;	
	
    private WebDriver driver;
    ReadConfig rc = new ReadConfig();
    
    // Constructor
    private CofigWebDriver(){
    	String br = rc.getRequiredBroswer();
		logger.info("CofigWebDriver() : setting browser :"+br);

		if(br.equals("chrome"))
		{
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(br.equals("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		}
		else {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
			driver=new ChromeDriver();
		}

    }

    // TO create instance of class
    public static CofigWebDriver getInstanceOfCofigWebDriver(){
        if(instanceOfCofigWebDriver==null){
        	instanceOfCofigWebDriver = new CofigWebDriver();
        }
        return instanceOfCofigWebDriver;
    }
    
    // To get driver
    public WebDriver getDriver()
    {
    	return driver;
    }

}