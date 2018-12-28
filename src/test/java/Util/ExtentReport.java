package Util;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.xml.XmlSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReport implements IReporter{
	private ExtentReports extent;
	
	
	
	@Override
	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		System.out.println("starting report:" + cal.getTimeInMillis());
		
		this.extent = new ExtentReports("./target/surefire-reports/TestExecutionReport.html", Boolean.valueOf(true));
		
		
	    addDetailstoTheExtentReport(suites);
	    
	    for (ISuite suite : suites) {
			Map<String, ISuiteResult> result = suite.getResults();
		for (ISuiteResult r : result.values()) {
				ITestContext context = r.getTestContext();
				buildTestNodes(context.getPassedTests(), LogStatus.PASS);
				buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
				buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
				}
			
		}           
	    
	       
	    this.extent.flush();
		this.extent.close();
		Calendar cal1 = Calendar.getInstance();
		System.out.println("end of the report:" + cal1.getTimeInMillis());
	}


	public void addDetailstoTheExtentReport(List<ISuite> suites) {
		
		
		this.extent.addSystemInfo("haha","haha");
	}

	 private void buildTestNodes(IResultMap tests, LogStatus status) {
		 Calendar cal = Calendar.getInstance();
			if (tests.size() <= 0)
				return;  
			for (ITestResult result : tests.getAllResults()) {
				String finalDesc = result.getMethod().getDescription();
				String testClassName = result.getMethod().getTestClass().getName();
				String testMethodName = result.getMethod().getMethodName();				
				int arrlen = testClassName.split("\\.").length;
			ExtentTest test = this.extent.startTest(testMethodName+" ( "+finalDesc+" )");
			if (arrlen > 0)
				test.assignCategory(new String[]{testClassName.split("\\.")[(arrlen - 1)]});
			else {
				test.assignCategory(new String[]{testClassName});
			}
			test.setStartedTime(getTime(result.getStartMillis()));
			test.setEndedTime(getTime(result.getEndMillis()));
			String[] arrayOfString;
			
            if (result.getThrowable() != null) {
           	if (result.getThrowable().toString().contains("java.lang.AssertionError")) {
					test.log(LogStatus.INFO, getparams(result)+"");
					test.log(LogStatus.INFO, finalDesc);
					test.log(status, result.getThrowable());
					test.log(LogStatus.INFO,"Screenshot"+"   "+test.addScreenCapture(System.getProperty("user.dir")+"/Screenshots/"+testClassName+"_"+testMethodName+".png"));
					
					

					

				} else {
					test.log(LogStatus.INFO, getparams(result) + "");
					test.log(LogStatus.INFO, finalDesc);
					test.log(LogStatus.SKIP, result.getThrowable());
					test.log(LogStatus.INFO,"Screenshot"+"   "+test.addScreenCapture(System.getProperty("user.dir")+"/Screenshots/"+testClassName+"_"+testMethodName+".png"));

				}
			}else {
				test.log(status, "Test " + status.toString().toLowerCase() + "ed");
			}

			
            this.extent.endTest(test);
			}	
	 }
	 
	 

		private Date getTime(long millis) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(millis);
			return calendar.getTime();
		}

		
		private StringBuilder getparams(ITestResult result) {
			StringBuilder testdata = new StringBuilder();
			testdata.append("TestData:<br>");
			Object[] parameters = result.getParameters();
			for (int i = 0; (parameters != null) && (i < parameters.length); ++i) {
				Object parameter = parameters[i];
				testdata.append("param[" + i + "]: ").append(parameter).append(",");
			}
			return testdata;
		}
		
}
