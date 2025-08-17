package api.stepdefinition;

import api.base.BaseApiTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import org.junit.Assert;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;



import static org.junit.Assert.assertEquals;

public class ErrorHandlingSteps {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private String invalidEndpoint;
    private Response response;
    private String statuscode;

    @Given("I have an invalid endpoint")
    public void iHaveAnInvalidEndpoint() {
        invalidEndpoint=TestUtils.getProperty("InvalidBasepath");
        Log.info("Invalid end point " + invalidEndpoint);
        ExtentManager.getTest().info("Invalid end point " + invalidEndpoint);
    }

    @When("I send GET request with that endpoint")
    public void iSendGETRequestWithThatEndpoint() {
        response= BaseApiTest.getRequest(BASE_URL,invalidEndpoint).get();
        ExtentManager.getTest().info(response.body().asString());
    }

    @Then("the we should receive bad request status code")
    public void theWeShouldReceiveBadRequestStatusCode() {

        statuscode = TestUtils.getProperty("InvalidStatusCode");

        try
        {
            Assert.assertEquals("Status code mismatch",Integer.parseInt(statuscode),response.getStatusCode());

            Log.info("Status code matches "+ response.getStatusCode());
            ExtentManager.getTest().pass("Status code matches "+ response.getStatusCode());
        } catch (Exception e) {
            Log.error("Status code mismatches "+ response.getStatusCode());
            ExtentManager.getTest().pass("Status code mismatches "+ response.getStatusCode());
        }
    }

    @And("response should be null")
    public void responseShouldBeNull() {
        try
        {
            assertEquals("Response is not an empty JSON object",response.getBody().asString().trim(), "{}");
            Log.info("No response found  "+ response.getBody().asString());
            ExtentManager.getTest().pass("No response found  "+ response.getBody().asString());
        } catch (Exception e) {
            Log.error("Response is not an empty JSON object "+ response.getBody().asString());
            ExtentManager.getTest().pass("Response is not an empty JSON object "+ response.getBody().asString());
        }
    }
}
