package testScripts;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class BasicAuthSample {
	// @Test
	public void preemptiveAuth() {
		RestAssured.given().auth().preemptive().basic("postman", "password").when()
				.get("https://postman-echo.com/basic-auth").then().log().body();
	}

	@Test
	public void challengedAuth() {
		RestAssured.given().auth().basic("postman", "password").when().get("https://postman-echo.com/basic-auth").then()
				.log().body();
	}
	
	@Test
	public void getWeatherWithAPI() {
		RestAssured.given().queryParam("q","chennai")
		.queryParam("appid", "99aa57d5e77052343405cf6b9e646c9d")
		.when().get("http://api.openweathermap.org/data/2.5/weather")
		.then().log().body();
	}
	
	@Test
	public void validCountry() {
		String strCountry= RestAssured.given().queryParam("q","chennai")
		.queryParam("appid", "99aa57d5e77052343405cf6b9e646c9d")
		.when().get("http://api.openweathermap.org/data/2.5/weather")
		.then().extract().path("sys.country");
		Assert.assertTrue(strCountry.equals("IN"));
	}
	
	@Test
	public void getWeather() {
		String mainWeather= RestAssured.given().queryParam("q","chennai")
		.queryParam("appid", "99aa57d5e77052343405cf6b9e646c9d")
		.when().get("http://api.openweathermap.org/data/2.5/weather")
		.then().extract().path("weather[0].main");
		System.out.println("Main Weather is :"+mainWeather);
	}
}
