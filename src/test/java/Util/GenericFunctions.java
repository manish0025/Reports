package Util;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;



public class GenericFunctions {
	
	public static void captureScreenshot(WebDriver driver,String screenshotName)
	{
	 
	try 
	{
		System.out.println("yaha");
		   File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
           //The below method will save the screen shot in d drive with name "screenshot.png"
              FileUtils.copyFile(scrFile, new File("./Screenshots/"+screenshotName+".png")); 
	System.out.println("Screenshot taken");
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
	}
	
	}

}
