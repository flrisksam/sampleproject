package APITestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import io.restassured.http.ContentType;
import io.restassured.response.*;

public class users 
{
	
	JSONObject request = new JSONObject();
	
	//@Test
	void getInventory()
	{
		
		Response getInventory = get("https://petstore.swagger.io/v2/store/inventory");
		System.out.println(getInventory.asString());
		System.out.println(getInventory.getStatusCode());
		Assert.assertEquals(getInventory.getStatusCode(), 200);
	}

	//@Test
	public void getInventory1()
	{
		given().
			get("https://petstore.swagger.io/v2/store/inventory").
		then().
			statusCode(200).
			log().all().
			body("sold", greaterThanOrEqualTo(1));
			//body("dd", equalTo(1))
			
	}
	

	public void createUser(String userName)
	{
		request.put("id", 0);
		request.put("username", userName);
		request.put("firstName", "Sam");
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
			post("https://petstore.swagger.io/v2/user").
			then().statusCode(200).log().all();
	}
	
	public void updateUser(String userName)
	{
		request.put("id", 0);
		request.put("username", userName);
		request.put("firstName", "Sampath");
		request.put("lastName", "Mohan");
		request.put("email", "sk040922@hym.com");
		request.put("password", "Sam@123");
		request.put("phone", "0123456789");
		request.put("userStatus", 0);
		given().
			header("Content-Type", "application/json").
			accept(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("https://petstore.swagger.io/v2/user/"+userName).
		then().
			body("firshName", hasItem("Sampath")).
			statusCode(200).log().all();
	}
	
	public void patchUser(String userName)
	{
		request.put("firstName", "Sampath");
		given().
			header("Content-Type", "application/json").
			accept(ContentType.JSON).accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("https://petstore.swagger.io/v2/user/"+userName).
		then().
			statusCode(200).
			log().all()
			.body("firshName", hasItem("Sampath"));
	}
	public void deleteUser(String userName)
	{
		baseURI = "https://petstore.swagger.io";
		given().
			header("Content-Type", "application/json").
			accept(ContentType.JSON).accept(ContentType.JSON).
		when().
			param("username", userName).
			delete("/v2/user/"+userName).
			then().statusCode(200).log().all();
	}
	
	public void getUser(String userName)
	{
		baseURI = "https://petstore.swagger.io";
		given().
			header("Content-Type", "application/json").
			accept(ContentType.JSON).accept(ContentType.JSON).param("username", userName);
		when().
			get("/v2/user/"+userName).
		then().
			body("firstName", equalTo("Sam")).
			statusCode(200).
			log().all();
	}
	
		//Post pet
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
			then().body("id", equalTo(id)).
				statusCode(200).log().all();
				
			}
			catch(Exception e)
			{
				e.getMessage().toString().trim();
			}
		}

	
	@Test
	public void run()
	{
		createUser("Sam");
		//updateUser("Sam");
		getUser("Sam");
		//patchUser("Sam");
		//deleteUser("Sam");
		postPets(8, 1, "Cat", "Mixed cats", "https://wallpapercave.com/wp/wv6mpZ5.jpg", 0, "Internal", "available");
	}
}
