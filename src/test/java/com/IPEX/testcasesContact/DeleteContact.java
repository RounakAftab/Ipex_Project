package com.IPEX.testcasesContact;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

public class DeleteContact extends TestBase {
	
	@Test(priority= 4)
	public void contactDelete() throws InterruptedException {
		
		Thread.sleep(2000);
		driver.findElement(By.linkText(OR.getProperty("EditButton"))).click();
		Thread.sleep(4000);
		
		driver.findElement(By.linkText(OR.getProperty("DeleteButton"))).click();
		Thread.sleep(2000);
		
		driver.findElement(By.cssSelector("button[class='swal2-confirm btn btn-danger']")).click();
		Thread.sleep(9000);
		
		WebElement subjectField= driver.findElement(By.id(OR.getProperty("DeletedSearchField")));
		subjectField.sendKeys(LoginData.getProperty("UpdatedSubjectContactValue"));
		Thread.sleep(1000);
		subjectField.sendKeys(Keys.ENTER);
		Thread.sleep(6000);

}
}
