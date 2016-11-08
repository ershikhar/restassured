package com.barclays.wealth;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ValidatableResponse;

public class finaltestfile {

	 @BeforeClass
	    public static void setup() {
	       
     RestAssured.baseURI="http://localhost:9999";
     RestAssured.basePath="/api/v1/shipwrecks";
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
	 
	 @Test	 
	 public void ndtTest2() throws IOException{
	 	 Response response;
	 	 String jsonAsString;
	 	 Object obj;
	 	   response= given().when().get("http://maps.googleapis.com/maps/api/geocode/json?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.JSON).extract().response();
	 		 jsonAsString=response.asString();
	 		 writetextresponse(jsonAsString);
	 		// System.out.println(jsonAsString);
	 	//	 JsonPath jsonPath = new JsonPath(jsonAsString);		 
	     // extract().response();
	 	// vr= given().when().get("/maps/api/geocode/json?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.JSON);
	 	// vr.assertThat().
	 	// System.out.println(vr.toString());
	 	// jsonAsString=response.asString();
	 	 //System.out.println(jsonAsString);
     }	
	 
	 public static void writetextresponse(String response) throws IOException
	 {
		 Properties prop = new Properties();
			InputStream input = null;
		 JsonPath jsonPath = new JsonPath(response);
		 try {
			input = new FileInputStream("./Properties/Validation.properties");
			 Set<Object> s1=prop.keySet();
		        Iterator<Object> it=s1.iterator();
		        List<Object> li=new ArrayList<Object>();
			       li.addAll(s1);
			       
			       ArrayList[] list = new ArrayList[li.size()];
			       int j=0;
			       int max=0;
			       for (int i=li.size()-1; i>=0;i--)
			       {
			    	   System.out.println(li.get(i));
			    	  
			    	   list[j] = new ArrayList<String>();
			    	   list[j]=   (ArrayList) jsonPath.getList(prop.getProperty((String) li.get(i)));
			    	   System.out.println("list[j]:"+list[j].size());
			    	   if (max<list[j].size())
			    		   max=list[j].size();
			    	 j++;
			       }
			       StringBuilder str = new StringBuilder();
			       File file = new File("./TestReport/filename2.txt");
			       FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					// if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}
			       System.out.println(max);
			       
			       for(int i1=0; i1<max;i1++)
			       {
			    	   for(int i2=0; i2<li.size();i2++)
				       {
				    	   str.append(list[i2].get(i1)+"\t");
				    	   
				       } 
			    	   str.append("\r\n");
			       }
			    	   
			       bw.write(str.toString());
			       bw.close();
				     
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	 
}
