package stepDefinition;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.Properties;

import org.apache.poi.POIDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.hamcrest.Matchers;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import util_Modules.AProgramModule;
import utilities.Config_Reader;
import utilities.LoggerLoad;
import utilities.PageUtils;

public class AProgram_module {
	private static String postURI,programinvalidID;
	 public  static String programID;
    private static String PutEndpoint;
    private static String DeleteEndpoint;
    private static String getEndpoint;
    private static String excelsheetname;
    private Response response;
    private static String responseinvalid;
    Config_Reader configreader=new Config_Reader();
    static Properties prop;
    private PageUtils pageUtil=new PageUtils();
    private static AProgramModule program=new AProgramModule();
    
    @Given("User sets Authoization to {string}")
    public void user_sets_authoization_to(String string) {
    	program.noAuthentication(string);
    }

	
	@Given("User is provided with the BaseUrl and endpoint and nonexisting fields in payload")
	public void user_is_provided_with_the_base_url_and_endpoint_and_nonexisting_fields_in_payload() throws IOException {
	  prop=configreader.init_prop();	 
	  postURI=configreader.baseUrl()+configreader.postProgramEndpoint();
	  System.out.println(postURI);
	  LoggerLoad.info("**************getting the end point for post**************" + postURI);
	  
	}

	@When("User send the HTTPsPOST request to server with the payload from {string} and {int}")
	public void user_send_the_htt_ps_post_request_to_server_with_the_payload_from_and_rownumber(String SheetName, int rowno) throws InvalidFormatException, IOException {

	    	program.getDatafromExcel(SheetName, rowno);
		    response= program.postprogram(postURI);
	        programID=response.jsonPath().getString("programId");
	        String progid=programID;
	        System.out.println(progid);
	        System.out.println("The status received: " + response.statusLine());
	        System.out.println("The status received: " + response.statusCode());
	        System.out.println("The status received: " + response.asString());
	        System.out.println("The status received: " + response.contentType());
	        LoggerLoad.info("****************Program is created valid data*********************");
			
			
	}

	@Then("User validates the response with status code {int}, response time, header")
	public void user_validates_the_response_with_status_code_response_time_header(Integer statuscode) {
		Assert.assertEquals(response.getStatusCode(),statuscode ); 
	        
	}
	
//	@When("User send the HTTPsPOST request to server with the payload from {string} and {int} with existing Programname")
//	public void user_send_the_htt_ps_post_request_to_server_with_the_payload_from_and_with_existing_programname(String SheetName, Integer rowno) throws InvalidFormatException, IOException {
////		program.getDatafromExcel(SheetName, rowno);
////	    responseinvalid= program.invalidpostprogram(postURI);
////       // programinvalidID=responseinvalid.jsonPath().getString("programId");
////       
////        System.out.println(responseinvalid);
////        LoggerLoad.info("****************Program creation with invalid data*************");
//	}

	@Then("User validates the response with status code, response time, header")
	public void user_validates_the_response_with_status_code_response_time_header() {
	//	Assert.assertEquals(response.getStatusCode(),statuscode ); 
	}

	@Given("User is provided with the BaseUrl and endpoint with missing ProgramName to create a POST Request with missing programname")
	public void user_is_provided_with_the_base_url_and_endpoint_with_missing_program_name_to_create_a_post_request_with_missing_programname() {
	    
	}
	@Given("User is provided with the BaseUrl and endpoint with existing ProgramName to create a POST Request")
	public void user_is_provided_with_the_base_url_and_endpoint_with_existing_program_name_to_create_a_post_request() {
		
	}

	@When("User send the HTTPsPOST request to server with the payload from {string} and {int} with existing Programname")
	public void user_send_the_htt_ps_post_request_to_server_with_the_payload_from_and_with_existing_programname(String string, Integer int1) {
	    
	}
	
	@Given("User is provided with the BaseUrl and endpoint with missing ProgramName to create a POST Request")
	public void user_is_provided_with_the_base_url_and_endpoint_with_missing_program_name_to_create_a_post_request() {
	   
	}

	@Given("User is provided with the BaseUrl and the Endpoints to create a GET request")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_create_a_get_request() {
		postURI=configreader.baseUrl()+configreader.getAllEndpoint();
		  System.out.println(postURI);
		  LoggerLoad.info("**************retrieve all the programs **************" + postURI);
	}

	@When("User send the HTTPsGET request")
	public void user_send_the_htt_ps_get_request() {
	   program.getAllPrograms(postURI);
	}

