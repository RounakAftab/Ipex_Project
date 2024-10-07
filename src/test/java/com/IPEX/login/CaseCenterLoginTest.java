package com.IPEX.login;

import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;
import com.IPEX.testcasesForMailedCaseCreateCollectiveInvoice.AdminReportSendForMailedCase;

import utils.ExtentReporterNG;

public class CaseCenterLoginTest extends TestBase {
	
	private static final Logger logger = Logger.getLogger(AdminReportSendForMailedCase.class.getName());
	
	@Test (priority = 1)
	public void AdminloginPerform() throws InterruptedException {
		
		driver.get(config.getProperty("Ctestsiteurl"));
        logger.info("Navigated to Case Center Stage URL.");
        Thread.sleep(2000);
        
		WebElement CuserID= driver.findElement(By.cssSelector(OR.getProperty("UserIdField")));
		Thread.sleep(1000);
		CuserID.sendKeys(LoginData.getProperty("CUsername"));
		ExtentReporterNG.addStep("User ID input");
		
		WebElement password= driver.findElement(By.cssSelector(OR.getProperty("Cpass")));
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
