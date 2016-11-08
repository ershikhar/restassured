package com.barclays.wealth;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

//import static com.jayway.restassured.RestAssured.given;
//import static io.restassured.RestAssured.*;
//io.restassured.matcher.RestAssuredMatchers.*
//org.hamcrest.Matchers.*

import static com.jayway.restassured.RestAssured.given; 
import static com.jayway.restassured.RestAssured.when; 
import static org.hamcrest.core.IsEqual.equalTo; 
//import static org.mockito.Matchers.anyString; 
//import static org.mockito.Matchers.eq; 
//import static org.mockito.Mockito.mock; 


public class sampletest {
	 @BeforeClass
	    public static void setup() {
	       
   RestAssured.baseURI="http://maps.googleapis.com";
   RestAssured.basePath="/maps/api/geocode/json";
	}
@Test	 
public void getTest(){
	 Response response;
	 String jsonAsString;
	 Object obj;
	 ValidatableResponse vr;
	 
	 
			 given().when().
     get("?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").
     then().
     contentType(ContentType.JSON).statusCode(200)
     .body("status", equalTo("OK"))
     .body("results.formatted_address", equalTo("[1600 Amphitheatre Pkwy, Mountain View, CA 94043, USA]"));
			 
    // extract().response();
	// vr= given().when().get("/maps/api/geocode/json?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.JSON);
	// vr.assertThat().
	// System.out.println(vr.toString());
	// jsonAsString=response.asString();
	 //System.out.println(jsonAsString);


}

}


