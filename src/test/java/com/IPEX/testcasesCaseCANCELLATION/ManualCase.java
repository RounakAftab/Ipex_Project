package com.IPEX.testcasesCaseCANCELLATION;

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

public class ManualCase extends TestBase {

    private static final Logger logger = Logger.getLogger(ManualCase.class.getName());
    public JavascriptExecutor jse;

    @Test(priority = 2)
    public void createManualCase() {
        try {
            // Initialize JavascriptExecutor
            jse = (JavascriptExecutor) driver;

            // Go to Manual case page
            logger.info("Navigating to Manual case page.");
            driver.findElement(By.cssSelector(OR.getProperty("Cases_Menu"))).click();
            driver.findElement(By.cssSelector(OR.getProperty("Create_Case_Button"))).click();
            Thread.sleep(2000);
         
            // Select Insurance company
            logger.info("Selecting Insurance company.");
            driver.findElement(By.cssSelector(OR.getProperty("Insurance_Company"))).click();
            Thread.sleep(2000);

            List<WebElement> CompanyValues = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement CV : CompanyValues) {
                if (CV.getText().equalsIgnoreCase("Zurich")) {
                    CV.click();
                    break;
                }
            }

            // Select Client Tele
            logger.info("Selecting Client Tele.");
            driver.findElement(By.xpath(OR.getProperty("ClientTele"))).click();
            Thread.sleep(3000);

            List<WebElement> ClientT = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement CT : ClientT) {
                if (CT.getText().equalsIgnoreCase("Zurich KH Anprall")) {
                    CT.click();
                    break;
                }
            }

            // Inspector Select
            logger.info("Selecting Inspector.");
            driver.findElement(By.cssSelector(OR.getProperty("InspectorFieldClick"))).click();
            WebElement inspectorNameType = driver.findElement(By.cssSelector(OR.getProperty("InspectorNameWrite")));
            inspectorNameType.click();
            Thread.sleep(2000);
            inspectorNameType.sendKeys("New Stage Inspector");

            List<WebElement> suggList = driver.findElements(By.xpath(OR.getProperty("InspSelect")));
            Thread.sleep(2000);
            for (WebElement e : suggList) {
                if (e.getText().equalsIgnoreCase("New Stage Inspector")) {
                    e.click();
                    driver.findElement(By.cssSelector("div[class='options-section w-100-p']")).click();
                    break;
                }
                Thread.sleep(3000);
                driver.findElement(By.cssSelector("div[class='options-section w-100-p']")).click();
            }

            // Claim Number
            logger.info("Generating Claim Number.");
            Random random = new Random();
            int textLength = 15;
            StringBuilder randomText = new StringBuilder(textLength);
            for (int i = 0; i < textLength; i++) {
                int randomIndex = random.nextInt(characters.length());
                char randomChar = characters.charAt(randomIndex);
                randomText.append(randomChar);
            }

            String insClaimNumber = "Manual-Cancellation-" + randomText;
            WebElement Claim_Number = driver.findElement(By.cssSelector(OR.getProperty("ClaimNumber")));
            Claim_Number.sendKeys(insClaimNumber);
            Thread.sleep(2000);

            // get caseID
            caseID = driver.findElement(By.xpath(OR.getProperty("InsuClaimNumber"))).getAttribute("value");
            Thread.sleep(2000);            
            // Generate Report Name
            logger.info("Generating Report Name.");
            SimpleDateFormat ppdf = new SimpleDateFormat("dMyyyy");
            Date currentDate = new Date();
            String formattedDateCurrent = ppdf.format(currentDate);

            reportName = "Report_" + caseID + "_" + formattedDateCurrent + ".pdf";

            // Invoice Select
            logger.info("Selecting Invoice.");
            driver.findElement(By.cssSelector(OR.getProperty("SingleInvoice"))).click();
            Thread.sleep(2000);

