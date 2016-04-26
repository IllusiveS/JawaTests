package com.example.webguidemo.pages;

import java.util.concurrent.TimeUnit;

import org.jbehave.web.selenium.WebDriverPage;
import org.jbehave.web.selenium.WebDriverProvider;
import org.openqa.selenium.By;

public class Home extends WebDriverPage {

	public Home(WebDriverProvider driverProvider) {
		super(driverProvider);
	}


	
	public void open() {
		get("http://www.seleniumframework.com/Practiceform/");
		manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}
	
	public void FillForm(String name, String mail, String phone, String country, String company, String message){
		findElement(By.name("name")).sendKeys(name);
		findElement(By.name("email")).sendKeys(mail);
		findElement(By.name("telephone")).sendKeys(phone);
		findElement(By.name("country")).sendKeys(country);
		findElement(By.name("company")).sendKeys(company);
		findElement(By.name("message")).sendKeys(message);
		findElement(By.className("dt-btn dt-btn-m dt-btn-submit")).click();
	}
	
}
