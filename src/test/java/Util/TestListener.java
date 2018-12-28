package Util;

import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestNGMethod;
import org.testng.ITestResult;

import com.qa.reports.Base;

public class TestListener extends Base implements ITestListener {

	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		Object currentClass = result.getInstance();
	      
		
		String testClassName = result.getMethod().getTestClass().getName();
		String testMethodName = result.getMethod().getMethodName();	
		System.out.println("***** Error "+testClassName+"_"+testMethodName+" test has failed *****");
    	new GenericFunctions().captureScreenshot(((Base) currentClass).getDriver(), testClassName+"_"+testMethodName);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
Object currentClass = result.getInstance();
	      
		
		String testClassName = result.getMethod().getTestClass().getName();
		String testMethodName = result.getMethod().getMethodName();	
		System.out.println("***** Error "+testClassName+"_"+testMethodName+" test has skipped *****");
    	new GenericFunctions().captureScreenshot(((Base) currentClass).getDriver(), testClassName+"_"+testMethodName);
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		Set<ITestResult> failedTests = context.getFailedTests().getAllResults();
		for (ITestResult temp : failedTests) {
			ITestNGMethod method = temp.getMethod();
			if (context.getFailedTests().getResults(method).size() > 1) {
				failedTests.remove(temp);
			} else {
				if (context.getPassedTests().getResults(method).size() > 0) {
					failedTests.remove(temp);
				}
			}
		
	}

	}
	}
