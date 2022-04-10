package com.inventory.framework;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.util.concurrent.TimeUnit;

import static com.inventory.config.BaseWebDriver.driver;

public class WaitUtils {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(WaitUtils.class);
    public final static long defaultTimeOut = 30;

    public static FluentWait<WebDriver> newWait(final WebDriver Driver) {
        return new FluentWait<WebDriver>(Driver);
    }

    public static FluentWait<WebDriver> newTolerantWait(final WebDriver Driver) {
        return newWait(Driver).ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
    }

    public static void waitForElementToDisplay(final WebDriver Driver, final WebElement element) {
        newWait(Driver).withTimeout(5000, TimeUnit.MILLISECONDS)
                .pollingEvery(250, TimeUnit.MILLISECONDS)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(StaleElementReferenceException.class, NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(element));
    }

    public static void waitFor5sec(){
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static void waitWithGivenTime(long t){
        driver.manage().timeouts().implicitlyWait(t, TimeUnit.SECONDS);
    }

    public static void sleep(int timer){
        try {
            Thread.sleep(timer);
            logger.info("sleep method with :"+timer);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
