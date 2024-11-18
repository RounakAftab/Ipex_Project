package com.IPEX.testcasesForMailedCaseCreateSingleInvoice;

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

            if (checkInvoiceSystem.equalsIgnoreCase("Single")) {

                String getInvoiceType = driver.findElement(By.xpath(OR.getProperty("qualityStatus"))).getText();

                if ("CLOSED".equalsIgnoreCase(getInvoiceType)) {
                    logger.info("Case is successfully CLOSED and invoice is sent.");
                    Assert.assertEquals("CLOSED", getInvoiceType);

                    WebElement addMoreScroll3 = driver.findElement(By.xpath(OR.getProperty("moreScrool")));
                    jse2.executeScript("arguments[0].scrollIntoView(true);", addMoreScroll3);
                    Thread.sleep(2000);

                } else {
                    Assert.assertFalse("CLOSED".equalsIgnoreCase(getInvoiceType));
                }

            } else {

                String getInvoiceType = driver.findElement(By.xpath(OR.getProperty("readyFBilling"))).getText();
                if ("READY FOR BILLING".equalsIgnoreCase(getInvoiceType)) {
                    logger.info("Case is on ready for billing state.");
                    Assert.assertEquals("READY FOR BILLING", getInvoiceType);
                } else {
                    Assert.assertFalse(checkInvoiceSystem.equalsIgnoreCase(getInvoiceType));
                }
            }

            Thread.sleep(2000);
            logger.info("Single Invoice is created successfully for Mailed Case.");
            ExtentReporterNG.addStep("Single Invoice is created successfully for Mailed Case.");
            
        } catch (Exception e) {
            logger.log(Level.SEVERE, "An error occurred during the adminSendInvoice test case.", e);
            throw e;
        } finally {
            logger.info("Completed adminSendInvoice test case.");
        }
    }
}
