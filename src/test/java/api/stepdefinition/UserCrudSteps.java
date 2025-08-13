package api.stepdefinition;


import api.models.UserPOJO;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.Objects;

import static junit.framework.Assert.assertEquals;

public class UserCrudSteps {
    private final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private UserPOJO userPOJO;
    private int id;
    private Response response;



    @Given("I create a new user")
    public void iCreateANewUserWithNameAndEmail() {

        String name=(String) Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("CreateRecord"))).get("name");
        String email=(String) Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("CreateRecord"))).get("email");
        userPOJO =new UserPOJO(name,email);
        response= RestAssured.given().baseUri(BASE_URL).basePath("/users").
                  header("Content-Type","application/json")
                .body(userPOJO).post();

    }
    @Then("the status code should be {string}")
    public void theStatusCodeShouldBe(String code) {
       try{
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
        userPOJO.setId(id);
        try
        {
            assertEquals("The name matches as expected", userPOJO.getName(),response.jsonPath().getString("name"));
            assertEquals("The email matches as expected", userPOJO.getEmail(),response.jsonPath().getString("email"));
            ExtentManager.getTest().info("The newly created id is "+ userPOJO.getId());
            ExtentManager.getTest().pass("The name "+ response.jsonPath().getString("name") + "the email "+response.jsonPath().getString("email") +"is matching as expected");
            Log.info("The name "+ response.jsonPath().getString("name") + " the email "+response.jsonPath().getString("email") +"is matching as expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Name and email id is not matching");
            Log.error("The name and email id is not matching");
        }


    }




}
