package APITesting.com.org.api;

import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import APTTesting.com.org.classes.Info;
import APTTesting.com.org.classes.Posts;
import APTTesting.com.org.classes.Post2;
import APITesting.com.org.classes.advanced.*;

import static org.hamcrest.Matchers.lessThan;
import static com.jayway.restassured.RestAssured.*;


public class JsonServerRequest {
	
	//displaying all records
	//@Test
	public void get_request(){
		Response resp = given().
				        when().
				        get("http://localhost:3000/posts");
		System.out.println(resp.asString());
	}
	
	//creating a new record
	//@Test
	public void post_request(){
		Response resp = given().
						body("{\"id\":\"2\",\"title\":\"new-server\",\"author\":\"jim\"}").
						when().
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");
	}
	
	//creating a new record using object
	//@Test
	public void post_request_using_object(){
		
		Posts posts = new Posts();
		posts.setId("3");
		posts.setTitle("automated-server");
		posts.setAuthor("Bean");
		
		given().
		body(posts).
		when().
		contentType(ContentType.JSON).
		post("http://localhost:3000/posts");
		
	}
	
	//displaying a particular record
	//displaying only particular record say 3
	//@Test
	public void get_request_particular_record(){
		Response resp = given().
						when().
						contentType(ContentType.JSON).
						get("http://localhost:3000/posts/3");
		System.out.println(resp.asString());
	}
	
	//updating a existing record
	//@Test
	public void put_request(){
		Posts posts = new Posts();
		posts.setId("3");
		posts.setTitle("automated-server-updated");
		posts.setAuthor("Mr.Bean");
		
		Response resp = given().
						body(posts).
						when().
						contentType(ContentType.JSON).
						put("http://localhost:3000/posts/3");
				
	}
	
	//updating using patch
	//@Test
	public void patch_request(){
		Response resp = given().
						body("{\"author\":\"Mrs.Beans\"}").
						when().
						contentType(ContentType.JSON).
						patch("http://localhost:3000/posts/3");
	}
	
	//deleting a record
	//@Test
	public void delete_request(){
		Response resp = given().
						when().
						delete("http://localhost:3000/posts/3");
	}
	
	//Complex post(use db2.json and start it before execution)
	//@Test
	public void complex_post_request(){
		Info info = new Info();
		info.setEmail("abc@gmail.com");
		info.setPhone("123456789");
		info.setAddress("22 Hill Court");
		
		Post2 post2 = new Post2();
		post2.setId("2");
		post2.setAuthor("Donald");
		post2.setTitle("Happy");
		post2.setInfo(info);
		
		Response resp = given().
		body(post2).
		when().contentType(ContentType.JSON).
		post("http://localhost:3000/posts");
		
		System.out.println(resp.asString());	

	}
	//Complex Post containing array(use db2advanced.json and start it before execution)
	//@Test
	public void complex_array_post_request(){
		InfoAd info1 = new InfoAd();
		info1.setEmail("testemail1@gmail.com");
		info1.setPhone("896754");
		info1.setAddress("Yoda street");
		
		InfoAd info2 = new InfoAd();
		info2.setEmail("testemail2@gmail.com");
		info2.setPhone("132628");
		info2.setAddress("Sudoko street");
		
		PostsAd posts = new PostsAd();
		posts.setId("2");
		posts.setAuthor("Craig");
		posts.setTitle("Mocking Bird");
		posts.setInfo(new InfoAd[]{info1,info2});
		
		Response resp = given().
						body(posts).
						when().
						contentType(ContentType.JSON).
						post("http://localhost:3000/posts");
		
		System.out.println(resp.asString());
			
	}
	
	//calculating response time
//	@Test
	public void response_time_get_request(){
	 Response resp = given().
			 		 when().
			 		 get("http://localhost:3000/posts");
	 
	 Long time= resp.
			 	then().
			 	extract().
			 	time();
	 
	 System.out.println(time); 
	 
	 
	 
	
	 }
	
	//verify time less that certain amount.
		 //first import org.hamcrest.Matchers.lessThan
	
	@Test
	public void assert_time_get_request(){
		         given().
				 when().
				 get("http://localhost:3000/posts").
				 then().
				 and().
				 time(lessThan(500L)); 
		         
	}
	

}
