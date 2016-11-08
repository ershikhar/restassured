package com.amazon.stepin;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

public class test {
	public static StringBuilder str;
	public static BufferedWriter bw;
	
	
	 @BeforeClass
	    public static void setup() throws IOException {
	       
  RestAssured.baseURI="http://localhost:9999";
//  RestAssured.basePath="/api/v1/shipwrecks";
  
   str = new StringBuilder();
  File file = new File("./TestReport/filename2.txt");
  FileWriter fw = new FileWriter(file.getAbsoluteFile());
	 bw = new BufferedWriter(fw);
	// if file doesnt exists, then create it
	if (!file.exists()) {
		file.createNewFile();
	}
	}
	 
	 
	 @AfterClass
	 public static void teardown() throws IOException {
	 
		 bw.write(str.toString());
	       bw.close();
	 
	 }
	
	 @Test	 
	 public void getTest() throws ParseException{
		 Response response;
	 	 String jsonAsString;
	 	 Object obj, obj1;
	 	 
	 	given().when().
	      get("/api/v1/shipwrecks").
	      then().
	      contentType(ContentType.JSON).statusCode(200);
	 	str.append("---STATUS CODE FOR THE REQUEST IS 200" +"\r\n");
	 	   
	 	response= given().
	 			   when().
	 			   get("/api/v1/shipwrecks").
	 			   then().
	 			   contentType(ContentType.JSON).extract().response();
	 		 jsonAsString=response.asString();
	 		JSONParser parser1 = new JSONParser();
	 		obj1=parser1.parse(jsonAsString);
	 		JSONArray jsonObject1 = (JSONArray) obj1;	
	 		
	        JSONParser parser = new JSONParser();
	
			 try {
				obj = parser.parse(new FileReader("./TestData/source.json"));
				JSONArray jsonObject = (JSONArray) obj;	
				  try{
					  Assert.assertEquals(jsonObject, jsonObject1);
					  str.append("---JSON RESPONSE FILE IS MATCHING WITH TEST SOURCE FILE" +"\r\n");
			     }catch(AssertionError e){
			       //   System.out.println(description + " - failed");
			    	 
			    	 str.append("---JSON RESPONSE FILE IS not MATCHING WITH TEST SOURCE FILE" +"\r\n");
			    	 str.append(e.getLocalizedMessage());
			    	 str.append("\r\n");

			        throw e;
			     }
				
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				 
	 		 System.out.println();
  }	 
	 
	 
	 @Test	 
	 public void getTestWITHSpecificID() throws ParseException{
		 Response response;
		 String jsonAsString;
		 Object obj, obj1;
		 given().
		 when().
	      get("/api/v1/shipwrecks/2").
	      then().
	      contentType(ContentType.JSON).statusCode(200);
			response= given().
		 			   when().
		 			   get("/api/v1/shipwrecks/2").
		 			   then().
		 			   contentType(ContentType.JSON).extract().response();
		 		 jsonAsString=response.asString();
		 		 System.out.println(jsonAsString);
		 		 JSONParser parser = new JSONParser();
		 		JSONParser parser1 = new JSONParser(); 
		 		try {
					obj = parser.parse(new FileReader("./TestData/source.json"));
					JSONArray jsonarray = (JSONArray) obj;	
					jsonarray.get(2);
					
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		// 		JSONArray.toJSONString(arg0)
				
		 
	 }
	 
	 
	 

}
