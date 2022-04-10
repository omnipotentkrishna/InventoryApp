package com.inventory.framework;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
	
	Properties pro;
	
	public ReadConfig()
	{
		File src = new File("./testdata.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	public String getApplicationURL()
	{
		String url=pro.getProperty("baseURL");
		return url;
	}
	
	public String getValue(String keyName) {
		String retVal = null;
		retVal = pro.getProperty(keyName);
		return retVal;
	}
	
	public String getRequiredBroswer()
	{
		String browser=pro.getProperty("browser");
		return browser;
	}
	

}
