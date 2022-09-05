package frameworkReference;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class helper extends driverClass
{

	public helper(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}
	
	public static void fluentWait(WebDriver driver, WebElement element, ExtentTest test)
	{
		Wait<WebDriver> gWait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(60))
				.pollingEvery(Duration.ofSeconds(2))
				.ignoring(NoSuchElementException.class);
		gWait.until(ExpectedConditions.visibilityOf(element));
		gWait.until(ExpectedConditions.elementToBeClickable(element));
	}
	
	public static void click(WebElement element, String value, ExtentTest test)
	{
		try
		{
			fluentWait(driver, element, test);
			element.click();
			test.log(Status.PASS, value+" clicked.");
		}
		catch (Exception e)
		{
			test.log(Status.FAIL, "Unable to click: "+e.getMessage().toString().trim());
		}
	}
	
	public static void sendKeys(WebElement element, String value, ExtentTest test)
	{
		try
		{
			fluentWait(driver, element, test);
			element.sendKeys(value);
			test.log(Status.PASS, value+" entered.");
		}
		catch (Exception e)
		{
			test.log(Status.FAIL, "Unable to enter: "+e.getMessage().toString().trim());
		}
	}
	
	public static void assertion(Object actual, Object expected, ExtentTest test)
	{
		try
		{
			Assert.assertEquals(actual, expected);
			test.log(Status.PASS, actual+" matched "+expected);
		}
		catch(AssertionError e)
		{
			test.log(Status.FAIL, "Assertion mismatch: Actual "+actual+","
					+ " but expected "+expected);
		}
	}
	
}