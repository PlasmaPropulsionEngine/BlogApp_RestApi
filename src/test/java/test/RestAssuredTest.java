package test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.reporters.Files;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import payloads.CategoryRequest;
import payloads.CategoryResponce;
import payloads.LoginRequest;
import payloads.LoginResponcePojo;
import payloads.PostRequest;
import payloads.PostResponce;



public class RestAssuredTest {

	
@Test	
public void endToEndTest()
{
	
	// test 1: login 		
		//spec builder request headers	
		  RequestSpecification req = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
		.setContentType(ContentType.JSON).build();
		  
		//login responce pojo object	
		LoginRequest loginRequest=new LoginRequest();
		
		//set login details
		loginRequest.setUsername("postman07@gmail.com");
		loginRequest.setPassword("test");		
	// post req for login
		RequestSpecification reqLogin = given().spec(req).log().all().body(loginRequest);
		LoginResponcePojo loginResponce = reqLogin.when().post("/v1/auth/login").then().log().all().extract().response().as(LoginResponcePojo.class);

		String jwtToken = loginResponce.getJwtToken();
		int user_id = loginResponce.getUser_id();
		
	// end login req /////	
		
		
	// test 2: add category 		
		//first add category of your blog it may drop down or autosuggetion 
		
		 RequestSpecification addCategoryReq = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
		 .setContentType(ContentType.JSON).addHeader("Authorization","Bearer "+jwtToken).build();
		
		 CategoryRequest categoryRequest=new CategoryRequest();
		 
		 categoryRequest.setCategoryTitle("black adam");
		
		 RequestSpecification categoryReq = given().spec(addCategoryReq).log().all().body(categoryRequest);
		 
		 CategoryResponce categoryResponce = categoryReq.when().post("/category").then().log().all().extract().response().as(CategoryResponce.class);
		
		  System.out.println(categoryResponce.getCategoryTitle());
		  int cid = categoryResponce.getCid();

//		end add category//
		  
		  		
	// test 3: add blog 	  
		  RequestSpecification addBlogReq = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
		 .setContentType(ContentType.JSON).addHeader("authorization","Bearer "+jwtToken).build();
		  
		  PostRequest postRequest=new PostRequest();
		
		  postRequest.setTitle("super heros");
		  postRequest.setContent("spider and robin");
		  
		  RequestSpecification blogpost = given().spec(addBlogReq).log().all().body(postRequest);
		  PostResponce as = blogpost.when().post("/user/"+user_id+"/category/"+cid+"/posts").then()
		.log().all().assertThat().statusCode(201).extract().response().as(PostResponce.class);
		  System.out.println(as.getPId());
			
		  Integer pId = as.getPId();

		
	//test 4: get blog by post id	
		  RequestSpecification getBog = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
		.setContentType(ContentType.JSON).addHeader("authorization","Bearer "+jwtToken).build();	
		
		
			String postById = given().spec(getBog).log().all().when().get("/posts/"+pId)
			.then().log().all().assertThat().statusCode(202).extract().response().asString();
			
			System.out.println(postById);
			

	//test 5 add image api		
			RequestSpecification addimage = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
			.addHeader("authorization","Bearer "+jwtToken).build();	
						
			String imageResponce = given().log().all().spec(addimage)
			.multiPart("image",new File(System.getProperty("user.dir")+"\\images\\profile.png"))
			.when().post("/post/image/upload/"+pId)
			.then().log().all().assertThat().statusCode(202).extract().response().asString();
			System.out.println(imageResponce);
			
			
			
	//test 6: delete post by post id
			
		 RequestSpecification delete = new RequestSpecBuilder().setBaseUri("http://localhost:9092/api")
		.setContentType(ContentType.JSON).addHeader("authorization","Bearer "+jwtToken).build();
		 
		 String deleteString = given().spec(delete).log().all().when().delete("/posts/"+pId).then()
		 .log().all().assertThat().statusCode(200).extract().response().asString();
		 
		 System.out.println(deleteString);
		 
		 JsonPath jp=new JsonPath(deleteString);
		 
		 String message = jp.get("message");
		 
		 Assert.assertEquals(message,"Post deleted succesfully!");
		 
		}	
	
	
	
}
	
	
	