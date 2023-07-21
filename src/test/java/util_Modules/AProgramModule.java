package util_Modules;
import static io.restassured.RestAssured.given;


import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utilities.Config_Reader;
import utilities.ExcelReader;
public class AProgramModule {
	private String noAuth;
	private String programdescription;
	private String progname;
	private static String progidinvalid,programNamestr;
	private String progstatus;
	public static String programId;
	public static String progid;
	RequestSpecification requestSpecification;
	private static String progNameinvalid;

	public RequestSpecification noAuthentication(String noauth)
	{
		noAuth=noauth;	
		RequestSpecification requestSpecification = RestAssured.given()
			.header("Authorization", noauth).contentType("application/json");
	return requestSpecification;
	}

	public void getDatafromExcel(String sheetname, int rownumber) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		ExcelReader reader = new ExcelReader();
		String data[]=new String[2];
		List<Map<String, String>> testdata;
		try {
			 testdata = reader.getData(Config_Reader.excelpath(), sheetname);
			 programdescription = testdata.get(rownumber).get("programDescription");	
			 progname= testdata.get(rownumber).get("programName");
			 progstatus= testdata.get(rownumber).get("programStatus");
			  progidinvalid = testdata.get(rownumber).get("invalidprogramID");
			  progNameinvalid=testdata.get(rownumber).get("invalidprogramName");
			  
			 
		} catch (InvalidFormatException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
			}
	
	
	public Response postprogram(String postUri)
	{
		JSONObject jsonObject = new JSONObject();
		String s = RandomStringUtils.randomNumeric(3); 
		  programNamestr=progname+s;
		  System.out.println(programNamestr);
		jsonObject.put("programDescription",programdescription);
		jsonObject.put("programName", programNamestr);
		jsonObject.put("programStatus", progstatus);
		String payload = jsonObject.toString();
		Response response = noAuthentication(noAuth).body(payload).post(postUri);		
		programId=response.jsonPath().getString("programId");
	//	System.out.println("input data************"+programdescription+programNamestr+progstatus);
		
		System.out.println(response.jsonPath().prettyPrint());
		
		return response;
	}
	
	public String invalidpostprogram(String postUri)
	{
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("programDescription",programdescription);
		jsonObject.put("programName", progNameinvalid);
		jsonObject.put("programStatus", progstatus);
		//String payload = jsonObject.toString();
		String endpoint=postUri+Config_Reader.deleteprogramByidEndpoint()+progNameinvalid;
		System.out.println(endpoint+"******from invalid id");
		String responseinvalid = progidinvalid;		
		///programId=response.jsonPath().getString("programId");
		//Assert.assertEquals(false, null);
		//System.out.println(responseinvalid.jsonPath().prettyPrint()+ "from invalid");
		return responseinvalid;
	}

	

	public void deletebyprogramid(String progID, String deleteEndpoint) {
		given()	
		.when()
		 .delete(deleteEndpoint+progidinvalid)
		.then() 
		  .statusCode(200)
		//  .statusLine(success)
		  .log().all();
		
		System.out.println(deleteEndpoint+programId +"****************** deletepoint");
		System.out.println("response class****"+Response.class);
	}
	
	public void deletebyprogramName( String deleteEndpoint) {
		given()	
		.when()
		 .delete(deleteEndpoint+progidinvalid)
		.then() 
		  .statusCode(200)
		//  .statusLine(success)
		  .log().all();
		
		System.out.println(deleteEndpoint+programId +"****************** deletepoint");
		System.out.println("response class****"+Response.class);
	}

	public void getAllPrograms(String postURI) {
		 given()
		   .header("Content-Type","application/json")
		 //  .params()
			.when()
			   .get(postURI)
			.then()
			 .statusCode(200)
			// .body("data.id[0]",equalTo(7))
			// .body("data.first_name", hasItems("Michael", "Lindsay"))
			 .log().all();
	 
	}

	public void getAllProgramsbyID(String postURI) {
		
		given()
		   .header("Content-Type","application/json")
		 //  .params()
			.when()
			   .get(postURI)
			.then()
			 .statusCode(200)
			// .body("data.id[0]",equalTo(7))
			// .body("data.first_name", hasItems("Michael", "Lindsay"))
			 .log().all();
	}

	
}
