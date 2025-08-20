package api.stepdefinition;

import api.Pojo.HeaderCookiePojo;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class HeaderCookieSteps {

    private RequestSpecification request;
    private Response response;

    private Map<String, String> headers = new HashMap<>();
    private Map<String, String> cookies = new HashMap<>();

    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private final String endpoint=TestUtils.getProperty("BasePathHeaderCookie");
    String url=BASE_URL+endpoint;

    @Given("I set a custom header with value")
    public void iSetACustomHeaderWithValue() {
       String header= TestUtils.getProperty("Custom-Header");
       headers.put("Custom-Header",header);
    }

    @And("I set a cookie with value")
    public void iSetACookieWithValue() {
        String cookie=TestUtils.getProperty("session_id");
        cookies.put("session_id",cookie);
    }

    @When("I send a GET request to")
    public void iSendAGETRequestTo() {
        request = RestAssured.given().headers(headers).cookies(cookies);
        response = request.when().get(url);
        
    }

    @Then("the response status code should be {int} for header cookie")
    public void theResponseStatusCodeShouldBeForHeaderCookie(int code) {
        try{
            Log.info("Status code" + response.getStatusCode());
            assertEquals("Status code matches",code, response.getStatusCode());
            ExtentManager.getTest().pass("Status code matches "+response.getStatusCode());
        } catch (Exception e) {
            Log.error("Status code doesn't match");
            ExtentManager.getTest().fail("Status code mismatches "+response.getStatusCode());
        }
    }

    @Then("the response should contain at least one comment")
    public void theResponseShouldContainAtLeastOneComment() {
        List<HeaderCookiePojo> headerCookiePojos = response.jsonPath().getList("", HeaderCookiePojo.class);
        assertNotNull(headerCookiePojos);
        assertFalse(headerCookiePojos.isEmpty());
    }

    @Then("the response header {string} should be {string}")
    public void theResponseHeaderShouldBe(String headername, String expectedValue) {
        String actualValue = response.getHeader(headername);
        try{
            Log.info("Header value" + actualValue);
            assertEquals("Header Value matches",expectedValue,actualValue);
            ExtentManager.getTest().pass("Header Value matches"+actualValue);
        } catch (Exception e) {
            Log.error("Header Value doesn't match");
            ExtentManager.getTest().fail("Header Value doesn't match "+actualValue);
        }
    }
}
