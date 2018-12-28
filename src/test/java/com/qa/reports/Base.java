package com.qa.reports;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Base {
	
	public WebDriver driver;
	
	public WebDriver startDriver() {
		System.setProperty("webdriver.chrome.driver", "./Resource/chromedriver2");
		 driver = new ChromeDriver();
		 return driver ;
	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
