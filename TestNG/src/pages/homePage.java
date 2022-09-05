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

public class homePage extends driverClass 
{

	public homePage(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}
	
	@FindBy(how=How.XPATH, using="//div[@class='container']/h3")
	public WebElement pageTitle_txt;
	
	@FindBy(how=How.XPATH, using="//a[contains(text(),'Home')]")
	public WebElement home_a;
	
	@FindBy(how=How.XPATH, using="//blockquote[@id='citation']/p")
	public WebElement homeQuotes_lbl;
	
	public String goToMenu(String menu)
	{String pageTitle = null;
		try
		{
			helper.click(driver.findElement(By.xpath("//a[contains(text(),'"+menu+"')]")), "Click menu"+menu, test);
			pageTitle = pageTitle_txt.getText().toString().trim();
			test.log(Status.PASS, "Menu clicked "+menu+" page reached "+pageTitle);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Home title "+pageTitle+" error "+e.getMessage().toString().trim());
		}
		return pageTitle;
	}
	
	public String goToHome()
	{String pageTitle = null;
		try
		{
			helper.click(home_a, "Click Home", test);
			pageTitle = homeQuotes_lbl.getText().toString().trim();
			test.log(Status.PASS, "Home title ");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Click home title "+e.getMessage().toString().trim());
		}
		return pageTitle;
	}

}
