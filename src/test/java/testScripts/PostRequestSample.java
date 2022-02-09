package testScripts;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostRequestSample {
	//@Test
	public void postUser() {
		//RestAssured.baseURI="https://gorest.co.in/public/v2/users";
		RestAssured.baseURI="https://reqres.in/api/users";
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("name","TestUser");
		jsonObject.put("job","TestArchitect");
//		jsonObject.put("email", "testuser@gmail.com");
//		jsonObject.put("gender", "female");
//		jsonObject.put("status", "active");
		Response resp= RestAssured.given()
				.accept(ContentType.JSON)
				.contentType("application/JSON")
				.and()
				.body(jsonObject.toString())
				.post();
		System.out.println("Status code : "+resp.getStatusCode());
	}
	
	@Test
	public void postUserWithAuth() {
		//RestAssured.baseURI="https://gorest.co.in/public/v2/users";
		RestAssured.baseURI="https://reqres.in/api/users";
		JSONObject jsonObject= new JSONObject();
		jsonObject.put("email", "testuser@gmail.com");
		jsonObject.put("gender", "female");
		jsonObject.put("status", "active");
		System.out.println(jsonObject.toString());
		Response resp= RestAssured.given()
				.header("authorization","Bearer 8c2cfec7f98ca22f78f61bd33e209bbdf9ad1723fabf06e5c8a7513eb5e0e031")
				.accept(ContentType.JSON)
				.contentType("application/JSON")
				.and()
				.body(jsonObject.toString())
				.post();
		System.out.println("Status code : "+resp.getStatusCode());
		System.out.println(resp.toString());
	}
}
