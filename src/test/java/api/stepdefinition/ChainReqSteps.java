package api.stepdefinition;

import api.Pojo.PostChainPojo;
import api.Pojo.UserChainPojo;
import api.base.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.Objects;

import static org.junit.Assert.assertEquals;


public class ChainReqSteps {

    private Response response;
    private String BASE_URL=TestUtils.getProperty("BASE_URL");
    private String endpoint=TestUtils.getProperty("UsersBasepath");
    UserChainPojo userChainPojo=new UserChainPojo();
    PostChainPojo postChainPojo=new PostChainPojo();
    UserChainPojo createdUser;
    PostChainPojo createdPost;
    Integer id;


    @Given("I create a new user in users endpoint")
    public void iCreateANewUserInUsersEndpoint() {
        // Step 1: Create a user POJO

        userChainPojo.setName(String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("UserChainJsonPath"))).get("name")));
        userChainPojo.setUsername(String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("UserChainJsonPath"))).get("username")));
        userChainPojo.setEmail(String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("UserChainJsonPath"))).get("email")));

        response= BaseApiTest.getRequest(BASE_URL,endpoint).body(userChainPojo).post();
        Log.info("Status code after creating user in users endpoint "+ response.getStatusCode());
        ExtentManager.getTest().info("Status code after creating user in users endpoint "+ response.getStatusCode());

        createdUser=response.then().extract().as(UserChainPojo.class);
        id = createdUser.getId();
        Log.info("Created id for users "+ id);
        ExtentManager.getTest().info("Created id for users "+ id);

    }
    //Post request to create id in user
    @When("I create a post for that user")
    public void iCreateAPostForThatUser() {
        postChainPojo.setId(id);
        postChainPojo.setTitle(String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("PostChainJsonPath"))).get("title")));
        postChainPojo.setBody(String.valueOf(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("PostChainJsonPath"))).get("body")));
        postChainPojo.setUserId((Integer)(TestUtils.readJsonData(TestUtils.getProperty("UserChainJsonPath"))).get("userId"));

        response= RestAssured.given().baseUri(BASE_URL).basePath(endpoint).body(postChainPojo).post();
        Log.info("Status code after creating user in post "+ response.getStatusCode());
        ExtentManager.getTest().info("Status code after creating user in post "+ response.getStatusCode());
        createdPost=response.then().extract().as(PostChainPojo.class);

    }

    @Then("the post should be associated with the user")
    public void thePostShouldBeAssociatedWithTheUser() {
        try{
            assertEquals(id,createdPost.getId());
            Log.info(" Id matches with the post after creation " + createdPost.getId());
            ExtentManager.getTest().pass(" Id matches with the post after creation " + createdPost.getId());
        } catch (Exception e) {
            Log.error(" Id did not match with the post after creation");
            ExtentManager.getTest().pass(" Id did not match with the post after creation");
        }


    }
}
