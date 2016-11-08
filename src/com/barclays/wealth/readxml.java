package com.barclays.wealth;

import static com.jayway.restassured.RestAssured.given;

import java.util.List;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.path.xml.XmlPath;
import com.jayway.restassured.response.Response;

public class readxml {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Abc123");
		Response response;
		 String xmlAsString;
		 
      response= given().when().get("http://maps.googleapis.com/maps/api/geocode/xml?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.XML).extract().response();
	 xmlAsString=response.asString();
//System.out.println(xmlAsString);
	 XmlPath xmlPath = new XmlPath(xmlAsString);
	 
	 String stat= xmlPath.getString("GeocodeResponse.status");
	// String stat1= xmlPath.getString("results.address_components[0].long_name");
//	 List<String> winnerIds = xmlPath.getList("results[0].address_components[0].long_name");
//	 
//	 for (int i=0;i<winnerIds.size();i++)
//	 {
//		 System.out.println("===:"+winnerIds.get(i));
//	 }
//	 
 System.out.println(stat);
//	 System.out.println(stat1);
	}

}
