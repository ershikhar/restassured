package com.barclays.wealth;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import org.junit.Assert;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Abc123");
		Response response;
		 String jsonAsString;
		 
      response= given().when().get("http://maps.googleapis.com/maps/api/geocode/json?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.JSON).extract().response();
	 jsonAsString=response.asString();
	// System.out.println(jsonAsString);
	 JsonPath jsonPath = new JsonPath(jsonAsString);
	 String stat= jsonPath.getString("results[0].formatted_address");
	 String stat1= jsonPath.getString("results.address_components[0].long_name");
	 List<String> winnerIds = jsonPath.getList("results.address_components[0].long_name");
	// Assert.assertEquals(""a, actuals);
	 
	 for (int i=0;i<winnerIds.size();i++)
	 {
		 System.out.println("===:"+winnerIds.get(i));
	 }
	 
	 System.out.println(stat);
	 System.out.println(stat1);

	}

}
