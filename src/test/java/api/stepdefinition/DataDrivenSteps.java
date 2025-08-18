package api.stepdefinition;

import api.Pojo.DataDrivenPojo;
import api.base.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import ui.Utils.ExcelUtils;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DataDrivenSteps {
    private List<DataDrivenPojo> dataDrivenPojos;
    private List<Response> responses = new ArrayList<>();
    private final String BASE_URL = TestUtils.getProperty("BASE_URL");
    private String endpoint=TestUtils.getProperty("UsersBasepath");

    @Given("I read user data from excel")
    public void iReadUserDataFromExcel() {
        dataDrivenPojos = ExcelUtils.readExcelData();
    }

    @When("I create users using API endpoint")
    public void iCreateUsersUsingAPIEndpoint() {
        for (DataDrivenPojo dataDrivenPojo : dataDrivenPojos) {

            Response response= BaseApiTest.getRequest(BASE_URL,endpoint).body(dataDrivenPojo).post();

            responses.add(response);
            Log.info("Created user: " + response.getBody().asString());
            Log.info("Created User's id " + response.jsonPath().getInt("id") +
                    " Created User's name " + response.jsonPath().getString("name")
                    + " Created User's email " + response.jsonPath().getString("email")
                    + " Created User's username " + response.jsonPath().getString("username"));
            ExtentManager.getTest().info("Created User's id " + response.jsonPath().getInt("id") +
                    " Created User's name " + response.jsonPath().getString("name")
                    + " Created User's email " + response.jsonPath().getString("email")
                    + " Created User's username " + response.jsonPath().getString("username"));

        }


    }

    @Then("all users should be created successfully")
    public void allUsersShouldBeCreatedSuccessfully() {

        for (int i = 0; i < responses.size(); i++) {
            Response response = responses.get(i);
            DataDrivenPojo expected = dataDrivenPojos.get(i);

            // Assert status code
            assertEquals("Status code for user " + expected.getUsername(), 201, response.getStatusCode());
            Log.info("Status code for user " + response.getStatusCode());
            ExtentManager.getTest().pass("Status code for user " + response.getStatusCode() + " is matching");

            // Deserialize response to User POJO
            DataDrivenPojo actual = response.as(DataDrivenPojo.class);


            // Assert fields
            try{
                assertEquals("Name matches for user " + expected.getUsername(), expected.getName(), actual.getName());
                Log.info("Name matches for user " + expected.getUsername() + "Expected name: " + expected.getName() + "Actual name: " + actual.getName());
                ExtentManager.getTest().pass("Name matches for user " + expected.getUsername() + "Expected name: " + expected.getName() + "Actual name: " + actual.getName());

                assertEquals("Username matches for user " + expected.getUsername(), expected.getUsername(), actual.getUsername());
                Log.info("Username matches for user " + expected.getUsername() + "Expected username: " + expected.getUsername() + "Actual username: " + actual.getUsername());
                ExtentManager.getTest().pass("Username matches for user " + expected.getUsername() + "Expected username: " + expected.getUsername() + "Actual username: " + actual.getUsername());

                assertEquals("Email matches for user " + expected.getUsername(), expected.getEmail(), actual.getEmail());
                Log.info("Email matches for user " + expected.getUsername() + "Expected email: " + expected.getEmail() + "Actual email: " + actual.getEmail());
                ExtentManager.getTest().pass("Email matches for user " + expected.getUsername() + "Expected email: " + expected.getEmail() + "Actual email: " + actual.getEmail());


            } catch (Exception e) {

                Log.error("Name mismatches for user " + expected.getUsername() + "Expected name: " + expected.getName() + "Actual name: " + actual.getName());
                ExtentManager.getTest().pass("Name mismatches for user " + expected.getUsername() + "Expected name: " + expected.getName() + "Actual name: " + actual.getName());

                Log.error("Username mismatches for user " + expected.getUsername() + "Expected username: " + expected.getUsername() + "Actual username: " + actual.getUsername());
                ExtentManager.getTest().pass("Username mismatches for user " + expected.getUsername() + "Expected username: " + expected.getUsername() + "Actual username: " + actual.getUsername());

                Log.error("Email mismatches for user " + expected.getUsername() + "Expected email: " + expected.getEmail() + "Actual email: " + actual.getEmail());
                ExtentManager.getTest().pass("Email mismatches for user " + expected.getUsername() + "Expected email: " + expected.getEmail() + "Actual email: " + actual.getEmail());


            }

        }
    }
}
