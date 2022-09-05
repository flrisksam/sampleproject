package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import frameworkReference.driverClass;
import frameworkReference.helper;

public class loadDelay extends driverClass
{

	public loadDelay(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}

	@FindBy(how=How.XPATH, using="//button[@class='btn btn-primary' and normalize-space(text()='Button Appearing After Delay')]")
	public WebElement loadDelay_btn;;

	public void checkLoaded()
	{
		try
		{
			helper.click(loadDelay_btn, "Click Load Delay Button", test);
			test.log(Status.PASS, "Column value is ");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Login fail: "+e.getMessage().toString().trim());
		}
	}
	
}