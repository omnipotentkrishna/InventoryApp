package com.inventory.pages;

import com.inventory.framework.WaitUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static com.inventory.config.BaseWebDriver.driver;
import static com.inventory.config.BaseWebDriver.softAssertion;

public class ManufacturingPage {
    private static final Logger logger = LogManager.getLogger(ManufacturingPage.class);

    public ManufacturingPage() {
        PageFactory.initElements(driver, this); }

    @FindBy(xpath = "//button[contains(text(),'Create')]")
    private WebElement createBtn;

    @FindBy(xpath = "//div[@name='product_id']//div/input")
    private WebElement productInput;

    @FindBy(xpath = "//div[@class='o_statusbar_buttons']/button[@name='action_confirm']/span")
    private WebElement confirmBtn;

    @FindBy(xpath = "//input[@name='qty_producing']")
    private WebElement manufacturingQty;

    @FindBy(xpath = "//span[@class='text-muted']")
    private WebElement manufacturingOrderNo;

    @FindBy(xpath = "//button[@class='btn btn-primary']//span[contains(text(),'Mark as Done')]")
    private WebElement markAsDoneBtn;

    @FindBy(xpath = "//footer[@class='modal-footer']/button/span[text()='Ok']")
    private WebElement confirmOkBtn;

    @FindBy(xpath = "//div[@class='o_statusbar_status o_field_widget o_readonly_modifier']/button[@title='Current state']")
    private WebElement doneCurrentStatus;

    @FindBy(xpath = "//a[contains(text(),'Manufacturing Orders')]")
    private WebElement manufacturingOrderLink;

    @FindBy(xpath = "//div[@class='o_facet_values']/../i")
    private WebElement manufacturingOrderFilterCloseBtn;

    @FindBy(xpath = "//div[@class='o_searchview_input_container']/input[@role='searchbox']")
    private WebElement searchInput;

    @FindBy(xpath = "//td[@name='product_qty']")
    private WebElement productQty;

    public WebElement getCreateBtn() {
        return createBtn;
    }

    public WebElement getProductInput() {
        return productInput;
    }

    public WebElement getConfirmBtn() {
        return confirmBtn;
    }

    public WebElement getManufacturingQty() {
        return manufacturingQty;
    }

    public WebElement getManufacturingOrderNo() {
        return manufacturingOrderNo;
    }

    public WebElement getMarkAsDoneBtn() {
        return markAsDoneBtn;
    }

    public WebElement getConfirmOkBtn() {
        return confirmOkBtn;
    }

    public WebElement getDoneCurrentStatus() {
        return doneCurrentStatus;
    }

    public WebElement getManufacturingOrderLink() {
        return manufacturingOrderLink;
    }

    public WebElement getManufacturingOrderFilterCloseBtn() {
        return manufacturingOrderFilterCloseBtn;
    }

    public WebElement getSearchInput() {
        return searchInput;
    }

    public WebElement getProductQty() {
        return productQty;
    }

    public boolean verifyManufacturingOrderDetails(String productName){
        boolean flag = false;
        if(driver.findElement(By.xpath("//td[contains(text(),'"+productName+"')]")).isDisplayed()){
            System.out.println("Product is displayed");
            if(getProductQty().getText().equals("10")){
                System.out.println("Entered quantity is present");
                flag = true;
            }
        }
        return flag;
    }


    public void selectProduct(String prodName){
        driver.findElement(By.xpath("//li/a[text()='"+prodName+"']")).click();
    }


    public void searchManufacturingOrder(String mOrderNum){
        getManufacturingOrderFilterCloseBtn().click();
        WaitUtils.waitWithGivenTime(60);
        getSearchInput().click();
        getSearchInput().sendKeys(mOrderNum);
        WaitUtils.waitWithGivenTime(10);
        getSearchInput().sendKeys(Keys.RETURN);
    }

    public void verifyOrderDetails(String prodName, String qty){
        WaitUtils.waitWithGivenTime(60);
        if(driver.findElement(By.xpath("//td[contains(text(),'"+prodName+"')]")).isDisplayed()){
            logger.info("verifyOrderDetails() : Product is displayed");
            softAssertion.assertTrue(getProductQty().getText().equals(qty), "Entered ordered quantity is present");
        }

    }

}
