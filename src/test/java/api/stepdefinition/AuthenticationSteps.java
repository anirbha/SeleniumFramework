package api.stepdefinition;

import api.Pojo.AuthPojo;
import api.base.BaseApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;


import static junit.framework.Assert.assertEquals;

public class AuthenticationSteps {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private AuthPojo authpojo;
    private String authHeader;
    private Response response;
    private int id;
    private String endpoint;
    private String jsonpath;
    private String jsoncontent;
    JsonNode node;

    ObjectMapper mapper=new ObjectMapper();

    @Given("I set the Authorization header")
    public void iSetTheAuthorizationHeaderTo() {
        authHeader= TestUtils.getProperty("BearerToken");
    }

    @When("I send a POST request")
    public void iSendAPOSTRequestTo() throws IOException {
        jsonpath = TestUtils.getProperty("CreateRecordWithAuth");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        try {
            node = mapper.readTree(jsoncontent);
        } catch (Exception e) {
            Log.error("Error reading JSON content: " + e.getMessage());
        }

        String title = node.get("title").asText();
        String body = node.get("body").asText();


        authpojo =new AuthPojo(title,body);
        endpoint =TestUtils.getProperty("BasepathPost");

        response= BaseApiTest.getRequest(BASE_URL,endpoint).header("Authorization", authHeader).body(authpojo).post();

    }

    @Then("the newly created id should have the same title and body")
    public void theNewlyCreatedIdShouldHaveTheSameTitleAndBody() {
        id =response.jsonPath().getInt("id");
        authpojo.setId(id);

        try
        {
            assertEquals("The title matches as expected", authpojo.getTitle(),response.jsonPath().getString("title"));
            assertEquals("The email matches as expected", authpojo.getBody(),response.jsonPath().getString("body"));
            ExtentManager.getTest().info(response.asString());
            ExtentManager.getTest().info("The newly created id is "+ id);
            ExtentManager.getTest().pass("The title "+ response.jsonPath().getString("name") + " the body "+response.jsonPath().getString("email") +" are matching as expected");
            Log.info(response.asString());
            Log.info("The title "+ response.jsonPath().getString("title") + " the body "+response.jsonPath().getString("body") +" are matching as expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Title and body are not matching");
            Log.error("The title and body are not matching");
        }
    }

    @Then("the status code should be {int}")
    public void theStatusCodeShouldBe(int code) {
        try{
            Log.info("Status code" + response.getStatusCode());
            assertEquals("Status code matches",code, response.getStatusCode());
            ExtentManager.getTest().pass("Status code matches as expected");
            Log.info("Status code matches as expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Status code does not match as expected");
            Log.info("Status code does not match as expected");
        }
    }
}
