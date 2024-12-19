package test_components;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import extentReportConfig.ExtentReportConfig;

public class Listeners implements ITestListener
{

ExtentTest test;	
		
ExtentReports extentReports	=ExtentReportConfig.getExtentreportObject();
	
ThreadLocal<ExtentTest>extentTest=new ThreadLocal<ExtentTest>();
	
	

@Override
public void onTestStart(ITestResult result) 
{		
	test = extentReports.createTest(result.getMethod().getMethodName());	
	extentTest.set(test);
		
}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
		 extentTest.get().log(Status.PASS,"Test Passed..!");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		extentTest.get().fail(result.getThrowable());
		
	}


	@Override
	public void onFinish(ITestContext context) {
		
		extentReports.flush();
		
		
	}




	
	
	
	
	
	
	
	
	
	
}
