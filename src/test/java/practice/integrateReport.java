package practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;


public class integrateReport {
public WebDriver driver;

	
	
	public void inteReport() {
		
	
		 WebDriverManager.chromedriver().setup();
		 driver = new ChromeDriver();
		 driver.get("https://yopmail.com/en/");
		 driver.findElement(By.id("refreshbut")).click();
		 
		 
		 
		
		
		
	}

}
