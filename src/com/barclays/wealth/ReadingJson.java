package com.barclays.wealth;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;



public class ReadingJson {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException, IOException, ParseException {
		// TODO Auto-generated method stub
		//JSONParser parser = new JsonPath();
		//awsravi
		JSONParser parser = new JSONParser();
		Object obj = parser.parse(new FileReader("./Properties/sample.json"));
		JSONObject jsonObject = (JSONObject) obj;
		//jsonObject.get(arg0)
		Response response;
	    String jsonAsString=obj.toString();
	    System.out.println(jsonAsString);
	    JsonPath jsonPath = new JsonPath(jsonAsString);
		Properties prop = new Properties();
		InputStream input = null;
	       List<Object> li=new ArrayList<Object>();
	       input = new FileInputStream("./Properties/Validation.properties");

			// load a properties file
			prop.load(input);
			 Set<Object> s1=prop.keySet();
		        Iterator<Object> it=s1.iterator();
	     
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
	       File file = new File("./TestReport/filename3.txt");
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
		

		
	}

}
