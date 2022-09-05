package frameworkReference;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class driverClass 
{
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest test;

	
	public driverClass(WebDriver driver, ExtentReports report, ExtentTest test)
	{
		driverClass.driver=driver;
		driverClass.report=report;
		driverClass.test=test;
		PageFactory.initElements(driver, this);
	}
}
