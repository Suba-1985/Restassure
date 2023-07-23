package stepDefinition;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

import org.json.simple.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import util_Modules.AProgramModule;
import util_Modules.BBatchModule;
import utilities.Config_Reader;
import utilities.ExcelReader;
import utilities.LoggerLoad;
import utilities.PageUtils;

public class BBatch_module_SD {
	private static Config_Reader configreader=new Config_Reader();
	 static Properties prop;
	 private PageUtils pageUtil=new PageUtils();	 
	   // private static BBatchModule batch=new BBatchModule();
	    private static AProgramModule program=new AProgramModule();
	   public static ResponseBody body;
	    public static String batchprogName ,postprogramName;	
	    private static RequestSpecification request;
	  Response response;
	    private static String postURI,programdescription;
		private static int postprogramID,batchID;
		public  static String programID,programName;	   
	    private static String currentTime=PageUtils.getcurrentDateTime();
	    private static String lastModTime=PageUtils.getcurrentDateTime();   
	
	   
	    
	    @Given("User is provided with the BaseUrl and endpoint")
		public void user_is_provided_with_the_base_url_and_endpoint_and_nonexisting_fields_in_payload() throws IOException {
		  prop=Config_Reader.init_prop();
		  request = RestAssured.given();
		  postURI=configreader.baseUrl()+Config_Reader.postProgramEndpoint();
		  System.out.println(postURI);
		  LoggerLoad.info("**************getting the baseurl and endpoint for post**************" + postURI);  
		}

		@SuppressWarnings("unchecked")
		@When("User send the POST request to server with the payload from {string} and {int}")
		public void user_send_the_HTTPsPOST_post_request_to_server_with_the_payload_from_and_rownumber(String SheetName, int rowno) throws InvalidFormatException, IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
			
			ExcelReader reader = new ExcelReader();
			
			List<Map<String, String>> testdata;
	    	 testdata = reader.getData(Config_Reader.excelpath(), SheetName);
			String programdescription = testdata.get(rowno).get("programDescription");	
			String progname= testdata.get(rowno).get("programName");
			String progstatus= testdata.get(rowno).get("programStatus");	
			
