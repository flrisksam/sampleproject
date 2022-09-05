package testRunner;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import APITestCase.apiTests;
import fileReader.configPropertyFileReader;
import frameworkReference.helper;

import pages.dynamicTable;
import pages.homePage;
import pages.loadDelay;
import pages.sampleAppPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class TestNGRunner 
{

	static WebDriver driver;
	static ExtentTest test;
	static ExtentReports report;
	static ExtentSparkReporter spark;
	static configPropertyFileReader configPropReader;
	sampleAppPage loginpage;
	homePage homepage;
	dynamicTable dynamictable;
	loadDelay loaddelay;
	apiTests apitests;
	
  @Test (priority=1, description = "To verify valid, invalid user login")
  public void verifyValidLogin() 
  {
	  try 
	  {
		helper.assertion(homepage.goToMenu("Sample App"), "Sample App", test);
		loginpage.login("Sam", "pwd");
		helper.assertion(loginpage.loginStatus_lbl.getText().toString().trim(), "Welcome, Sam!", test);
		loginpage.login("Sam", "abc");
		helper.assertion(loginpage.loginStatus_lbl.getText().toString().trim(), "Invalid username/password", test);
		loginpage.login("abc", "");
		helper.assertion(loginpage.loginStatus_lbl.getText().toString().trim(), "Invalid username/password", test);
		loginpage.login("", "pwc");
		helper.assertion(loginpage.loginStatus_lbl.getText().toString().trim(), "Invalid username/password", test);
		loginpage.login("", "");
		helper.assertion(loginpage.loginStatus_lbl.getText().toString().trim(), "Invalid username/password", test);
		
		helper.assertion(homepage.goToHome(), "Quality is not an act, it is a habit.", test);
		test.log(Status.PASS, "Valid Login Test Success");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "Valid Login Test fail: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
   
  @Test (priority=2, description = "To verify Dynamic table Chrome CPU")
  public void verifyChromeCPU()
  {
	  try 
	  {
		helper.assertion(homepage.goToMenu("Dynamic Table"), "Dynamic Table", test);
		helper.assertion("Chrome CPU: "+dynamictable.getChromeCPU(), dynamictable.chromeCPU.getText().toString().trim(), test);
		helper.assertion(homepage.goToHome(), "Quality is not an act, it is a habit.", test);
		test.log(Status.PASS, "Chrome CPU test success");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "Chrome CPU test fail: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
  
  @Test (priority=3, description = "To verify load delay")
  public void verifyLoadDelay()
  {
	  try 
	  {
		helper.assertion(homepage.goToMenu("Load Delay"), "Load Delays", test);
		loaddelay.checkLoaded();
		helper.assertion(loaddelay.loadDelay_btn.getText().toString().trim(), "Button Appearing After Delay", test);
		helper.assertion(homepage.goToHome(), "Quality is not an act, it is a habit.", test);
		test.log(Status.PASS, "Load delay test success");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "Load delaye fail: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
  
  @Test (priority=4, description = "To verify add users")
  public void createUser()
  {
	  try 
	  {
		apitests.createUser("Sam_0509202202");
		test.log(Status.PASS, "Create user success");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "User creation fail: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
  
  @Test (priority=5, description = "To verify add pets")
  public void postPets()
  {
	  try 
	  {
		apitests.postPets(101, 1, "Cat", "Black cats", "https://images.pexels.com/photos/45201/kitty-cat-kitten-pet-45201.jpeg?cs=srgb&dl=pexels-pixabay-45201.jpg&fm=jpg", 0, "Internal", "available");
		test.log(Status.PASS, "Pet created success");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "Unable to create: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
  
  @Test (priority=6, description = "To verify purchase pet")
  public void purchasePet()
  {
	  try 
	  {
		apitests.purchasePet(101);
		test.log(Status.PASS, "Pet purchased");
	  }
	  catch(Exception e)
	  {
		  test.log(Status.FAIL, "Pet purchase failed: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }
  
  @BeforeMethod
  public void beforeMethod(ITestResult result) 
  {
	  test = report.createTest(result.getMethod().getDescription().toString()).log(Status.PASS, "Test Started");
	  loginpage = new sampleAppPage(driver, report, test);
	  homepage = new homePage(driver, report, test);
	  dynamictable = new dynamicTable(driver, report, test);
	  loaddelay = new loadDelay(driver, report, test);
	  apitests = new apiTests(driver, report, test);
  }

  @AfterMethod
  public void afterMethod(ITestResult result) 
  {
	  report.removeTest(result.getName());
  }

  @BeforeClass
  public void beforeClass() {
  }

  @AfterClass
  public void afterClass() {
  }

  @BeforeTest
  public void beforeTest() {
  }

  @AfterTest
  public void afterTest() 
  {
	  
  }

  @BeforeSuite
  public void beforeSuite() 
  {
	  System.out.println("Before Suite Starts");
	  openBrowser();
	  report = new ExtentReports();
	  spark = new ExtentSparkReporter("./test-output/TestReportRunner.html");
	  report.attachReporter(spark);
	  report.flush();
  }

  @AfterSuite
  public void afterSuite() 
  {
	  closeBrowser();
  }
  
  public void openBrowser()
  {
	  try
	  {
		  configPropReader = new configPropertyFileReader();
		  System.setProperty("webdriver.chrome.driver", configPropReader.ChromeDriverPath());
		  driver = new ChromeDriver();
		  driver.manage().window().maximize();
		  driver.get(configPropReader.URL());
		  Thread.sleep(3000);
		  System.out.println("Browser Intiated");
	  }
	  catch(Exception e)
	  {
		  System.out.println("Open browser error: "+e.getMessage().toString().trim());
	  }
  }
  
  public void closeBrowser()
  {
	  try
	  {
		  driver.close();
		  driver.quit();
	  }
	  catch(Exception e)
	  {
		  System.out.println("Close browser error: "+e.getMessage().toString().trim());
	  }
	  report.flush();
  }

}