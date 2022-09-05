package frameworkReference;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class testNGITestListener implements ITestListener
{

	static ExtentTest test;
	static ExtentReports report;
	static ExtentSparkReporter spark;
	
	@Override
	public void onTestStart(ITestResult result)
	{
		/*
		report = new ExtentReports();
		spark = new ExtentSparkReporter("./test-output/TestReport.html");
		report.attachReporter(spark);
		*/
		System.out.println(result.getName());
		System.out.println(result.getTestName());
		System.out.println(result.getInstanceName());
		//test = report.createTest(result.getName()).log(Status.PASS, "Test Started Successfully");
	}
	
	
}
