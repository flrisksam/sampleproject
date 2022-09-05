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

public class dynamicTable extends driverClass 
{
	public dynamicTable(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}
	
	@FindBy(how=How.XPATH, using="//p[@class='bg-warning']")
	public WebElement chromeCPU;

	public String getChromeCPU()
	{
		int Column = 0, Row=0;
		String CPU = null;
		try
		{
			for(int i=1; i<6; i++)
			{
				String name = driver.findElement(By.xpath("//div[@role='rowgroup'][1]//div[@role='row'][1]/span["+i+"]")).getText().toString().trim();
				if(name.equals("CPU"))
				{
					Column = i;
					break;
				}
			}
			for(int j=1; j<5; j++)
			{
				String name = driver.findElement(By.xpath("//div[@role='rowgroup'][2]//div[@role='row']["+j+"]/span[1]")).getText().toString().trim();
				if(name.equals("Chrome"))
				{
					Row = j;
					break;
				}
			}
			CPU = driver.findElement(By.xpath("//div[@role='rowgroup'][2]//div[@role='row']["+Row+"]/span["+Column+"]")).getText().toString().trim();
			test.log(Status.PASS, "Value is "+CPU);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Fail: "+e.getMessage().toString().trim());
		}
		return CPU;
	}
}
