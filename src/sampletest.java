import org.junit.BeforeClass;
import org.junit.Test;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.response.Response;


public class sampletest {
	 @BeforeClass
	    public static void setup() {
	       
     RestAssured.baseURI="http://localhost";
     RestAssured.basePath="http://services.groupkt.com";
	}
@Test	 
public void getTest(){
	 Response response;
	 String jsonAsString;

}

}


