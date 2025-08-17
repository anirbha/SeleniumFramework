package api.stepdefinition;

import api.Pojo.PaginationPojo;
import api.base.BaseApiTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import ui.Utils.ExcelUtils;
import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

public class PaginationSteps {
    private static final String BASE_URL = TestUtils.getProperty("BASE_URL");
    private static String endpoint=TestUtils.getProperty("BasePathQueryParam");
    private List<PaginationPojo> page1Posts;
    private List<PaginationPojo> page2Posts;
    String pagenoKey, pageNoValue;
    String limitKey, limitValue;
    Response response;

    @When("I request page number with limit")
    public void iRequestPageNumberWithLimit() throws IOException {
        pagenoKey= ExcelUtils.readExcelData(0,0,0,"PaginationExcelPath");
        pageNoValue=ExcelUtils.readExcelData(0,1,0,"PaginationExcelPath");
        limitKey=ExcelUtils.readExcelData(0,0,1,"PaginationExcelPath");
        limitValue=ExcelUtils.readExcelData(0,1,1,"PaginationExcelPath");


        response= BaseApiTest.getRequest(BASE_URL,endpoint).queryParam(pagenoKey,pageNoValue).queryParam(limitKey,limitValue).get().then().extract().response();
        Log.info("The first get method requested and " + response.getStatusCode()+ " status code received");
        page1Posts = response.jsonPath().getList("", PaginationPojo.class);
    }

    @Then("I should receive posts with that limit")
    public void iShouldReceivePostsWithThatLimit() {
        try{
            assertNotNull(page1Posts);
            assertEquals(Integer.parseInt(limitValue), page1Posts.size());
            Log.info("Given page limit value and retrieved size matches " + page1Posts.size());
            ExtentManager.getTest().pass("Given page limit value and retrieved size matches "+ page1Posts.size());
        }catch (Exception e)
        {
            Log.error("Given page limit value and retrieved size does not match " + page1Posts.size());
            ExtentManager.getTest().fail("Given page limit value and retrieved size does not match "+ page1Posts.size());
        }

    }

    @When("I newly request page number with limit")
    public void iNewlyRequestPageNumberWithLimit() throws IOException {
        pagenoKey= ExcelUtils.readExcelData(0,0,0,"PaginationExcelPath");
        pageNoValue=ExcelUtils.readExcelData(0,2,0,"PaginationExcelPath");
        limitKey=ExcelUtils.readExcelData(0,0,1,"PaginationExcelPath");
        limitValue=ExcelUtils.readExcelData(0,2,1,"PaginationExcelPath");

        response= RestAssured.given().queryParam(pagenoKey,pageNoValue).queryParam(limitKey,limitValue).baseUri(BASE_URL)
                .basePath(endpoint).get().then().extract().response();

        page2Posts = response.jsonPath().getList("", PaginationPojo.class);
        Log.info("The second get method requested and " + response.getStatusCode()+ " status code received");

    }
    @And("the posts on pages should not overlap")
    public void thePostsOnPagesShouldNotOverlap() {
        try{
            assertNotNull(page2Posts);
            assertEquals(Integer.parseInt(limitValue), page2Posts.size());
            Log.info("Given page limit value and retrieved size matches " + page2Posts.size());
            ExtentManager.getTest().pass("Given page limit value and retrieved size matches "+ page2Posts.size());
        }catch (Exception e)
        {
            Log.error("Given page limit value and retrieved size does not match " + page2Posts.size());
            ExtentManager.getTest().fail("Given page limit value and retrieved size does not match "+ page2Posts.size());
        }
        Set<Integer> idsPage1 = new HashSet<>();
        for (PaginationPojo post : page1Posts) {
            idsPage1.add(post.getId());
            Log.info("First Page ids: "+ post.getId());
            ExtentManager.getTest().info("First Page ids: "+ post.getId());
        }
        try{
            for (PaginationPojo post : page2Posts) {
                assertFalse("Overlap found: Post ID " + post.getId(), idsPage1.contains(post.getId()));
                Log.info("No overlapping.Second page id" + post.getId() );
                ExtentManager.getTest().pass("No overlapping of ids.Second page id" + post.getId() );
            }
        } catch (Exception e) {
            Log.error("overlapping");
            ExtentManager.getTest().fail(" overlapping of ids");
        }

    }
}
