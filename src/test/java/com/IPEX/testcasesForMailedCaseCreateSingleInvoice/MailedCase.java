package com.IPEX.testcasesForMailedCaseCreateSingleInvoice;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.IPEX.base.TestBase;

import utils.ExtentReporterNG;

public class MailedCase extends TestBase {

    private static final Logger logger = Logger.getLogger(MailedCase.class.getName());
    public JavascriptExecutor jse;

    @Test(priority = 2)
    public void createManualCase() {
        try {
            // Initialize JavascriptExecutor
            jse = (JavascriptExecutor) driver;

            // Go to Manual case page
            logger.info("Navigating to MAILED case page.");
            
            // Goto Mailbox Module
            driver.findElement(By.xpath("//span[text()=\"Mailbox\"]")).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Navigating to MAILED case page.");

            // Goto IPEX Automate Mailbox
            WebElement mailboxElement = driver.findElement(By.xpath("//div[text()=\" IPEX Automate \"]"));
            jse.executeScript("arguments[0].scrollIntoView(true);", mailboxElement);
            driver.findElement(By.xpath("//div[text()=\" IPEX Automate \"]")).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Navigated to IPEX Automate mailbox");
            

            // Goto Unread Category
            driver.findElement(By.xpath("//span[text()=\"Unread \"]")).click();
            Thread.sleep(3000);
            ExtentReporterNG.addStep("Navigated to unread category");


            int mailFound = 0;
            while (true) {
                for (int i = 0; i <= 9; i++) {
                    String makeMailID = "mailContainer" + i;
                    driver.findElement(By.id(makeMailID)).click();
                    Thread.sleep(2000);

                    List<WebElement> getInvoice = driver.findElements(By.className("doc-process"));
                    getInvoice.get(1).click();
                    String docProcessStatus = getInvoice.get(1).getText();
                    Thread.sleep(2000);

                    if (docProcessStatus.equals("Document Processing Complete")) {
                        // Click on Create Case
                        driver.findElement(By.id("am-1258")).click();
                        Thread.sleep(10000);
                        mailFound = 1;
                        break;
                    } else {
                        continue;
                    }
                }
                if (mailFound == 1) {
                    break;
                } else {
                    driver.findElement(By.xpath("//button[@aria-label=\"Next page\"]")).click();
                }
            }
         
            //PolicyHolder Phone Number
            logger.info("Policy holer phone number input");
            WebElement phPhoneN= driver.findElement(By.cssSelector(OR.getProperty("PHphonenumber")));
            phPhoneN.sendKeys("343444444");
            Thread.sleep(1000);
            ExtentReporterNG.addStep("Policy holder phone number inputted");

            // Inspector Select
            logger.info("Selecting Inspector.");
            driver.findElement(By.cssSelector(OR.getProperty("InspectorFieldClick"))).click();
            WebElement inspectorNameType1 = driver.findElement(By.cssSelector(OR.getProperty("InspectorNameWrite")));
            inspectorNameType1.click();
            inspectorNameType1.sendKeys("New Stage");
            Thread.sleep(2000);

            List<WebElement> suggList1 = driver.findElements(By.cssSelector(OR.getProperty("InspSelectMail")));
            Thread.sleep(3000);
            for (WebElement e9 : suggList1) {
                if (e9.getText().equalsIgnoreCase("New Stage Inspector")) {
                    e9.click();
                    driver.findElement(By.cssSelector("div[class='options-section w-100-p']")).click();
                    break;
                    
                }
            }
            Thread.sleep(1000);
            
            driver.findElement(By.cssSelector("div[class='options-section w-100-p']")).click();
            ExtentReporterNG.addStep("Inspector Selected");

            // Language Select
            logger.info("Selecting Language.");
            driver.findElement(By.cssSelector(OR.getProperty("LanguageSelect"))).click();
            List<WebElement> languageSel = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement ls : languageSel) {
                if (ls.getText().equalsIgnoreCase("English")) {
                    ls.click();
                    ExtentReporterNG.addStep("Language Select");
                    break;
                }
            }

            // get caseID
            Thread.sleep(2000);
            mailboxCaseID = driver.findElement(By.xpath("//input[@formcontrolname=\"InsuranceClaimNumber\"]")).getAttribute("value");
            
            // Generate Report Name
            logger.info("Generating Report Name.");
            SimpleDateFormat ppdf2 = new SimpleDateFormat("dMyyyy");
            Date currentDate = new Date();
            String formattedDateCurrent = ppdf2.format(currentDate);

            reportName = "Report_" + mailboxCaseID + "_" + formattedDateCurrent + ".pdf";

            // Invoice Select
            logger.info("Selecting Invoice.");
            driver.findElement(By.cssSelector(OR.getProperty("SingleInvoice"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Single Invoice. selected");
            
            // Add auto-check service
            logger.info("Adding auto-check service.");
            driver.findElement(By.id("am-1076")).click();
            Thread.sleep(1000);
            List<WebElement> AutoType = driver.findElements(By.xpath(OR.getProperty("AutoVal")));

            for (WebElement autoTYP : AutoType) {
                if (autoTYP.getText().equalsIgnoreCase("ipex | check")) {
                    autoTYP.click();
                    ExtentReporterNG.addStep("Auto check select");
                    break;
                }
            }

            
            // Insurance Mail Set
            logger.info("Setting Insurance Mail.");
            WebElement Insurance_Mail = driver.findElement(By.cssSelector(OR.getProperty("InsuranceMail")));
            Insurance_Mail.clear();
            Thread.sleep(1000);
            Insurance_Mail.sendKeys(LoginData.getProperty("insuMail"));
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Insurance Mail input");
            
            // Case Type select
            logger.info("Selecting Case Type.");
            driver.findElement(By.cssSelector(OR.getProperty("CaseType"))).click();
            List<WebElement> CaseType = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement Ctype : CaseType) {
                if (Ctype.getText().equalsIgnoreCase("divers")) {
                    Ctype.click();
                    ExtentReporterNG.addStep("Case Type Select");
                    break;
                }
            }
            
            //Zip code set
            logger.info("Setting Zip Code.");
            WebElement ZipC = driver.findElement(By.cssSelector(OR.getProperty("ZipCode")));
            ZipC.sendKeys(LoginData.getProperty("ZipValue"));
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Zip Code input done");
          
            // Go to Item List
            logger.info("Navigating to Item List.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement itemListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ScrollToTheItem"))));
            jse.executeScript("arguments[0].scrollIntoView(true);", itemListElement);
            Thread.sleep(2000);
            //driver.findElement(By.id(OR.getProperty("MailitemOpen"))).click();
            
