package com.inventory.framework;

import com.inventory.config.BaseWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;

public class Utils extends BaseWebDriver{
	
	/** Return Month and Day by adding no of days to current date */
	public static String getNextWeekDate(int noOfDays) {
        Calendar cal2 = Calendar.getInstance();
        DateFormat dateFormat1 = new SimpleDateFormat("MMMM dd");
        String nextDate = null;
        
        cal2.add(Calendar.DATE, noOfDays);
        nextDate = dateFormat1.format(cal2.getTime());
		
		return nextDate;
	}
	
	public static void scrollTillElement(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", ele);
	}

	public static String randomNumericByLen(int num, String name){
		return  name + randomNumeric(num);
	}

}
