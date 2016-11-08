package com.barclays.wealth;

import static com.jayway.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

public class TabDelimetedsaving {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// this program is for tab delimetd saving
		
		Properties prop = new Properties();
		InputStream input = null;

		try {

			input = new FileInputStream("./Properties/Validation.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println(prop.getProperty("v1"));
			System.out.println(prop.getProperty("v2"));
			System.out.println(prop.getProperty("v3"));
	        Set<Object> s1=prop.keySet();
	        Iterator<Object> it=s1.iterator();
//	        while(it.hasNext())
//	        {
//	        	System.out.println(it.next());
//	        }
	    	Response response;
			 String jsonAsString;
			 
	      response= given().when().get("http://maps.googleapis.com/maps/api/geocode/json?address=1600 Amphitheatre Parkway, Mountain View, CA&sensor=false").then().contentType(ContentType.JSON).extract().response();
		 
	      jsonAsString=response.asString();
	  	// System.out.println(jsonAsString);
	  	 JsonPath jsonPath = new JsonPath(jsonAsString);
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
	    	   if (max<list[j].size())
	    		   max=list[j].size();
	    	 j++;
	       }
	       
	       System.out.println(max);
	    	   
	    	   
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
