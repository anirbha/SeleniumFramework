package api.stepdefinition;

import api.Pojo.QueryParamPojo;
import api.base.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.List;
import java.util.Objects;


import static junit.framework.Assert.assertEquals;

public class QueryParamSteps {
    private Response response;
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private final String endpoint=TestUtils.getProperty("BasePathQueryParam");

    private QueryParamPojo queryParamPojo;
    private String queryparampath = TestUtils.getProperty("QueryParamJsonPath");

    String id=String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(queryparampath).get("id")));
    String userId=String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(queryparampath).get("userId")));

    List<Integer> ids;
    List<Integer> userIds;
    List<String> title;
    List<String> body;

    @Given("I send a GET request with id query parameter")
    public void iSendAGETRequestToWithQueryParameter() {

        response= BaseApiTest.getRequest(BASE_URL,endpoint).queryParam("id",id).when().get();
        Log.info(response.body().asString());
        ExtentManager.getTest().info(response.body().asString());
    }

    @Then("the response status code should be {int}")
    public void theResponseStatusCodeShouldBe(int code) {
      try{
          Log.info("Status code" + response.getStatusCode());
          assertEquals("Status code matches",code, response.getStatusCode());
          ExtentManager.getTest().pass("Status code matches "+response.getStatusCode());
      } catch (Exception e) {
          Log.error("Status code doesn't match");
          ExtentManager.getTest().fail("Status code mismatches "+response.getStatusCode());
      }


    }

    @Then("the response should contain the query parameter id")
    public void theResponseShouldContainTheQueryParameter() {
        ids =response.jsonPath().getList("id");
        userIds= response.jsonPath().getList("userId", Integer.class);
        title=response.jsonPath().getList("title");
        body=response.jsonPath().getList("body");
        queryParamPojo=new QueryParamPojo();
        queryParamPojo.setId(ids.get(0));
        int userid=userIds.get(0);
        queryParamPojo.setUserId(userid);
        queryParamPojo.setTitle(title.get(0));
        queryParamPojo.setTitle(body.get(0));

        try{
            assertEquals("id matches",Integer.parseInt(id) , queryParamPojo.getId());
            Log.info("id matches as expected " + queryParamPojo.getId());
            ExtentManager.getTest().pass("id matches as expected " + queryParamPojo.getId() +
                    "userId " + queryParamPojo.getUserId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        } catch (Exception e) {

            Log.error("id didn't match" + queryParamPojo.getId());
            ExtentManager.getTest().fail("id does not match as expected " + queryParamPojo.getId() +
                    "userId " + queryParamPojo.getUserId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        }

    }

    @Given("I send a GET request with userId query parameter")
    public void iSendAGETRequestWithUserIdQueryParameter() {
        response= RestAssured.given().baseUri(BASE_URL).basePath(endpoint)
                .queryParam("userId",userId).when().get();
        Log.info(response.body().asString());
        ExtentManager.getTest().info(response.body().asString());
    }

    @Then("the response should contain the query parameter userId")
    public void theResponseShouldContainTheQueryParameterUserId() {
        ids =response.jsonPath().getList("id");
        userIds= response.jsonPath().getList("userId", Integer.class);
        title=response.jsonPath().getList("title");
        body=response.jsonPath().getList("body");
        queryParamPojo=new QueryParamPojo();
        queryParamPojo.setId(ids.get(0));
        int userid=userIds.get(0);
        queryParamPojo.setUserId(userid);
        queryParamPojo.setTitle(title.get(0));
        queryParamPojo.setTitle(body.get(0));

        try{
            assertEquals("userid matches ",Integer.parseInt(userId) , queryParamPojo.getUserId());
            Log.info("userId matches as expected " + queryParamPojo.getUserId());
            ExtentManager.getTest().pass("userid matches as expected " + queryParamPojo.getUserId() +
                    "id " + queryParamPojo.getId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        } catch (Exception e) {

            Log.error("userId didn't match" + queryParamPojo.getUserId());
            ExtentManager.getTest().fail("userId does not match as expected " + queryParamPojo.getUserId() +
                    "id " + queryParamPojo.getId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        }
    }

    @Given("I send a GET request with id and userId query parameter")
    public void iSendAGETRequestWithIdAndUserIdQueryParameter() {
        response= RestAssured.given().baseUri(BASE_URL).basePath(endpoint)
                .queryParam("id", id)
                .queryParam("userId",userId).when().get();
        Log.info(response.body().asString());
        ExtentManager.getTest().info(response.body().asString());
        
    }

    @Then("the response should contain the query parameter id and userId")
    public void theResponseShouldContainTheQueryParameterIdAndUserId() {
        ids =response.jsonPath().getList("id");
        userIds= response.jsonPath().getList("userId", Integer.class);
        title=response.jsonPath().getList("title");
        body=response.jsonPath().getList("body");
        queryParamPojo=new QueryParamPojo();
        queryParamPojo.setId(ids.get(0));
        int userid=userIds.get(0);
        queryParamPojo.setUserId(userid);
        queryParamPojo.setTitle(title.get(0));
        queryParamPojo.setTitle(body.get(0));

        try{
            assertEquals("userid matches ",Integer.parseInt(userId) , queryParamPojo.getUserId());
            Log.info("userId matches as expected " + queryParamPojo.getUserId());
            ExtentManager.getTest().pass("userid matches as expected " + queryParamPojo.getUserId() +
                    "id " + queryParamPojo.getId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        } catch (Exception e) {

            Log.error("userId didn't match" + queryParamPojo.getUserId());
            ExtentManager.getTest().fail("userId does not match as expected " + queryParamPojo.getUserId() +
                    "id " + queryParamPojo.getId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        }

        try{
            assertEquals("id matches",Integer.parseInt(id) , queryParamPojo.getId());
            Log.info("id matches as expected " + queryParamPojo.getId());
            ExtentManager.getTest().pass("id matches as expected " + queryParamPojo.getId() +
                    "userId " + queryParamPojo.getUserId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        } catch (Exception e) {

            Log.error("id didn't match" + queryParamPojo.getId());
            ExtentManager.getTest().fail("id does not match as expected " + queryParamPojo.getId() +
                    "userId " + queryParamPojo.getUserId() + " title " + queryParamPojo.getTitle() + " Body " + queryParamPojo.getBody());

        }

    }
}
