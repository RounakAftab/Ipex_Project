package com.IPEX.testcasesFileConvert;

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

public class CaseCenterFileConvert extends TestBase {

	private static final Logger logger = Logger.getLogger(CaseCenterFileConvert.class.getName());
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
	            
	         String ClaimInsuNumber = "UPLOAD-File_Convert-" + randomText;
	            
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
			
			//PNG Claim Add
			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
			Thread.sleep(2000);

			//Add Upload Files
			File documentPath = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.png");
			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath.getAbsolutePath());
			Thread.sleep(2000);

			WebElement element = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType1")));
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("PNG Upload done with Offer Type");
			System.out.println("Successfully PNG Document Upload done with Offer Type");
			
			
			//JPG Claim Add
			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
			Thread.sleep(2000);
			
			File documentPath21 = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.jpg");
			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath21.getAbsolutePath());
			Thread.sleep(2000);

			WebElement element21 = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType2")));
			Actions actions21 = new Actions(driver);
			actions21.moveToElement(element21).click().perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("JPG Doc Upload done with Offer Type");
			System.out.println("Successfully JPG Document Upload done with Offer Type");
			
			//JPEG Claim Add
			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
			Thread.sleep(2000);
			
			File documentPath22 = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.jpeg");
			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath22.getAbsolutePath());
			Thread.sleep(2000);

			WebElement element22 = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType3")));
			Actions actions22 = new Actions(driver);
			actions22.moveToElement(element22).click().perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("JPEG Doc Upload done with Offer Type");
			System.out.println("Successfully JPEG Document Upload done with Offer Type");
			
			//RTF Claim Add
			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
			Thread.sleep(2000);
			
			File documentPath23 = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.rtf");
			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath23.getAbsolutePath());
			Thread.sleep(2000);

			WebElement element23 = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType4")));
			Actions actions23 = new Actions(driver);
			actions23.moveToElement(element23).click().perform();
			Thread.sleep(1000);

			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
			Thread.sleep(2000);
			ExtentReporterNG.addStep("RTF Doc Upload done with Offer Type");
			System.out.println("Successfully RTF Document Upload done with Offer Type");
			
			//TIF Claim Add
//			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
//			Thread.sleep(2000);
//			
//			File documentPath24 = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.tif");
//			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath24.getAbsolutePath());
//			Thread.sleep(2000);
//
//			WebElement element24 = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType5")));
//			Actions actions24 = new Actions(driver);
//			actions24.moveToElement(element24).click().perform();
//			Thread.sleep(1000);
//
//			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
//			Thread.sleep(2000);
//			ExtentReporterNG.addStep("TIF Doc Upload done with Offer Type");
//			System.out.println("Successfully TIF Document Upload done with Offer Type");
			
			//TIFF Claim Add
//			driver.findElement(By.xpath(OR.getProperty("PUloadModal"))).click();
//			Thread.sleep(2000);
//			
//			File documentPath25 = new File("Files/ipex_test_document_706fbefd-1539-48c1-a53b-18cea176a5af.tiff");
//			driver.findElement(By.xpath("//input[@accept=\".PNG,.png,.jpg,.jpeg,.pdf,.doc,.rtf\"]")).sendKeys(documentPath25.getAbsolutePath());
//			Thread.sleep(2000);
//
//			WebElement element25 = driver.findElement(By.cssSelector(OR.getProperty("pDocumentType6")));
//			Actions actions25 = new Actions(driver);
//			actions25.moveToElement(element25).click().perform();
//			Thread.sleep(1000);
//
//			driver.findElement(By.xpath(OR.getProperty("pOfferClick"))).click();
//			Thread.sleep(2000);
//			ExtentReporterNG.addStep("TIFF Doc Upload done with Offer Type");
//			System.out.println("Successfully TIFF Document Upload done with Offer Type");
			
			driver.findElement(By.xpath(OR.getProperty("pSubmitCase"))).click();
			Thread.sleep(8000);
			logger.info("file added successfully.");
			ExtentReporterNG.addStep("file added successfully.");
			System.out.println("file added successfully.");

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
