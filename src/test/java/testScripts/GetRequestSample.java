package testScripts;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetRequestSample {
	@Test
	public void testresponseStatus() {
		RequestSpecification request= RestAssured.given();
		request.baseUri("https://demoqa.com/utilities/weather/city");
		Response response= request.get("/chennai");
		String resString= response.asPrettyString();
		System.out.println("response Details:"+resString);
		System.out.println("Response Headers:"+response.getContentType());
		System.out.println("Status Code:"+response.getStatusCode());
		ValidatableResponse valRes=response.then();
		valRes.statusCode(200);
		Headers allHeaders=response.headers();
		for(Header header:allHeaders) {
			System.out.println("Key:"+header.getName()+"   Value:"+header.getValue());
		}
		String serverType = response.header("Server");
		Assert.assertEquals(serverType,"nginx/1.17.10 (Ubuntu)");
		Assert.assertEquals(resString.toLowerCase().contains("chennai"), true);
		JsonPath jsonPathEval= response.jsonPath();
		System.out.println("City Name :"+jsonPathEval.get("City"));
		String cityName= jsonPathEval.get("City");
		Assert.assertEquals(cityName,"chennai","Expected city name is fetched");
}
	
	@Test
	public void testResponseBDD() {
		RestAssured.given()
		.baseUri("https://demoqa.com/utilities/weather/city")
		.when().get("/chennai")
		.then().statusCode(200);
	}
}
