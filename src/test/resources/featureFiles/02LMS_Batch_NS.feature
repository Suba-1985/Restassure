@Batch_module
Feature: Test LMS apis batch module with BaseUrl and Endpoints
  
 Background: 
 Given User sets Authorization to "No Auth"

  @POST_Positive
  Scenario Outline: validating User able to create a batch with valid endpoint and Payload
    Given User is provided with the BaseUrl and endpoint and nonexisting batch fields in payload
    When User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header
    Examples:
			| sheetname |rownumber|
			| data			| 0   |
 
 @POST_NegativeByExistingBatchName
  Scenario Outline: validating User able to create a batch with valid endpoint and existing BatchName
    Given User is provided with the BaseUrl and endpoint with existing BatchName to create a POST Request
    When User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header
    Examples:
			| sheetname |rownumber|
			| data			| 0   |	
			
	@POST_NegativeByMissingBatchFields
  Scenario Outline: validating User able to create a batch with valid endpoint and missing fields batchName, batchStatus,programID,no of classes
    Given User is provided with the BaseUrl and endpoint with missing batch fields to create a POST Request
    When User send the HTTPsPOST request to server with the payload from "<sheetname>" and <rownumber>
    Then User validates the response with status code, response time, header
    Examples:
			| sheetname |rownumber|
			| data			| 0   |
			
  @GET_PositiveAllBatches
  Scenario: validating User able to retrieve all batches with valid endpoint
    Given User is provided with the BaseUrl and the Endpoints to create a GET request for all batches
    When  User send the HTTPsGET request
    Then  User validates the response with status code, response time, header

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

	@GET_NegativeByInvalidBatchId
  Scenario: validating User able to retrieve batch with Invalid batchID
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request with invalid batchID
    Then  User validates the response with status code, response time, header
    
    @GET_NegativeByInvalidBatchName
  Scenario: validating User able to retrieve batch with Invalid batch name
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request with invalid batch name
    Then  User validates the response with status code, response time, header
    
    @GET_NegativeByInvalidProgramId
  Scenario: validating User able to retrieve batch with Invalid programID
    Given User is provided with the BaseUrl and the Endpoints to create a GET request
    When  User send the HTTPsGET request for batch with invalid programID
    Then  User validates the response with status code, response time, header

 @PUT_PositiveUsingValidBatchID
  Scenario: validating user able to update a batch with valid batchID and Payload
    Given User is provided with the BaseUrl and the Endpoints to update fields with batchID
    When  User send the HTTPsPUT request with valid batchID
    Then  User validates the response with status code, response time, header
    
    @PUT_NegativeUsingInvalidBatchID
  Scenario: validating user able to update a batch with invalid batchID and Payload
    Given User is provided with the BaseUrl and the Endpoints to update batch with invalid batchID
    When  User send the HTTPsPUT request with invalid batchID
    Then  User validates the response with status code, response time, header
    
    @PUT_NegativeUsingvalidbatchIDMissingfields
  Scenario: validating user able to update a batch with valid batchID and missing batchName,programID,batchStatus,NoOfClasses field in payload
    Given User is provided with the BaseUrl and the Endpoints to update batch with missing fields in payload
    When  User send the HTTPsPUT request with valid programID
    Then  User validates the response with status code, response time, header
    
     @DELETE_PositiveBatchWithValidProgramName
  Scenario: validating user able to delete a batch with valid programName 
    Given User is provided with the BaseUrl and the Endpoints to delete a batch with valid programName 
    When User send the HTTPsDELETE request with valid programName
    Then User validates the response with status code, response time, header
    
  @DELETE_NegativeBatchWithInvalidProgramName
  Scenario: validating user able to delete a batch with valid endpoint and invalid programName 
    Given User is provided with the BaseUrl and the Endpoints to delete batch with invalid programName 
    When User send the HTTPsDELETE request with invalid programName
    Then User validates the response with status code, response time, header
  