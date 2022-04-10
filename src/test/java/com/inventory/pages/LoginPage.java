package com.inventory.pages;

import com.inventory.config.BaseWebDriver;
import com.inventory.framework.ReadConfig;
import com.inventory.framework.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class LoginPage extends BaseWebDriver {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);

    @FindBy(xpath = "//input[@id='login']")
    private WebElement usernNameInputBox;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwotdInputBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginBtn;

    @FindBy(xpath = "//div[@id='login']//div[@class='modal-header']/button")
    private WebElement closeBtn;

    @FindBy(xpath = "//span[@class='oe_topbar_name']")
    private WebElement UserMenu;

    @FindBy(xpath = "//a[@data-menu='logout']")
    private WebElement logoutBtn;


    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public WebElement getUsernNameInputBox() {
        return usernNameInputBox;
    }

    public WebElement getPasswotdInputBox() {
        return passwotdInputBox;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }

    public WebElement getCloseBtn() {
        return closeBtn;
    }

    public WebElement getUserMenu() {
        return UserMenu;
    }

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    /**
     * login with username and password
     */
    public HomePage userLogin() {
        ReadConfig rc = new ReadConfig();
        logger.info("userLogin() method:");
        try {
            //Enter user name
            getUsernNameInputBox().sendKeys(rc.getValue("userName"));
            getPasswotdInputBox().sendKeys(rc.getValue("password"));
            Thread.sleep(3000);
            getLoginBtn().click();
            WaitUtils.waitWithGivenTime(70);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("userLogin() :" + e.getStackTrace());
        }
        return new HomePage();
    }


}
