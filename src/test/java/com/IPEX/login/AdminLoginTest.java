package com.IPEX.login;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

import utils.ExtentReporterNG;

public class AdminLoginTest extends TestBase {
	
	@Test (priority = 1)
	public void AdminloginPerform() throws InterruptedException {
		
		
		WebElement userID= driver.findElement(By.cssSelector(OR.getProperty("UserIdField")));
		Thread.sleep(1000);
		userID.sendKeys(LoginData.getProperty("Username"));
		ExtentReporterNG.addStep("User ID input");
		
		WebElement password= driver.findElement(By.cssSelector(OR.getProperty("UserPasswordField")));
		Thread.sleep(1000);
		password.sendKeys(LoginData.getProperty("password"));
		ExtentReporterNG.addStep("Password input");
		
		driver.findElement(By.cssSelector(OR.getProperty("LoginButton"))).click();
		Thread.sleep(6000);	
		ExtentReporterNG.addStep("Clicked on Login button");
		
		String StageFrontEndBuildVersion = "Front-end version: " + driver.findElement(By.cssSelector(".text-center.w-100-p")).getText();
        System.out.println(StageFrontEndBuildVersion);
        ExtentReporterNG.addStep("Log in Successfully");
		
	}
	


}
