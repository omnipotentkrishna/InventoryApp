package com.inventory.pages;

import com.inventory.framework.Utils;
import com.inventory.framework.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.inventory.config.BaseWebDriver.driver;
import static com.inventory.config.BaseWebDriver.softAssertion;

public class InventoryPage {
    private static org.apache.logging.log4j.Logger logger = LogManager.getLogger(InventoryPage.class);

    public InventoryPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button/span[text()='Products']")
    private WebElement productsMenu;

    @FindBy(xpath = "//div/a[@data-menu-xmlid='stock.menu_product_variant_config_stock']")
    private WebElement productsSubMenu;

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    private WebElement createBtn;

    @FindBy(xpath = "//h1//input[@name='name']")
    private WebElement productNameInput;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    private WebElement saveBtn;

    @FindBy(xpath = "//span[contains(text(),'Update Quantity')]")
    private WebElement updateQtyMenu;

    @FindBy(xpath = "//td/input[@name='inventory_quantity']")
    private WebElement countedQtyInput;

    @FindBy(xpath = "//nav/a[@title='Home menu']")
    private WebElement homeMenu;

    @FindBy(xpath = "//div[text()='Manufacturing']/parent::a")
    private WebElement manufacturingMenu;

    @FindBy(xpath = "//li/span[text()='Products']")
    private WebElement productsMenuTxt;

    public WebElement getProductsMenuTxt() {
        return productsMenuTxt;
    }

    public WebElement getProductsMenu() {
        return productsMenu;
    }

    public WebElement getProductsSubMenu() {
        return productsSubMenu;
    }

    public WebElement getCreateBtn() {
        return createBtn;
    }

    public WebElement getProductNameInput() {
        return productNameInput;
    }

    public WebElement getSaveBtn() {
        return saveBtn;
    }

    public WebElement getUpdateQtyMenu() {
        return updateQtyMenu;
    }

    public WebElement getCountedQtyInput() {
        return countedQtyInput;
    }

    public WebElement getHomeMenu() {
        return homeMenu;
    }

    public WebElement getManufacturingMenu() {
        return manufacturingMenu;
    }

    public ManufacturingPage navigateManufacturingPage() {
        getManufacturingMenu().click();
        return new ManufacturingPage();
    }

    public void navigateToProducts() {
        getProductsMenu().click();
        getProductsSubMenu().click();
        softAssertion.assertTrue(getProductsMenuTxt().getText().contains("Products"), "Products Header present");
    }

    public String createProduct(String qty) {
        logger.info("createProduct() : creating Product");
        getCreateBtn().click();
        WaitUtils.waitWithGivenTime(60);
        //Enter name
        String prodName = Utils.randomNumericByLen(5, "Apple");
        getProductNameInput().click();
        getProductNameInput().sendKeys(prodName);
        getSaveBtn().click();
        WaitUtils.sleep(3000);
        if (getUpdateQtyMenu().isDisplayed()) {
            getUpdateQtyMenu().click();
            WaitUtils.sleep(1000);
            //click on create button
            getCreateBtn().click();
            WaitUtils.sleep(3000);
            //update qty
            getCountedQtyInput().clear();
            getCountedQtyInput().sendKeys(qty);
            //click on Save button
            getSaveBtn().click();
        }
        return prodName;
    }
}
