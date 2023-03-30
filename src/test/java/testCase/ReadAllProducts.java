package testCase;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReadAllProducts {
	@Test
	public void readAllProducts() {
		
		Response response =
	given()
		.baseUri("https://techfios.com/api-prod/api/product")
		.header("Content-Type", "application/json; charset=UTF-8")
		.auth().preemptive().basic("demo@techfios.com", "abc123")
	.when()
		.get("/read.php")
	.then()
		.extract().response();
		
		int statusCode  =response.getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		long responseTime = response.getTimeIn(TimeUnit.MILLISECONDS);
		System.out.println("Response Time: "+responseTime);
		
		if(responseTime <=2000) {
			System.out.println("Response time is within the range.");
		}else {System.out.println("Response time is out of range.");
		
		}
		
		String responseHeader = response.getHeader("Content-Type");
		System.out.println("Response Header ContentType : "+ responseHeader);
		Assert.assertEquals(responseHeader,  "application/json; charset=UTF-8");
		
		String responseBody = response.getBody().asString();
//		System.out.println("Response Body: " + responseBody);
		
		JsonPath jsonPath = new JsonPath(responseBody);
		
		String firstProductId = jsonPath.getString("records[0].id");
		System.out.println("First Prodcut ID: "+ firstProductId);
		
		if(firstProductId != null) {
			System.out.println("Records are not null.");
			
		}else {
			System.out.println("Records are null.");
		}
		
	}
}
