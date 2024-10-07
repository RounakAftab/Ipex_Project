package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ITestListenerDemo implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
    ExtentReporterNG.createTest(result.getTestClass().getName());
		
	}

	
	@Override
	public void onFinish(ITestContext context) {
		ExtentReporterNG.flushReport();
	}

	

}
