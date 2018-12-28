package com.qa.reports;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.beust.jcommander.Parameter;

import Util.GenericFunctions;


@Test(groups= {"M1"})
public class TestClass extends Base{
	
	GenericFunctions gf;
	@BeforeMethod
	public void before() {
		driver  =startDriver();
		gf = new GenericFunctions();
	}
	
	
	@Parameters("var1")
	@Test
	public void TC_01(@Optional("abc") String var1) {
		System.out.println(var1);
		driver.get("https://www.google.com/");
		Assert.assertFalse(true);
	}

	
	
//	public void TC_02() {
//		
//		driver.get("https://www.google.com/");
//		Assert.assertFalse(true);
//		
//	}

	

	
	@AfterMethod
	public void close() {
		System.out.println("qqqqq");
		driver.quit();
	}

}
