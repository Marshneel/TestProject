package APITesting.com.org.api;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class WeatherGetRequest {
	
	//simple get request for getting weather request by City Name
	//@Test
	public void simple_get_request(){
		Response resp = when().
							get("http://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b1b15e88fa797225412429c1c50c122a1");
		Assert.assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.asString());
		
	}
	
	//using parameters using test assured
	//@Test
	public void parameterised_get_request(){
		Response resp = given().
								param("q","London,uk").
								param("appid","b1b15e88fa797225412429c1c50c122a1").
						when().
							get("http://samples.openweathermap.org/data/2.5/weather");
		Assert.assertEquals(resp.getStatusCode(),200);
		
	}
	//Assertion using rest assured
	//@Test
	public void asserted_parameterised_get_request(){
		
						given().
								param("q","London,uk").
								param("appid","b1b15e88fa797225412429c1c50c122a1").
						when().
							get("http://samples.openweathermap.org/data/2.5/weather").
						then().
							assertThat().statusCode(200);
		
	}
	
	//Assert response is json type
	//@Test
	public void assert_response_type(){
		                given().
						param("id","2172797").
						param("appid","b1b15e88fa797225412429c1c50c122a1").
						when().
						get("http://samples.openweathermap.org/data/2.5/weather").
						then().
						assertThat().contentType(ContentType.JSON);
	}
	
	//json path query
	//@Test
	public void query_extration(){
	String query =	given().
		param("id", "2172797").
		param("appid","b1b15e88fa797225412429c1c50c122a1").
		when().
		get("http://samples.openweathermap.org/data/2.5/weather").
		then().
		extract().
		path("weather[0].description");
	
	System.out.println(query);
	
	}
	//assert query
	//@Test
	public void assert_query(){
		Response resp = given().
				param("id", "2172797").
				param("appid","b1b15e88fa797225412429c1c50c122a1").
				when().
				get("http://samples.openweathermap.org/data/2.5/weather");
		Assert.assertEquals(resp.then().extract().path("weather[0].description"),"scattered clouds");
	}
	
	//Transferring API response to other API
	//@Test
	public void assert_api(){
		Response resp1 = given().
				parameter("q","London,uk").
				parameter("appid","d3fd2ec3cf7cce74ef65a14808059476").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");
		
		String id = String.valueOf(resp1.then().contentType(ContentType.JSON).extract().path("id"));
		    
		Response resp2 = given().
				parameter("id",id).
				parameter("appid","d3fd2ec3cf7cce74ef65a14808059476").
				when().
				get("http://api.openweathermap.org/data/2.5/weather");
		
		Assert.assertEquals(resp1.asString(), resp2.asString());
		
	}
	
	
	
	
}
