package com.inventory.pages;

import com.inventory.config.BaseWebDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BaseWebDriver {
	private static final Logger logger = LogManager.getLogger(HomePage.class);

	@FindBy(xpath = "//div[text()='Inventory']/parent::a")
	private WebElement inventoryMenu;

	@FindBy(xpath = "//div[text()='Manufacturing']/parent::a")
	private WebElement manufacturingMenu;

	@FindBy(xpath = "//div[text()='Discuss']/parent::a")
	private WebElement discussMenu;

	public HomePage() {
		PageFactory.initElements(driver, this); }


	public InventoryPage navigateToInventoryPage(){
		logger.info("navigateToInventoryPage() : navigating to Inventory page");
		getInventoryMenu().click();
		return new InventoryPage();
	}

	public WebElement getInventoryMenu() {
		return inventoryMenu;
	}

	public WebElement getManufacturingMenu() {
		return manufacturingMenu;
	}

	public WebElement getDiscussMenu() {
		return discussMenu;
	}
}
