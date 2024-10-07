package com.IPEX.testcasesContact;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

public class UpdateContact extends TestBase {
	
	@Test(priority = 3)
	public void contactUpdate() throws InterruptedException {
		
		Thread.sleep(4000);
		
		driver.findElement(By.linkText("Edit")).click();
		Thread.sleep(4000);
		
		WebElement CompanyName=driver.findElement(By.id(OR.getProperty("Company_Name")));
		CompanyName.clear();
		Thread.sleep(1000);
		CompanyName.sendKeys(LoginData.getProperty("UpdatedCompanyNameData"));
		Thread.sleep(1000);
		
		WebElement CompanyAddress=driver.findElement(By.id(OR.getProperty("Company_Address")));
		CompanyAddress.clear();
		Thread.sleep(1000);
		CompanyAddress.sendKeys(LoginData.getProperty("UpdatedCompanyAddressData"));
		Thread.sleep(1000);
		
		WebElement CompanyCity=driver.findElement(By.id(OR.getProperty("Company_City")));
		CompanyCity.clear();
		Thread.sleep(1000);
		CompanyCity.sendKeys(LoginData.getProperty("UpdatedCompanyCityData"));
		Thread.sleep(1000);
		
		driver.findElement(By.linkText("Save")).click();
		Thread.sleep(9000);
		
	}

}
