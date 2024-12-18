package test_components;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener
{

	@Override
	public void onTestSuccess(ITestResult result) {
		
		
		System.out.println(result.getMethod().getMethodName());
		
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		System.out.println(result.getMethod().getMethodName());

	}




	
	
	
	
	
	
	
	
	
	
}