	@When("User send the HTTPsGET request with valid programID")
	public void user_send_the_htt_ps_get_request_with_valid_program_id() {
		postURI=configreader.baseUrl()+configreader.getOneProgramIdEndpoint()+10739;
	   program.getAllProgramsbyID(postURI);
	   LoggerLoad.info("*************GET PROGRAMS by valid programid**********"+postURI);
	}

	@When("User send the HTTPsGET request with invalid programID")
	public void user_send_the_htt_ps_get_request_with_invalid_program_id() {
		   postURI=configreader.baseUrl()+configreader.getOneProgramIdEndpoint()+10730;
		   program.getAllProgramsbyID(postURI);
		   LoggerLoad.info("*************GET  PROGRAM BY invalid ID**********"+postURI);
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with programID")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_program_id() {
	   
	}

	@When("User send the HTTPsPUT request with valid programID")
	public void user_send_the_htt_ps_put_request_with_valid_program_id() {
	   postURI=configreader.baseUrl()+configreader.putProgramByProgramIdEndpoint();
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with invalid programID")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_invalid_program_id() {
	    
	}

	@When("User send the HTTPsPUT request with invalid programID")
	public void user_send_the_htt_ps_put_request_with_invalid_program_id() {
	    
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with missing programName in payload")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_missing_program_name_in_payload() {
	   
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with programName")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_program_name() {
	   
	}

	@When("User send the HTTPsPUT request with valid programName")
	public void user_send_the_htt_ps_put_request_with_valid_program_name() {
	   
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with invalid programName")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_invalid_program_name() {
	   
	}

	@When("User send the HTTPsPUT request with invalid programName")
	public void user_send_the_htt_ps_put_request_with_invalid_program_name() {
	    
	}

	@Given("User is provided with the BaseUrl and the Endpoints to update status with missing program status")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_update_status_with_missing_program_status() {
	    
	}

	@When("User send the HTTPsPUT request with missing program status")
	public void user_send_the_htt_ps_put_request_with_missing_program_status() {
	    
	}

	@Given("User is provided with the BaseUrl and the Endpoints to delete a program with valid programName")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_delete_a_program_with_valid_program_name() {
	    postURI=configreader.baseUrl()+configreader.deleteprogramBynameEndpoint()+"JUL23-RestingNinjas-SDET-002018";
	}

	@When("User send the HTTPsDELETE request with valid programName")
	public void user_send_the_htt_ps_delete_request_with_valid_program_name() {
		//System.out.println(deleteEndpoint+ programID + "****************** deletepoint");
		 given()	
			.when()
			 .delete(postURI)
			.then() 
			 .statusCode(204)
			// .statusLine("deleted successfully")
			  .log().all();			
	}

	@Given("User is provided with the BaseUrl and the Endpoints to delete a program with invalid programName")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_delete_a_program_with_invalid_program_name() {
	   postURI=configreader.baseUrl()+configreader.deleteprogramBynameEndpoint()+"JUL23-RestingNinjas-SDET-0020182";
	}

	@When("User send the HTTPsDELETE request with invalid programName")
	public void user_send_the_htt_ps_delete_request_with_invalid_program_name() {
		given()	
		.when()
		 .delete(postURI)
		.then() 
	       //  .statusLine("success")
		  .log().all();			
	}

	@Given("User is provided with the BaseUrl and the Endpoints to delete a program with valid programId")
	public void user_is_provided_with_the_base_url_and_the_endpoints_to_delete_a_program_with_valid_program_id() {
	   
	}
  
	@When("User send the HTTPsDELETE request with valid programId")
	public void user_send_the_htt_ps_delete_request_with_valid_program_id() {
	 String deleteEndpoint=configreader.baseUrl()+configreader.deleteprogramByidEndpoint();
//	 LoggerLoad.info("**************getting the end point for post**************" + deleteEndpoint);
//	 System.out.println(deleteEndpoint+ programID + "****************** deletepoint");
//
//	  program.deletebyprogramid(deleteEndpoint, programID);
	 System.out.println(deleteEndpoint+ programID + "****************** deletepoint");
	 given()	
		.when()
		 .delete(deleteEndpoint+programID)
		.then() 
		//  .statusCode(204)
		//  .statusLine(success)
		  .log().all();
		
		System.out.println(deleteEndpoint+ programID + "****************** deletepoint");
		System.out.println("response class****"+Response.class);
}
	 
	@When("User send the HTTPsDELETE request with invalid programId")
	public void user_send_the_htt_ps_delete_request_with_invalid_program_id() {
	   
	}
}
