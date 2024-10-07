package com.IPEX.testcasesForMailedCaseCreateCollectiveInvoice;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.IPEX.base.TestBase;

import utils.ExtentReporterNG;

public class AdminReportSendForMailedCase extends TestBase {

    public JavascriptExecutor jse2;
    private static final Logger logger = Logger.getLogger(AdminReportSendForMailedCase.class.getName());

    @Test(priority = 4)
    public void adminSendInvoice() throws Exception {

        try {
            logger.info("Starting adminSendInvoice test case.");

            // Logout
            jse2 = (JavascriptExecutor) driver;
            driver.findElement(By.id(OR.getProperty("Menu_Dropdown"))).click();
            Thread.sleep(1000);
            ExtentReporterNG.addStep("Logout dropdown open");
            driver.findElement(By.xpath(OR.getProperty("Logout_Button"))).click();
            logger.info("Successfully logged out.");
            Thread.sleep(3000);
            ExtentReporterNG.addStep("Inspector Log out successfully");

            // Login
            WebElement userID = driver.findElement(By.cssSelector(OR.getProperty("UserIdField")));
            userID.clear();
            Thread.sleep(1000);
            userID.sendKeys(LoginData.getProperty("Username"));
            ExtentReporterNG.addStep("Mail input Done");
            WebElement password = driver.findElement(By.cssSelector(OR.getProperty("UserPasswordField")));
            password.clear();
            Thread.sleep(1000);
            password.sendKeys(LoginData.getProperty("password"));
            ExtentReporterNG.addStep("Password input Done");
            driver.findElement(By.cssSelector(OR.getProperty("LoginButton"))).click();
            logger.info("Successfully logged in to Admin account.");
            Thread.sleep(3000);
            ExtentReporterNG.addStep("Admin Login Successfully");

            checkInvoiceSystem = "Single";
            Thread.sleep(4000);

            // Go to Cases module
            driver.findElement(By.xpath(OR.getProperty("goToCaseModule"))).click();
            Thread.sleep(5000);

            // Click on last created case
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            driver.findElement(By.cssSelector(OR.getProperty("clearData"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            WebElement caseID= driver.findElement(By.cssSelector(OR.getProperty("clickOnCaseIDField")));
            caseID.sendKeys(mailboxCaseID);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("applySearchButton"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("caseClick"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Last created Case open successfully");

            // Click on case quality check edit button
            List<WebElement> caseNumberQualityCheckEdit = driver.findElements(By.xpath(OR.getProperty("clickQualityCheckEditButton")));
            caseNumberQualityCheckEdit.get(1).click();

            // Click on Complete radio button
            Thread.sleep(1000);
            driver.findElement(By.id(OR.getProperty("completeRadioButton"))).click();
            ExtentReporterNG.addStep("Complete radio button select");

            // Add Comments
            WebElement comments = driver.findElement(By.xpath(OR.getProperty("addQualityComments")));
            Thread.sleep(3000);
            comments.click();
            Thread.sleep(1000);
            comments.sendKeys("QC Complete");
            logger.info("QC Confirmation done successfully.");

            // Click Save button
            driver.findElement(By.xpath(OR.getProperty("qualitySaveButton"))).click();
            Thread.sleep(15000);
            ExtentReporterNG.addStep("QC confirmation Done Successfully");

            String getInvoiceType = driver.findElement(By.xpath(OR.getProperty("readyFBilling"))).getText();
                if ("READY FOR BILLING".equalsIgnoreCase(getInvoiceType)) {
                    logger.info("Case is on ready for billing state.");
                    Assert.assertEquals("READY FOR BILLING", getInvoiceType);
                } else {
                    Assert.assertFalse(checkInvoiceSystem.equalsIgnoreCase(getInvoiceType));
                }
            

            Thread.sleep(2000);
            logger.info("Collective Invoice is on ready for billing state for Mailed Case");
            ExtentReporterNG.addStep("Collective Invoice is on ready for billing state for Mailed Case");
            
         // Goto Admin Controls
            driver.findElement(By.xpath(OR.getProperty("AdminControls"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Nevigate to Admin Controls");
            

            // Goto Collective Billing
            driver.findElement(By.xpath(OR.getProperty("CollectiveBill"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Nevigate to Collective Bill");

            // Select Insurance Provider
            driver.findElement(By.cssSelector(OR.getProperty("SelectInsurance"))).click();
            Thread.sleep(1000);
            
            List<WebElement> insuranceSelect = driver.findElements(By.cssSelector(OR.getProperty("insuSel")));
            for (WebElement inS : insuranceSelect) {
                if (inS.getText().equalsIgnoreCase("IPEX Automate")) {
                    inS.click();
                    ExtentReporterNG.addStep("IPEX Automate Insurance provider selected");
                    break;
                }
            }
            

            // Select Company
            driver.findElement(By.xpath(OR.getProperty("SelectDepartmentForMailCase"))).click();
            ExtentReporterNG.addStep("Department Selected Successfully");

            // Select Date Time
            List<WebElement> caseNumberQualityCheckEdit2 = driver.findElements(By.id(OR.getProperty("SelectDate")));
            caseNumberQualityCheckEdit2.get(0).click();

            // Select Date
            ZoneId germanTimeZone = ZoneId.of("Europe/Berlin");
            LocalDateTime currentDateTime = LocalDateTime.now(germanTimeZone);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd");
            String formattedDateTime =currentDateTime.format(formatter);
            formattedDateTime = " "+ Integer.parseInt(formattedDateTime.trim()) +" ";

            driver.findElement(By.xpath("//div[text()='" + formattedDateTime + "']")).click();
            
            Thread.sleep(2000);
            System.out.println(formattedDateTime);

            List<WebElement> caseNumberQualityCheckEdit1 = driver.findElements(By.id(OR.getProperty("SelectDate")));
            caseNumberQualityCheckEdit1.get(1).click();
            driver.findElement(By.xpath("//div[text()='" + formattedDateTime + "']")).click();
            ExtentReporterNG.addStep("Date range selected");

            // Apply Filter
            driver.findElement(By.id(OR.getProperty("Filter"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Filter Applied");

            // Send Bill
            driver.findElement(By.xpath(OR.getProperty("SendBill"))).click();
            Thread.sleep(8000);
            ExtentReporterNG.addStep("Send Bill for Collective Invoice");

            // Close popup
            driver.findElement(By.xpath(OR.getProperty("PopupClose"))).click();
            Thread.sleep(5000);
            ExtentReporterNG.addStep("Pop up closed successfully");

            driver.findElement(By.xpath("//mat-icon[text()=\"receipt_long\"]")).click();
//            Thread.sleep(2000);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(5000);
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred during the adminSendInvoice test case.", e);
            throw e;
        } finally {
            logger.info("Completed adminSendInvoice test case.");
        }
    }
}