          //Add more button
            WebElement moreButton= driver.findElement(By.cssSelector(OR.getProperty("addMoreButton")));
            Thread.sleep(1000);
            moreButton.click();
            
            driver.findElement(By.cssSelector(OR.getProperty("ItemDelete"))).click();
            List<WebElement> itemValues = driver.findElements(By.cssSelector(OR.getProperty("ITEMVal")));
            for (WebElement Itype : itemValues) {
                if (Itype.getText().equalsIgnoreCase("Delete Item")) {
                	Thread.sleep(2000);
                    Itype.click();
                    break;
                }
            }
            
            driver.findElement(By.xpath(OR.getProperty("yesButton"))).click();
            Thread.sleep(2000);      
            
            // Add Construction Company Name
            logger.info("Adding Construction Company Name.");
            WebElement CCNF = driver.findElement(By.cssSelector(OR.getProperty("ConstructionCompanyNameField")));
            CCNF.sendKeys("ConstructionCompanyName");
            Thread.sleep(2000);

            // Add Trade item
            logger.info("Adding Trade item.");
            driver.findElement(By.cssSelector(OR.getProperty("TradeClick"))).click();
            List<WebElement> tradeCollect = driver.findElements(By.xpath(OR.getProperty("TradeLst")));
            Thread.sleep(2000);
            for (WebElement tradeNme : tradeCollect) {
            	Thread.sleep(2000);
                if (tradeNme.getText().equalsIgnoreCase("Beton- und Stahlbetonarbeiten")) {
                    tradeNme.click();
                    break;
                }
            }

            Thread.sleep(3000);

            
            // Go and Add Sub item
            logger.info("Adding Sub item.");
            WebElement subItemListElement = driver.findElement(By.cssSelector(OR.getProperty("SubItemTitle")));
            jse.executeScript("arguments[0].scrollIntoView(true);", subItemListElement);
            Thread.sleep(2000);

            WebElement subItemSel = driver.findElement(By.cssSelector(OR.getProperty("subItemListSelect")));
            subItemSel.sendKeys("Test Sub-Item");
            Thread.sleep(2000);
            driver.findElement(By.xpath(OR.getProperty("SubItemQuantity"))).sendKeys("2");
            Thread.sleep(2000);
            
            Random random = new Random();
            String unitPriceVal = "" + random.nextInt(300);
            driver.findElement(By.xpath(OR.getProperty("UnitPriceVal"))).sendKeys(unitPriceVal);
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Item Add Done");

            totalItemList = driver.findElements(By.cssSelector(OR.getProperty("TotalItemList"))).size();
            
            logger.info("Mailed case for Single Invoice created successfully.");
            driver.findElement(By.xpath(OR.getProperty("MailedSaveButton"))).click();
            Thread.sleep(8000);
            ExtentReporterNG.addStep("Case Save Successfully");

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted exception occurred.", e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred while creating manual case.", e);
        }
    }
}