			JSONObject jsonObject = new JSONObject();
			String s = RandomStringUtils.randomNumeric(3); 
			String programNamestr=progname+s;		
			jsonObject.put("programDescription",programdescription);
			jsonObject.put("programName", programNamestr);
			jsonObject.put("programStatus", progstatus);				 
			response=request.contentType(ContentType.JSON).body(jsonObject.toJSONString()).when().post(postURI).then().log().all().extract().response();
			postprogramName=response.path("programName");//this gives the created programid and name
			postprogramID=response.path("programId");	
			System.out.println(response.asString());
		    LoggerLoad.info("****************Program is created with valid data*********************");
			
		}

		@Then("User validates the response with status code {string} from batchpost")
		public void user_validates_the_response_with_status_code_response_time_header( String statuscode) {
			final int actualstatuscode = response.getStatusCode();

			if (actualstatuscode == 201) {
			  response.then().statusCode(Integer.parseInt(statuscode));
			  LoggerLoad.info("Post Request Successful");			
			  Assert.assertEquals(statuscode, "201");			
				 System.out.println("created program Successfully " + actualstatuscode);
				Assert.assertTrue(true);
			} else {
				LoggerLoad.error("Not Successful: 400");
				 System.out.println("Not Successful" + actualstatuscode);
				Assert.assertFalse(false);		}		
			ValidatableResponse  v=response.then();
			v.time(Matchers.lessThan(1000L));		
			//header validation
			response.then().assertThat().header("connection","keep-alive");
			response.then().assertThat().header("Content-Type" , "application/json");
			LoggerLoad.info("*********Header validation for post request**********");	
			response.then().assertThat().statusLine(response.statusLine());
			LoggerLoad.info("statusline");
			
			//to validate response body
			 body = response.getBody();
			// To check for sub string presence get the Response body as a String.
			// Do a String.contains
			String bodyAsString = body.asString();
			System.out.println("assertion odne");
			//Assert.assertEquals(bodyAsString.contains("Selenium") /*Expected value*/, true /*Actual Value*/, "Response body contains Hyderabad");
			
		}
		
	    
	@Given("User is provided with the BaseUrl and endpoint for batch")
	public void user_creates_post_request_for_the_batch_lms_api_endpoint() throws IOException {
		  prop=Config_Reader.init_prop();	 
		  postURI=configreader.baseUrl()+Config_Reader.createbatchsaveandGetBatchesAllputBatchUpdatebyBatchIdandDeleteBatch();
		  System.out.println(postURI);
		  System.out.println(postprogramName + postprogramID );
		  LoggerLoad.info("**************getting the end point for post**************" + postURI);
	}

	@SuppressWarnings("unchecked")
	@When("User sends HTTPS Request for batch and  request Body with mandatory , additional using {string} and {int}")
	public void user_sends_https_request_for_batch_and_request_body_with_mandatory_additional_using_and(String sheetname, Integer rowno) throws InvalidFormatException, IOException {
			ExcelReader reader = new ExcelReader();
		System.out.println("test "+AProgram_module_SD.programID);
			List<Map<String, String>> testdata;
			testdata = reader.getData(Config_Reader.excelpath(), sheetname);
			  String batchdescription = testdata.get(rowno).get("batchDescription");	
			  String batchname=testdata.get(rowno).get("batchName");
			  String batchclassno=testdata.get(rowno).get("batchNoOfClasses");
			  String batchstatus=testdata.get(rowno).get("batchStatus");
			  String s = RandomStringUtils.randomNumeric(3); 
			  String batch=batchname+s;
			  int programid=postprogramID;
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("batchDescription",batchdescription);
					jsonObject.put("batchName", batch);
					jsonObject.put("batchNoOfClasses", batchclassno);
					jsonObject.put("batchStatus", batchstatus);
					jsonObject.put("programId",programid );
		
		
		response=request.body(jsonObject.toJSONString()).when().post(postURI).then().log().all().extract().response();
		postprogramName=response.path("programName");//this gives the created programid and name
		postprogramID=response.path("programId");
		batchID=response.path("batchId");
		batchname=response.path("batchName");
	    LoggerLoad.info("****************Program is created valid data*********************");	
			
	}
			

	@Then("User receives {int} Created Status with response body.")
	public void user_receives_created_status_with_response_body(Integer int1) {
	    Assert.assertEquals(response.statusCode(), int1);
	}
	@Given("User is provided with the BaseUrl and the Endpoints to create a GET request for all batches")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request_for_all_batches() {
	    
	}

	@When("User send the HTTPsGET request with valid batchID")
	public void user_send_the_htt_ps_get_request_with_valid_batch_id() {
	    
	}

	@Then("User validates the response with status code, response time, header")
	public void user_validates_the_response_with_status_code_response_time_header() {
	   
	}

	@When("User send the HTTPsGET request with valid batch name")
	public void user_send_the_htt_ps_get_request_with_valid_batch_name() {
	    
	}

	@When("User send the HTTPsGET request for batch with valid programID")
	public void user_send_the_htt_ps_get_request_for_batch_with_valid_program_id() {
	    
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update fields with batchID")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_fields_with_batch_id() {
	    
	}

	@When("User send the HTTPsPUT request with valid batchID")
	public void user_send_the_htt_ps_put_request_with_valid_batch_id() throws IOException {
	  
	}

	@SuppressWarnings("static-access")
	@Given("User is provided with the BaseUrl and the Endpoints to delete a batch with valid BatchId")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_delete_a_batch_with_valid_batch_id() throws IOException {
	
		postURI=configreader.baseUrl()+configreader.createbatchsaveandGetBatchesAllputBatchUpdatebyBatchIdandDeleteBatch()+batchID; 
		System.out.println(postURI);
	}
	

	@When("User send the HTTPsDELETE request with valid batchid")
	public void user_send_the_htt_ps_delete_request_with_valid_batchid() {
		String res=when().delete(postURI).statusLine();
	    System.out.println("delete :" + res);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}



