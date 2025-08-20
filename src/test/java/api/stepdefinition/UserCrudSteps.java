package api.stepdefinition;


import api.Pojo.CrudPojo;
import api.base.BaseApiTest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import io.restassured.response.Response;

import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


import static junit.framework.Assert.assertEquals;



public class UserCrudSteps {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private String endpoint;
    private CrudPojo userpojo;
    private int id;
    private Response response;
    private String jsonpath;
    private String jsoncontent;

    ObjectMapper mapper = new ObjectMapper();
    JsonNode node;


    @Given("I create a new user")
    public void iCreateANewUserWithNameAndEmail() throws IOException {

        jsonpath = TestUtils.getProperty("CreateRecordJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);
        // Access fields dynamically
        String name = node.get("name").asText();
        String email= node.get("email").asText();

        userpojo =new CrudPojo(name,email);
        endpoint=TestUtils.getProperty("UsersBasepath");
        response= BaseApiTest.getRequest(BASE_URL,endpoint)
                .body(userpojo).post();
        ExtentManager.getTest().info("Initiated post method");
        Log.info("Initiated post method");
    }
    @Then("the status code should be {string}")
    public void theStatusCodeShouldBe(String code) {
       try{
           Log.info("Status code" + response.getStatusCode());
           assertEquals("Status code matches",Integer.parseInt(code), response.getStatusCode());
           ExtentManager.getTest().pass("Status code matches as expected");
           Log.info("Status code matches as expected");
       } catch (Exception e) {
           ExtentManager.getTest().fail("Status code does not match as expected");
           Log.info("Status code does not match as expected");
       }

    }

    @Then("the newly created id should have the same name and email")
    public void theNewlyCreatedIdShouldBeTheSameNameAndEmail() {
        id =response.jsonPath().getInt("id");
        userpojo.setId(id);
        try
        {
            assertEquals("The name matches as expected", userpojo.getName(),response.jsonPath().getString("name"));
            assertEquals("The email matches as expected", userpojo.getEmail(),response.jsonPath().getString("email"));
            ExtentManager.getTest().info(response.asString());
            ExtentManager.getTest().info("The newly created id is "+ userpojo.getId());
            ExtentManager.getTest().pass("The name "+ response.jsonPath().getString("name") + "the email "+response.jsonPath().getString("email") +"is matching as expected");
            Log.info(response.asString());
            Log.info("The name "+ response.jsonPath().getString("name") + " the email "+response.jsonPath().getString("email") +"is matching as expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Name and email id is not matching");
            Log.error("The name and email id is not matching");
        }

    }

/*--------------------------------------------------------------------------------------------------------------------*/

    @Given("I retrieve the user by ID")
    public void iRetrieveTheUserByID() throws IOException {

        jsonpath = TestUtils.getProperty("GetRecordJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);

        // Access fields dynamically
        id = node.get("id").asInt();

        // Assuming the ID is provided in the JSON file at GetRecordJsonPath
        endpoint=TestUtils.getProperty("UsersBasepath")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint).get();
        userpojo =new CrudPojo();
        userpojo.setName(response.jsonPath().getString("name"));
        userpojo.setEmail(response.jsonPath().getString("email"));

        ExtentManager.getTest().info("Initiated get method");
        Log.info("Initiated get method");


    }

    @Then("the user name and email should be same with the expected one")
    public void theUserNameShouldBeSameWithTheExpectedOne() {
        try
        {
            assertEquals("The name matches as expected", userpojo.getName(),response.jsonPath().getString("name"));
            assertEquals("The email matches as expected", userpojo.getEmail(),response.jsonPath().getString("email"));
            ExtentManager.getTest().info(response.asString());
            ExtentManager.getTest().pass("The name "+ response.jsonPath().getString("name") + "the email "+response.jsonPath().getString("email") +"is matching as expected");
            Log.info("The name "+ response.jsonPath().getString("name") + " the email "+response.jsonPath().getString("email") +"is matching as expected");
            Log.info(response.asString());
        } catch (Exception e) {
            ExtentManager.getTest().fail("Name and email id is not matching");
            Log.error("The name and email id is not matching");
        }
    }
/*----------------------------------------------------------------------------------------------------------------*/

    @Given("I update the user name and email")
    public void iUpdateTheUserName() throws IOException {

        jsonpath=TestUtils.getProperty("UpdateRecordJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);
        // Access fields dynamically
        String name = node.get("name").asText();
        String email= node.get("email").asText();
        int id= node.get("id").asInt();

        userpojo =new CrudPojo(name,email);

        userpojo.setId(id);

        endpoint=TestUtils.getProperty("UsersBasepath")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint).body(userpojo).put();

        ExtentManager.getTest().info("Initiated put method");
        Log.info("Initiated put method");

    }

    /*-------------------------------------------------------------------------------------------------------------*/

    @Given("I delete the user")
    public void iDeleteTheUser() throws IOException {
        jsonpath=TestUtils.getProperty("DeleteRecordJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node= mapper.readTree(jsoncontent);
        id= node.get("id").asInt();

        endpoint=TestUtils.getProperty("UsersBasepath")+id;

        response=BaseApiTest.getRequest(BASE_URL,endpoint)
                .delete();

    }

    @Then("the user data should not be found")
    public void theUserDataShouldNotBeFound() {
        try
        {
            Log.info(response.getBody().asString());
            ExtentManager.getTest().info(response.asString());
            assertEquals("The body is null","{}",response.getBody().asString());
            ExtentManager.getTest().pass("The response body is empty");
            Log.info("The response body is empty");
        } catch (Exception e) {
            ExtentManager.getTest().fail("The response body is not empty");
            Log.error("The response body is not empty");

        }
    }
}



