package com.IPEX.base;


import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.ExtentReporterNG;


public class TestBase {

    public static WebDriver driver;
    public static Properties config = new Properties();
    public static Properties OR = new Properties();
    public static Properties LoginData = new Properties();
    public static FileInputStream fis;
    public static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    public static float quantity, unit;
    public static String reportName, pdfText, currentLanguageSelector, checkInvoiceSystem, caseID, mailboxCaseID, UploadcaseID;
    public static int totalItemList;

    private static final Logger logger = Logger.getLogger(TestBase.class.getName());
    

    @BeforeSuite
    public void setUP() {
    	
    	ExtentReporterNG.setUpReport();
    	ChromeOptions options = new ChromeOptions();
        
    	
    	
        if (driver == null) {
            try {
                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\Config.properties");
                config.load(fis);
                logger.info("Config properties loaded.");

                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
                OR.load(fis);
                logger.info("OR properties loaded.");

                fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\LoginData.properties");
                LoginData.load(fis);
                logger.info("LoginData properties loaded.");
                
            } catch (IOException e) {
                logger.log(Level.SEVERE, "Failed to load properties files.", e);
                throw new RuntimeException("Failed to load properties files.", e);
            }

            try {
                String browser = config.getProperty("browser");
                if (browser.equals("chrome")) {
                    WebDriverManager.chromedriver().setup();
                    options.addArguments("--force-device-scale-factor=0.8");
                    driver = new ChromeDriver(options);
                    logger.info("ChromeDriver initialized.");
                } else if (browser.equals("firefox")) {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    logger.info("FirefoxDriver initialized.");
                } else {
                    throw new RuntimeException("Unsupported browser: " + browser);
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
                driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(100));
                driver.manage().window().maximize();
                logger.info("Browser settings configured."); 
                driver.get(config.getProperty("testsiteurl"));
                logger.info("Navigated to test site URL.");
               

            } catch (Exception e) {
                logger.log(Level.SEVERE, "Failed to initialize the browser driver.", e);
                throw new RuntimeException("Failed to initialize the browser driver.", e);
            }
            
        }
        
        
    }
    
    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            logger.info("Browser closed and driver quit.");
        }
    }
    
   
}
