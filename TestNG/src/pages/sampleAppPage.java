package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import frameworkReference.driverClass;
import frameworkReference.helper;

public class sampleAppPage extends driverClass 
{
	public sampleAppPage(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}
	
	@FindBy(name="UserName")
	public WebElement userName_txt;
	
	@FindBy(how=How.NAME, using = "Password")
	public WebElement passWord_txt;
	
	@FindBy(how=How.XPATH, using="//button[@id='login']")
	public WebElement login_btn;
	
	@FindBy(how=How.XPATH, using="//label[@id='loginstatus']")
	public WebElement loginStatus_lbl;

	public void login(String userName, String password)
	{
		try
		{
			if(login_btn.getText().toString().trim().equals("Log Out"))
			{
				helper.click(login_btn, "login_btn", test);
			}
			helper.sendKeys(userName_txt, userName, test);
			helper.sendKeys(passWord_txt, password, test);
			helper.click(login_btn, "login_btn", test);
			test.log(Status.PASS, "Login with "+userName+" and "+password);
			Thread.sleep(3000);
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Login fail: "+e.getMessage().toString().trim());
		}
	}
}
