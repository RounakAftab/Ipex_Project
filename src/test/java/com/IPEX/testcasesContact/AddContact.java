package com.IPEX.testcasesContact;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

public class AddContact extends TestBase{

    @Test (priority = 2)
	public void goContactPage() throws InterruptedException {

		
		driver.findElement(By.cssSelector(OR.getProperty("Contact_Menu"))).click();
		Thread.sleep(5000);
		
		driver.findElement(By.linkText(OR.getProperty("New_Contact"))).click();
		Thread.sleep(7000);
		
		//Create Contact
		
		WebElement CompanyName=driver.findElement(By.id(OR.getProperty("Company_Name")));
		CompanyName.sendKeys(LoginData.getProperty("CompanyNameData"));
		Thread.sleep(2000);
		
		WebElement CompanyAddress=driver.findElement(By.id(OR.getProperty("Company_Address")));
		CompanyAddress.sendKeys(LoginData.getProperty("CompanyAddressData"));
		Thread.sleep(2000);
		
		WebElement CompanyCity=driver.findElement(By.id(OR.getProperty("Company_City")));
		CompanyCity.sendKeys(LoginData.getProperty("CompanyCityData"));
		Thread.sleep(2000);
		
//		driver.findElement(By.id(OR.getProperty("Company_Country_Click"))).click();
//		Thread.sleep(2000);
		
//		WebElement countryName= driver.findElement(By.cssSelector(OR.getProperty("Company_Country_Name_Type")));
//		countryName.sendKeys(LoginData.getProperty("CompanyCountryNameData"));
//		Thread.sleep(3000);
//		
	List <WebElement> Countries =driver.findElements(By.cssSelector(OR.getProperty("Country_Select")));
		Thread.sleep(3000);
		

		for(WebElement Country : Countries) {
			
			if(Country.getText().equalsIgnoreCase(LoginData.getProperty("CountryNameData"))) {
				
				Thread.sleep(2000);
				Country.click();
				break;
			}
			
		}
		Thread.sleep(3000);
		
		WebElement categoryField= driver.findElement(By.xpath(OR.getProperty("Category_Select")));
		categoryField.click();
		Thread.sleep(2000);
	
//		categoryField.sendKeys(LoginData.getProperty("CategoryData"));
//		Thread.sleep(2000);
		
		List <WebElement> Catgs =driver.findElements(By.cssSelector(OR.getProperty("Category_Name")));
		Thread.sleep(2000);
		
	   for(WebElement Catgegory : Catgs) {
			
			if(Catgegory.getText().equalsIgnoreCase(LoginData.getProperty("CategoryFixedData"))) {
				
				Thread.sleep(3000);
				Catgegory.click();
				break;
			}
			
		}
		
	    Thread.sleep(3000);
		
		driver.findElement(By.linkText("Save")).click();
		Thread.sleep(7000);
		
	}


}
