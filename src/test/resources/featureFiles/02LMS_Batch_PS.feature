@Batch_module
Feature: Test LMS apis batch module with BaseUrl and Endpoints
  
 Background: 
 Given User sets Authorization to "No Auth"

  @POST_Positive
  Scenario Outline: validating User able to create a batch with valid endpoint and Payload
    Given User is provided with the BaseUrl and endpoint and nonexisting batch fields in payload
    When User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code "201" from post
    Examples:
			| sheetname |rownumber|
			| data			| 0   |
 

			
	
			
  @GET_PositiveAllBatches
  Scenario: validating User able to retrieve all batches with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET request for all batches
    When  User send the HTTPsGET request
    Then  User validates the response with Status code "200" and validate schema for all the program

 @GET_PositiveByValidBatchID
  Scenario: validating User able to retrieve batch with valid batchID
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request with valid batchID
    Then  User validates the response with status code, response time, header
    
    @GET_PositiveByValidBatchName
  Scenario: validating User able to retrieve batch with valid batch name
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request with valid batch name
    Then  User validates the response with status code, response time, header
    
    @GET_PositiveByValidProgramID
  Scenario: validating User able to retrieve batch with valid programID
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request for batch with valid programID
    Then  User validates the response with status code, response time, header

	
   
    
   
 @PUT_PositiveUsingValidBatchID
  Scenario: validating user able to update a batch with valid batchID and Payload
    Given User is provided with the BaseUrl and the Endpoints to update fields with batchID
    When  User send the HTTPsPUT request with valid batchID
    Then  User validates the response with status code, response time, header
    
    

    
    
     @DELETE_PositiveBatchWithValidProgramName
  Scenario: validating user able to delete a batch with valid programName 
    Given User is provided with the BaseUrl and the Endpoints to delete a batch with valid programName 
    When User send the HTTPsDELETE request with valid programName
    Then User validates the response with status code, response time, header
    
 

  