            // Language Select
            logger.info("Selecting Language.");
            driver.findElement(By.cssSelector(OR.getProperty("LanguageSelect"))).click();
            List<WebElement> languageSel = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement ls : languageSel) {
                if (ls.getText().equalsIgnoreCase("English")) {
                    ls.click();
                    break;
                }
            }
            
            // Add auto-check service
            logger.info("Adding auto-check service.");
            driver.findElement(By.id("am-1076")).click();
            Thread.sleep(1000);
            List<WebElement> AutoType = driver.findElements(By.xpath(OR.getProperty("AutoVal")));

            for (WebElement autoTYP : AutoType) {
                if (autoTYP.getText().equalsIgnoreCase("ipex | check")) {
                    autoTYP.click();
                    break;
                }
            }

            
            // Insurance Mail Set
            logger.info("Setting Insurance Mail.");
            WebElement Insurance_Mail = driver.findElement(By.cssSelector(OR.getProperty("InsuranceMail")));
            Insurance_Mail.sendKeys(LoginData.getProperty("insuMail"));
            Thread.sleep(2000);
            
            // Case Type select
            logger.info("Selecting Case Type.");
            driver.findElement(By.cssSelector(OR.getProperty("CaseType"))).click();
            List<WebElement> CaseType = driver.findElements(By.cssSelector(OR.getProperty("CompanyVal")));
            for (WebElement Ctype : CaseType) {
                if (Ctype.getText().equalsIgnoreCase("divers")) {
                    Ctype.click();
                    break;
                }
            }
            
            // Zip code set
            logger.info("Setting Zip Code.");
            WebElement ZipC = driver.findElement(By.cssSelector(OR.getProperty("ZipCode")));
            ZipC.sendKeys(LoginData.getProperty("ZipValue"));
            Thread.sleep(2000);
            
            // Go to Item List
            logger.info("Navigating to Item List.");
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            WebElement itemListElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(OR.getProperty("ScrollToTheItem"))));
            jse.executeScript("arguments[0].scrollIntoView(true);", itemListElement);
            Thread.sleep(2000);
            driver.findElement(By.xpath(OR.getProperty("itemOpen"))).click();
            
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

            String unitPriceVal = "" + random.nextInt(300);
            driver.findElement(By.xpath(OR.getProperty("UnitPriceVal"))).sendKeys(unitPriceVal);
            Thread.sleep(2000);

            totalItemList = driver.findElements(By.cssSelector(OR.getProperty("TotalItemList"))).size();
            driver.findElement(By.cssSelector(OR.getProperty("SaveButton"))).click();

            Thread.sleep(8000);
            logger.info("Manual case created successfully.");
            
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
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Clicked on last created case"+ caseID);
            Thread.sleep(3000);
            
            driver.findElement(By.xpath(OR.getProperty("cancellationButton"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Clicked on Cancellation Button");
            driver.findElement(By.xpath(OR.getProperty("sayYES"))).click();
            Thread.sleep(4000);
            ExtentReporterNG.addStep("Click on Yes");
            
            //Click on last created case
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            driver.findElement(By.cssSelector(OR.getProperty("clearData"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            
            WebElement caseID4= driver.findElement(By.cssSelector(OR.getProperty("clickOnCaseIDField")));
            caseID4.sendKeys(caseID);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("applySearchButton"))).click();
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("caseClick"))).click();
            Thread.sleep(2000);
            ExtentReporterNG.addStep("Clicked on last created case"+ caseID);
            Thread.sleep(2000);
            
            driver.findElement(By.xpath(OR.getProperty("reactivation"))).click();
            Thread.sleep(4000);
            ExtentReporterNG.addStep("Reactivation Done");
            
            driver.findElement(By.xpath(OR.getProperty("sayYES"))).click();
            Thread.sleep(3000);
            ExtentReporterNG.addStep("Click on Yes");
            
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(OR.getProperty("clearData"))).click();
            Thread.sleep(1000);
            driver.findElement(By.cssSelector(OR.getProperty("clickOnFilter"))).click();
            Thread.sleep(1000);
            
            WebElement caseID5= driver.findElement(By.cssSelector(OR.getProperty("clickOnCaseIDField")));
            caseID5.sendKeys(caseID);
            Thread.sleep(2000);
            driver.findElement(By.cssSelector(OR.getProperty("applySearchButton"))).click();
            Thread.sleep(3000);
            ExtentReporterNG.addStep("Case Reactivated & test Done");
            

        } catch (InterruptedException e) {
            logger.log(Level.SEVERE, "Interrupted exception occurred.", e);
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred while creating manual case.", e);
        }
    }
}
