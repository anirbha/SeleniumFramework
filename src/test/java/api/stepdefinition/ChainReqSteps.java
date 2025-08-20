package api.stepdefinition;

import api.Pojo.PostChainPojo;
import api.Pojo.UserChainPojo;
import api.base.BaseApiTest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


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
    ObjectMapper mapper = new ObjectMapper();
    JsonNode node;
    String jsonpath;
    String jsoncontent;


    @Given("I create a new user in users endpoint")
    public void iCreateANewUserInUsersEndpoint() throws IOException {
        // Step 1: Create a user POJO

        jsonpath = TestUtils.getProperty("UserChainJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);

        String name=node.get("name").asText();
        String username=node.get("username").asText();
        String email=node.get("email").asText();

        userChainPojo.setName(name);
        userChainPojo.setUsername(username);
        userChainPojo.setEmail(email);

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
    public void iCreateAPostForThatUser() throws IOException {
        postChainPojo.setId(id);

        jsonpath = TestUtils.getProperty("PostChainJsonPath");
        jsoncontent = new String(Files.readAllBytes(Paths.get(jsonpath)));
        node = mapper.readTree(jsoncontent);

        String title=node.get("title").asText();
        String body=node.get("body").asText();
        int userId=node.get("userId").asInt();

        postChainPojo.setTitle(title);
        postChainPojo.setBody(body);
        postChainPojo.setUserId(userId);

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
