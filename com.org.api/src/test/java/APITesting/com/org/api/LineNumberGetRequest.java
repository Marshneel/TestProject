package APITesting.com.org.api;

import static com.jayway.restassured.RestAssured.given;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

public class LineNumberGetRequest {
	
	//@Test
	public void AI_491_get_request(){
		Response resp = given().
						header("SystemInformation","MJ").
						header("username","adamr").
						header("password","password").
						contentType("application/json").
						when().
						get("http://test01-web01:9025/api/wlr3/LineDetails/01142755141");
		Assert.assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.asString());
		
	}
	
	@Test
	public void defaultValue_post_request(){
		Response resp = given().
						body("{\"ID\":\"901\",\"Description\":\"MJ Test\",\"ValueString\":\"Created by MJ\",\"ValueNumber\":\"1\",\"ValueDateTime\":\"2017-10-04T07:40:45.215\",\"ValueText\":\"This is created for testing purpose only\"}").
						header("SystemInformation","MJ").
						header("username","alperk").
						header("password","abcdef").
						contentType("application/json").
						when().
						post("http://test01-web01:9025/api/common/DefaultValue/901");
		//Assert.assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.asString());
	}
    //@Test
	public void defaultValue_patch_request(){
		Response resp = given().
						body("{\"ValueNumber\":\"0\"}").
						header("SystemInformation","MJ").
						header("username","alperk").
						header("password","abcdef").
						contentType("application/json").
						when().
						patch("http://test01-web01:9025/api/common/DefaultValue/885");
		Assert.assertEquals(resp.getStatusCode(),200);
		System.out.println(resp.asString());
	}

}
