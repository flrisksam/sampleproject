package APITestCase;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItem;
import io.restassured.http.ContentType;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import frameworkReference.driverClass;
import frameworkReference.helper;


public class apiTests extends driverClass 
{
	JSONObject request = new JSONObject();
	
	public apiTests(WebDriver driver, ExtentReports report, ExtentTest test) 
	{
		super(driver, report, test);
	}

	//Create pet verify if pet details
	public void postPets(int id, int categoryId, String categoryName, String name, String url, int tagId, String tagName, String status)
	{
		try
		{
			baseURI = "https://petstore.swagger.io/v2";
			
			ArrayList<String> photoUrls = new ArrayList<>();
			
			photoUrls.add(url);
							
			Map<Object, Object> category = new HashMap<>();
			category.put("id", categoryId);
			category.put("name", categoryName);
			
			Map<Object, Object> tag = new HashMap<>();
			tag.put("id", tagId);
			tag.put("name", tagName);
			
			List<Map<Object, Object>> petTag = new ArrayList<>();
			petTag.add(tag);
			
			request.put("id", id);
			request.put("category", category);
			request.put("name", name);
			request.put("photoUrls", photoUrls);
			request.put("tags", petTag);
			request.put("status", status);
			
			given().
			header("Content-Type", "application/json").
			accept(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/pet").
		then().
			body("id", equalTo(id)).
			statusCode(200).log().all();
			test.log(Status.PASS, "Create Pet Test passed");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, "Create Pet Test failed: "+e.getMessage().toString().trim());
		}report.flush();
	}
	
	//Create user and verify created user details
	public void createUser(String userName)
	{
		try
		{
			baseURI = "https://petstore.swagger.io/v2";
			
			request.put("id", 0);
			request.put("username", userName);
			request.put("firstName", userName);
			request.put("lastName", "Mohan");
			request.put("email", "sampathkumar_mohan@yahoo.co.in");
			request.put("password", "Sam@123");
			request.put("phone", "9994090993");
			request.put("userStatus", 0);
			given().
				header("Content-Type", "application/json").
				accept(ContentType.JSON).accept(ContentType.JSON).
				body(request.toJSONString()).
			when().
				post("/user").
			then().
				statusCode(200).
				body("firshName", hasItem(userName));
			test.log(Status.PASS, userName+" created");
		}
		catch(Exception e)
		{
			test.log(Status.FAIL, userName+" creation failed: "+e.getMessage().toString().trim());
		}report.flush();
	}
	
	//Purchase pet, verify order details.
		public void purchasePet(int petId)
		{
			try
			{
				baseURI = "https://petstore.swagger.io/v2";
						    
				request.put("id", 1);
				request.put("petId", petId);
				request.put("quantity", 1);
				request.put("shipDate", "2022-09-05T17:20:58.705Z");
				request.put("status", "placed");
				request.put("complete", true);
				given().
					header("Content-Type", "application/json").
					accept(ContentType.JSON).accept(ContentType.JSON).
					body(request.toJSONString()).
				when().
					post("/store/order").
				then().log().all().
				statusCode(200).
				body("petId", equalTo(petId));
				test.log(Status.PASS, petId+" purchased");
			}
			catch(Exception e)
			{
				test.log(Status.FAIL, petId+" purchase failed: "+e.getMessage().toString().trim());
			}report.flush();
		}
}
