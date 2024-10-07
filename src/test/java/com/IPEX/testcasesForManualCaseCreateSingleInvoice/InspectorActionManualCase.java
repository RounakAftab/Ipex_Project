package com.IPEX.testcasesForManualCaseCreateSingleInvoice;


import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

import utils.ExtentReporterNG;

public class InspectorActionManualCase extends TestBase {
    private static final Logger logger = Logger.getLogger(InspectorActionManualCase.class.getName());
    public JavascriptExecutor jse1;

    @Test(priority = 3)
    public void InspectorAction() {
        try {
            jse1 = (JavascriptExecutor) driver;

            // Logout
            logger.info("Logging out.");
            driver.findElement(By.id(OR.getProperty("Menu_Dropdown"))).click();
            Thread.sleep(1000);
            ExtentReporterNG.addStep("Admin logout dropdown open");
            driver.findElement(By.xpath(OR.getProperty("Logout_Button"))).click();
            logger.info("Successfully logged out.");
            ExtentReporterNG.addStep("Successfully logged out");
            Thread.sleep(3000);

            // Login to inspector account
            logger.info("Logging in as inspector.");
            driver.findElement(By.id(OR.getProperty("Email_Clear"))).clear();
            driver.findElement(By.id(OR.getProperty("Email_Clear"))).sendKeys(LoginData.getProperty("inspector_email"));
            logger.info("Inspector email input Done");
            driver.findElement(By.id(OR.getProperty("Password_Clear"))).clear();
            driver.findElement(By.id(OR.getProperty("Password_Clear"))).sendKeys(LoginData.getProperty("inspector_password"));
            driver.findElement(By.cssSelector(OR.getProperty("LoginButton"))).click();
            logger.info("Inspector login successful.");
            Thread.sleep(3000);

            // Go to Cases Module
            logger.info("Navigating to Cases Module.");
            driver.findElement(By.xpath(OR.getProperty("InspectorCaseClick"))).click();
            ExtentReporterNG.addStep("Navigating to Cases Module.");
            Thread.sleep(3000);

            // Click on last created case
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            driver.findElement(By.cssSelector(OR.getProperty("clearData"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            
            
            WebElement caseID3= driver.findElement(By.cssSelector(OR.getProperty("clickOnCaseIDField")));
            caseID3.sendKeys(caseID);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("applySearchButton"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("caseClick"))).click();
            ExtentReporterNG.addStep("Clicked on last created case"+ caseID);

            // Click on edit button
            logger.info("Clicking on edit button.");
            driver.findElement(By.id(OR.getProperty("CaseEditButton"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Clicking on edit button.");

            // Testing Category
            logger.info("Selecting Testing Category.");
            WebElement testingCategoryScroll = driver.findElement(By.xpath(OR.getProperty("TCscrool")));
            jse1.executeScript("arguments[0].scrollIntoView(true);", testingCategoryScroll);
            Thread.sleep(2000);
            driver.findElement(By.id(OR.getProperty("TcClick"))).click();
            Thread.sleep(2000);
            logger.info("Testing Category selected.");
            ExtentReporterNG.addStep("Testing Category selected.");

            // Add More
            logger.info("Scrolling to and clicking 'ADD MORE'.");
            WebElement addMoreScroll2 = driver.findElement(By.xpath(OR.getProperty("AddMoreButton")));
            jse1.executeScript("arguments[0].scrollIntoView(true);", addMoreScroll2);
            Thread.sleep(2000);
            
            logger.info("'ADD MORE' clicked.");
            for (int i = 0; i < totalItemList; i++) {
                String itemSerialNum = 1 + "." + ((char) (i + 65));

                logger.info("Scrolling to item: " + itemSerialNum);
                WebElement itemScroll = driver.findElement(By.xpath("//p[text()=" + "\"" + itemSerialNum + "\"" + "]"));
                jse1.executeScript("arguments[0].scrollIntoView(true);", itemScroll);

                List<WebElement> getInvoice = driver.findElements(By.cssSelector(OR.getProperty("GetInv")));
                Actions actions1 = new Actions(driver);
                actions1.moveToElement(getInvoice.get(i)).click().build().perform();
                logger.info("Invoice item clicked: " + itemSerialNum);

                driver.findElement(By.xpath(OR.getProperty("MaterialClick"))).click();
                Thread.sleep(1000);
                logger.info("Material clicked for item: " + itemSerialNum);
                ExtentReporterNG.addStep("Item added Successfully");
                Thread.sleep(1000);
            }

            // Inspector Report
            logger.info("Scrolling to Inspector Report.");
            WebElement inspectorReportPrice = driver.findElement(By.xpath(OR.getProperty("InspectReportPrice")));
            jse1.executeScript("arguments[0].scrollIntoView(true);", inspectorReportPrice);

            logger.info("Selecting price for Inspector Report.");
            List<WebElement> priceSelect = driver.findElements(By.id(OR.getProperty("PriceSelect")));
            priceSelect.get(0).click();
            driver.findElement(By.id(OR.getProperty("MatRadio"))).click();
            logger.info("Price selected for Inspector Report.");
            ExtentReporterNG.addStep("Price selected for inspector");
            Thread.sleep(2000);

            // Plausibility
            logger.info("Scrolling to Plausibility.");
            WebElement inspectorReportPlausibility = driver.findElement(By.xpath(OR.getProperty("Plausibili")));
            jse1.executeScript("arguments[0].scrollIntoView(true);", inspectorReportPlausibility);
            driver.findElement(By.id(OR.getProperty("Plausibility")));
            logger.info("Plausibility selected.");
            ExtentReporterNG.addStep("Plausibility selected.");
            Thread.sleep(2000);

            // Compensation
            logger.info("Scrolling to Compensation.");
            WebElement inspectorReportCompensation= driver.findElement(By.xpath(OR.getProperty("Mat_Button1")));
            Thread.sleep(2000);
            jse1.executeScript("arguments[0].scrollIntoView(true);", inspectorReportCompensation);
            Thread.sleep(4000);
            driver.findElement(By.cssSelector(OR.getProperty("Mat_Button"))).click();
            Thread.sleep(2000);
            logger.info("Compensation selected.");
            ExtentReporterNG.addStep("Compensation selected.");
            Thread.sleep(2000);

            // Save
            logger.info("Saving case.");
            driver.findElement(By.id(OR.getProperty("Inspector_Save"))).click();
            Thread.sleep(10000);
            logger.info("Case saved successfully.");
            ExtentReporterNG.addStep("Case saved successfully.");
            Thread.sleep(10000);

            // Generate Report
            logger.info("Generating report.");
            driver.findElement(By.id(OR.getProperty("GenerateReport"))).click();
            logger.info("Report generated successfully.");
            Thread.sleep(20000);
            ExtentReporterNG.addStep("Report generated successfully.");

            // Refresh and send report
            logger.info("Refreshing page and sending report.");
            driver.navigate().refresh();
            Thread.sleep(4000);
            driver.findElement(By.xpath("//span[text()=\"SEND\"]")).click();
            logger.info("Report sent successfully.");
            Thread.sleep(4000);
            ExtentReporterNG.addStep("Report sent successfully.");

            // Close popup
            logger.info("Closing popup.");
            driver.findElement(By.xpath("//span[text()=\" Close \"]")).click();
            logger.info("Popup closed.");
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted exception occurred.", e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred during inspector action.", e);
        }
    }
}
