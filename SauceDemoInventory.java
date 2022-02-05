package com.training;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class SauceDemoInventory {

	public static void main(String[] args) throws Exception {
		 
System.setProperty("webdriver.chrome.driver","C:\\Users\\aruna\\eclipse-workspace\\seleniumtraining\\Drivers\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
				
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@id='login-button']")).click();
			
		
		List<WebElement> prices = new ArrayList<>(driver.findElements(By.className("inventory_item_price")));	
		List<WebElement> cart = new ArrayList<>(driver.findElements(By.xpath("//button[text()='Add to cart']")));
		TreeMap<Double,WebElement> hashPrice= new TreeMap<Double,WebElement>();
	
		
		for (int i1=0;i1<prices.size();i1++) {
			hashPrice.put(Double.parseDouble(prices.get(i1).getText().replace("$", "").trim()),cart.get(i1));
			
		}
		Entry<Double, WebElement> cartitem = hashPrice.lastEntry();
		System.out.println(cartitem.getKey());
		WebElement lastitem = cartitem.getValue();
		lastitem.click();
		
		Thread.sleep(5000);
		driver.close();
			
		}
	}