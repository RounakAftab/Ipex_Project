package com.IPEX.testcasesForUploadCaseCollectiveInvoice;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

import utils.ExtentReporterNG;

public class CaseCenterCollectiveInvoiceCase extends TestBase {

	private static final Logger logger = Logger.getLogger(CaseCenterCollectiveInvoiceCase.class.getName());
	public JavascriptExecutor jse;

	@Test(priority = 2)
	public void createManualCase() {
		try {
			// Initialize JavascriptExecutor
			jse = (JavascriptExecutor) driver;


			//Create new case
			logger.info("Navigating to Case Center Case create page.");
			driver.findElement(By.cssSelector(OR.getProperty("CreateCaseButton"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("Successfully nevigate to the case create page");
			System.out.println("Successfully nevigate to the case create page");

			//Case Info Page
			logger.info("Input Data in the ClaimNumberInsurance field");
			
			 Random random = new Random();
	            int textLength = 15;
	            StringBuilder randomText = new StringBuilder(textLength);
	            for (int i = 0; i < textLength; i++) {
	                int randomIndex = random.nextInt(characters.length());
	                char randomChar = characters.charAt(randomIndex);
	                randomText.append(randomChar);
	            }
	            
	         String ClaimInsuNumber = "Case-Center-Collective-" + randomText;
	            
			WebElement cnI= driver.findElement(By.cssSelector(OR.getProperty("claimNumberInsu")));
			cnI.sendKeys(ClaimInsuNumber);
			Thread.sleep(2000);
			ExtentReporterNG.addStep("Data input done on ClaimNumberInsurance field");
			logger.info("Input Data in the ClaimNumberInsurance field");
			System.out.println("Successfully Data input done on ClaimNumberInsurance field");
			
			// get caseID
            UploadcaseID = driver.findElement(By.cssSelector(OR.getProperty("ClaimInsuNumber"))).getAttribute("value");

			//Date of Damage
			logger.info("Input Data in the DateOfDamage field");

			// Click the date picker input to open the calendar
			WebElement datePicker = driver.findElement(By.cssSelector(OR.getProperty("DateOfDamage"))); // Replace with your date picker element's locator
			datePicker.click();

			WebElement cde= driver.findElement(By.xpath(OR.getProperty("currentDate")));
			cde.click();


			ExtentReporterNG.addStep("Data input done on DateOfDamage field");
			System.out.println("Successfully Data input done on DateOfDamage field");

			// Case Type select
			logger.info("Selecting Case Type.");
			driver.findElement(By.cssSelector(OR.getProperty("CCaseType"))).click();
			List<WebElement> CaseType = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
			for (WebElement Ctype : CaseType) {
				if (Ctype.getText().equalsIgnoreCase("Glasbruch")) {
					Ctype.click();
					ExtentReporterNG.addStep("Case Type Select");
					break;
				}
				System.out.println("Successfully Case Type Selected");
			}

			//Case Info Page
			logger.info("Input Data in the Claim Description Field");
			driver.findElement(By.xpath(OR.getProperty("CServicetypeClick"))).click();
			Thread.sleep(2000);
			driver.findElement(By.xpath(OR.getProperty("CCClaimDes"))).sendKeys(LoginData.getProperty("climData"));
			Thread.sleep(1000);
			ExtentReporterNG.addStep("Input Data in the Claim Description Field");
			System.out.println("Input Data in the Claim Description Field");

			driver.findElement(By.xpath(OR.getProperty("CPropertyDes"))).sendKeys(LoginData.getProperty("propertDes"));
			Thread.sleep(1000);
			ExtentReporterNG.addStep("Input Data in the Claim Description Field");
			System.out.println("Input Data in the Claim Description Field");

			//Policy_Holder_Details
			driver.findElement(By.xpath(OR.getProperty("PHD"))).click();
			Thread.sleep(1000);
			ExtentReporterNG.addStep("Nevigate to the Policy Holder Details Page");
			System.out.println("Successfully Nevigate to the Policy Holder Details Page");

			driver.findElement(By.xpath(OR.getProperty("PHN"))).sendKeys(LoginData.getProperty("propertDes"));
			Thread.sleep(1000);
			ExtentReporterNG.addStep("Data input in the Policy Holder name field");
			System.out.println("Successfully Data input in the Policy Holder name field");

			//Street
			driver.findElement(By.cssSelector(OR.getProperty("PStreet"))).sendKeys(LoginData.getProperty("pStreet"));
			ExtentReporterNG.addStep("Data input in the street field");
			System.out.println("Data input in the street field");

			//Street Number
			driver.findElement(By.cssSelector(OR.getProperty("PStreetN"))).sendKeys(LoginData.getProperty("pSTNumber"));
			ExtentReporterNG.addStep("Data input in the street number field");
			System.out.println("Data input in the street number field");

			//Zip Number
			driver.findElement(By.cssSelector(OR.getProperty("PZip"))).sendKeys(LoginData.getProperty("pZip"));
			ExtentReporterNG.addStep("Data input in the ZIP field");
			System.out.println("Data input in the ZIP field");

			//City
			driver.findElement(By.cssSelector(OR.getProperty("PCity"))).sendKeys(LoginData.getProperty("pCity"));
			ExtentReporterNG.addStep("Data input in the CITY field");
			System.out.println("Data input in the CITY field");

			//Add Upload Files
			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
			Thread.sleep(1000);

			File documentPath = new File("Files/ipx_test_document_0b4a5daa-1538-4b3b-84bd-3291a0c8026f.pdf");
			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath.getAbsolutePath());
			Thread.sleep(2000);

			WebElement element = driver.findElement(By.xpath(OR.getProperty("pDocumentType")));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("Document Upload done with Offer Type");
			System.out.println("Successfully Document Upload done with Offer Type");

			driver.findElement(By.xpath(OR.getProperty("pSubmitCase"))).click();
			Thread.sleep(7000);
			logger.info("Case Center case created successfully.");
			ExtentReporterNG.addStep("Case Center case created successfully.");
			System.out.println("Case Center case created successfully.");

		} catch (InterruptedException e) {
			logger.log(Level.SEVERE, "Interrupted exception occurred.", e);
			Thread.currentThread().interrupt();
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Exception occurred while creating manual case.", e);
		}
	}
	
	
	    @Test(priority = 3)
        public void AdminloginPerform() throws InterruptedException {
		
		driver.get(config.getProperty("testsiteurl"));
        logger.info("Navigated to Case Center Stage URL.");
        Thread.sleep(2000);
        ExtentReporterNG.addStep("Nevigate to the Audit Center successfully.");
        
		WebElement CuserID= driver.findElement(By.cssSelector(OR.getProperty("UserIdField")));
		Thread.sleep(1000);
		CuserID.sendKeys(LoginData.getProperty("Username"));
		Thread.sleep(1000);
		ExtentReporterNG.addStep("User ID input");
		
		WebElement password= driver.findElement(By.cssSelector(OR.getProperty("Cpass")));
		Thread.sleep(1000);
		password.sendKeys(LoginData.getProperty("password"));
		Thread.sleep(1000);
		ExtentReporterNG.addStep("Password input");
		
		driver.findElement(By.cssSelector(OR.getProperty("LoginButton"))).click();
		Thread.sleep(6000);	
		ExtentReporterNG.addStep("Clicked on Login button");
		
		String StageFrontEndBuildVersion = "Front-end version: " + driver.findElement(By.cssSelector(".text-center.w-100-p")).getText();
        System.out.println(StageFrontEndBuildVersion);
        ExtentReporterNG.addStep("Log in Successfully");
		
	}
}
