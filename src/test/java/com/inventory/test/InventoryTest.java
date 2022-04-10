package com.inventory.test;

import com.inventory.config.BaseWebDriver;
import com.inventory.framework.WaitUtils;
import com.inventory.pages.HomePage;
import com.inventory.pages.InventoryPage;
import com.inventory.pages.LoginPage;
import com.inventory.pages.ManufacturingPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class InventoryTest extends BaseWebDriver {

    private static final Logger logger = LogManager.getLogger(InventoryTest.class);

    LoginPage loginPage;
    HomePage homePage;
    InventoryPage inventory;
    ManufacturingPage manufacturing;

    @BeforeMethod
    public void setup() {
        initialization();
        logger.info("setup() :");
    }

    @Test(priority = 1, description="Validate created Manufacturing Order")
    public void testRecharge() {
        loginPage = new LoginPage();
        homePage = new HomePage();
        inventory = new InventoryPage();

        try {
            System.out.println("User name :" + userName + " password :" + password);
            logger.info("login to application");
            homePage = loginPage.userLogin();
            //wait for payment page
            WaitUtils.waitWithGivenTime(30);

            inventory = homePage.navigateToInventoryPage();
            WaitUtils.waitWithGivenTime(60);
            inventory.navigateToProducts();

            //Create product with qty=10
            String prodName = inventory.createProduct("10");
            logger.info("Created product name :"+prodName);

            //click on Home menu
            inventory.getHomeMenu().click();
            manufacturing = inventory.navigateManufacturingPage();
            WaitUtils.waitWithGivenTime(30);
            Thread.sleep(5000);
            //Create manufacturing order
            manufacturing.getCreateBtn().click();
            WaitUtils.sleep(3000);
            //Select Product name
            manufacturing.getProductInput().sendKeys(prodName);
            manufacturing.selectProduct(prodName);
            //click on confirm
            manufacturing.getConfirmBtn().click();
            WaitUtils.sleep(3000);
            //Enter qty
            manufacturing.getManufacturingQty().clear();
            manufacturing.getManufacturingQty().sendKeys("10");
            WaitUtils.sleep(3000);

            String manufacturingOrderNum = manufacturing.getManufacturingOrderNo().getText();
            System.out.println("MOder num :"+manufacturingOrderNum);
            logger.info("Manufacturing Order number is :"+manufacturingOrderNum);

            manufacturing.getMarkAsDoneBtn().click();
            WaitUtils.waitWithGivenTime(5);
            manufacturing.getConfirmOkBtn().click();
            WaitUtils.sleep(3000);
            String doneStatus = manufacturing.getDoneCurrentStatus().getText();
            softAssertion.assertTrue(doneStatus.equalsIgnoreCase("Done"), "Manufacturing order status is Done");

            //Click on Manufacturing orders
            manufacturing.getManufacturingOrderLink().click();

            //Clear search filters and search
            manufacturing.searchManufacturingOrder(manufacturingOrderNum);
            WaitUtils.sleep(1000);
            //Verify details
            manufacturing.verifyOrderDetails(prodName, "10");

        }catch (InterruptedException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }



    @AfterMethod
    public void closeBrowser() {
        logger.info("closeBrowser() :");
        tearDown();
    }

}
