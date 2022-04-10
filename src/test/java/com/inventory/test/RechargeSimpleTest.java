package com.inventory.test;

import com.inventory.framework.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.util.concurrent.TimeUnit;

public class RechargeSimpleTest {

	public static void main(String[] args) {

		String curPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver",
				System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
		System.out.println("Current dir :" + curPath);

		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);

		WebDriver driver = new ChromeDriver();
		//WebDriverWait w = new WebDriverWait(driver, 60);
		// Implicit wait
		driver.manage().window().maximize();
		driver.get("https://aspireapp.odoo.com");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//click on email input
		driver.findElement(By.xpath("//input[@id='login']")).click();
		driver.findElement(By.xpath("//input[@id='login']")).sendKeys("user@aspireapp.com");
		driver.findElement(By.xpath("//input[@id='password']")).click();
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("@sp1r3app");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		//wait for page load
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Inventory
		driver.findElement(By.xpath("//div[text()='Inventory']/parent::a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//click on Prodcuts
		driver.findElement(By.xpath("//button/span[text()='Products']")).click();
		driver.findElement(By.xpath("//div/a[@data-menu-xmlid='stock.menu_product_variant_config_stock']")).click();

		//Product page
		//Create Product
		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
		driver.findElement(By.xpath("//h1//input[@name='name']")).click();
		String prodName = Utils.randomNumericByLen(5, "Apple");
		System.out.println("Creating Product name :"+prodName);
		driver.findElement(By.xpath("//h1//input[@name='name']")).sendKeys(prodName);
		//click on on Save button
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

		//Update Quantity:
		driver.findElement(By.xpath("//span[contains(text(),'Update Quantity')]")).click();
		//click on create
		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();

		//update the quantity
		driver.findElement(By.xpath("//td/input[@name='inventory_quantity']")).clear();
		driver.findElement(By.xpath("//td/input[@name='inventory_quantity']")).sendKeys("10");
		//click on on Save button
		driver.findElement(By.xpath("//button[contains(text(),'Save')]")).click();

		//click on Home Menu
		driver.findElement(By.xpath("//nav/a[@title='Home menu']")).click();
		//Select Manufacturing
		driver.findElement(By.xpath("//div[text()='Manufacturing']/parent::a")).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		//Create Manufacturing Order
		driver.findElement(By.xpath("//button[contains(text(),'Create')]")).click();
		driver.findElement(By.xpath("//div[@name='product_id']//div/input")).sendKeys(prodName);
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//li/a[text()='"+prodName+"']")).click();
		//click on Scheduled Date Lable
		//driver.findElement(By.xpath("//label[contains(text(),'Quantity')]")).click();
		//click on confirm
		driver.findElement(By.xpath("//div[@class='o_statusbar_buttons']/button[@name='action_confirm']/span")).click();

		//Update quantity
		driver.findElement(By.xpath("//input[@name='qty_producing']")).clear();
		driver.findElement(By.xpath("//input[@name='qty_producing']")).sendKeys("10");

		//Get Manufacturing order
		String mOrder = driver.findElement(By.xpath("//span[@class='text-muted']")).getText();
		System.out.println("Manufacturing order :"+mOrder);
		//click on Mark as Done
		driver.findElement(By.xpath("//button[@class='btn btn-primary']//span[contains(text(),'Mark as Done')]")).click();
		driver.findElement(By.xpath("//footer[@class='modal-footer']/button/span[text()='Ok']")).click();

		//Verify the Done
		String doneStatus = driver.findElement(By.xpath("//div[@class='o_statusbar_status o_field_widget o_readonly_modifier']/button[@title='Current state']")).getText();
		System.out.println("Done status :"+doneStatus);

		//click on Manufacturing Orders
		driver.findElement(By.xpath("//a[contains(text(),'Manufacturing Orders')]")).click();

		//Verify the created Manufacturing order
		//Delete searcj manufacturing order filter
		driver.findElement(By.xpath("//div[@class='o_facet_values']/../i")).click();
		driver.findElement(By.xpath("//div[@class='o_searchview_input_container']/input[@role='searchbox']")).click();
		driver.findElement(By.xpath("//div[@class='o_searchview_input_container']/input[@role='searchbox']")).sendKeys(mOrder);
		driver.findElement(By.xpath("//div[@class='o_searchview_input_container']/input[@role='searchbox']")).sendKeys(Keys.RETURN);

		if(driver.findElement(By.xpath("//td[contains(text(),'"+prodName+"')]")).isDisplayed()){
			System.out.println("Product is displayed");
			String actQty = driver.findElement(By.xpath("//td[@name='product_qty']")).getText();
			if(actQty.equals("10")){
				System.out.println("Entered quantity is present");
			}
		}


	}
}
