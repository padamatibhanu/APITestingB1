package testScripts;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PutRequestSample {
	int userId=2;
  //@Test
  public void updateUser() {
	  RestAssured.baseURI ="https://reqres.in/api/users";
	  JSONObject requestParams = new JSONObject();
	  requestParams.put("name", "Peter");
	  requestParams.put("job", "Project Leader");
	  Response res= RestAssured.given()
	  .accept(ContentType.JSON)
	  .contentType("application/json")
	  .and()
	  .body(requestParams.toString())
	  .put("/2");
	  System.out.println("Status code : "+res.getStatusCode());
	  System.out.println("Is Job of employee is Project Leader :"+res.asString().contains("Project Leader"));
	  Assert.assertTrue(res.asString().contains("Project Leader"));
  }
  
  @Test
  public void deleteUser() {
	  RestAssured.baseURI ="https://reqres.in/api/users";
	  Response res= RestAssured.given()
			  .delete("/2");
	  System.out.println("Status code after delete :"+res.getStatusCode());
  }
}
