package practice;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;


public class integrateReport {
public WebDriver driver;
ExtentReports extent;
	
	@BeforeTest
	public void config() {
		
		String path= System.getProperty("user.dir")+"\\reprts\\index.html";
		ExtentSparkReporter reporter=new ExtentSparkReporter(path);
		reporter.config().setReportName("Rounak's Test");
		reporter.config().setDocumentTitle("Test Result By Rounak");
	
	     extent = new ExtentReports();
	     extent.attachReporter(reporter);
	     extent.setSystemInfo("Tester", "Rounak Aftab");
	
	}
	
	@Test
	public void inteReport() {
		
		ExtentTest test= extent.createTest("Rounak's 1st Extent Report");
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.get("https://rahulshettyacademy.com/");
		 System.out.println(driver.getTitle());
		 
		 test.fail("This is Failed");
		 extent.flush();
		 
		
		
		
	}

}
