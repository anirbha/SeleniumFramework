package api.stepdefinition;

import api.base.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.File;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;


public class SchemaValidationSteps {

    private String schemaFile;
    private Response response;
    private String BASE_URL;
    private String endpoint;
    @Given("I have the JSON schema")
    public void iHaveTheJSONSchema() {
        this.schemaFile = TestUtils.getProperty("JsonSchemaPath");
        Log.info("Read the schema file");
        ExtentManager.getTest().info("Read the schema file");
    }

    @When("I send a GET request to users endpoint")
    public void iSendAGETRequestToUsersEndpoint() {
        BASE_URL=TestUtils.getProperty("BASE_URL");
        endpoint=TestUtils.getProperty("UsersBasepath");
        response= BaseApiTest.getRequest(BASE_URL,endpoint).get();
        Log.info(response.body().asString());
        ExtentManager.getTest().info(response.body().asString());
    }

    @Then("the response should match the JSON schema")
    public void theResponseShouldMatchTheJSONSchema() {
        try{

            response.then().assertThat().body(matchesJsonSchema(new File(schemaFile)));
            Log.info("Response matched with the schema");
            ExtentManager.getTest().pass("Response matched with the schema");
        } catch (Exception e) {
            Log.error("Response didn't match with the schema");
            ExtentManager.getTest().fail("Response didn't match with the schema");
        }

    }
}
