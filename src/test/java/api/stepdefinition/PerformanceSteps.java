package api.stepdefinition;

import api.base.BaseApiTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import ui.Utils.ExtentManager;
import ui.Utils.Log;
import ui.Utils.TestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertTrue;

public class PerformanceSteps {

    private int numberOfRequests;
    private int maxAcceptableResponseTimeMs;
    private List<Long> responseTimes = new ArrayList<>();
    private static final String BASE_URL= TestUtils.getProperty("BASE_URL");
    private String endpoint=TestUtils.getProperty("BasePathQueryParam");
    
    @Given("I have performance test parameters")
    public void iHavePerformanceTestParameters() {
        Object noOfReq=(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("PerformanceJsonPath")))).get("numberOfRequests");
        numberOfRequests= ((Long) noOfReq).intValue();
        Object maxActTime=(Objects.requireNonNull(TestUtils.readJsonData(TestUtils.getProperty("PerformanceJsonPath")))).get("maxAcceptableResponseTimeMs");
        maxAcceptableResponseTimeMs=((Long) maxActTime).intValue() ;
        Log.info("Maximum no of Requests " + numberOfRequests + " Maximum Acceptable Response Time" + maxAcceptableResponseTimeMs);
        ExtentManager.getTest().info("Maximum no of Requests " + numberOfRequests + " Maximum Acceptable Response Time" + maxAcceptableResponseTimeMs);
    }

    @When("I send a high volume of GET requests")
    public void iSendAHighVolumeOfGETRequests() {
        responseTimes.clear();
        for (int noOfReq = 0; noOfReq < numberOfRequests; noOfReq++) {
            long start = System.currentTimeMillis();

            BaseApiTest.getRequest(BASE_URL,endpoint).get();
            long end = System.currentTimeMillis();
            responseTimes.add(end - start);

        }
    }

    @Then("the average response time should be below the maximum acceptable threshold")
    public void theAverageResponseTimeShouldBeBelowTheMaximumAcceptableThreshold() {

        double avg = responseTimes.stream().mapToLong(Long::longValue).average().orElse(0);
        try{
            assertTrue("Average response time exceeded threshold: " + avg + " > " + maxAcceptableResponseTimeMs,
                    avg < maxAcceptableResponseTimeMs);
            Log.info("Average response time does not exceed threshold: " + avg + " < " + maxAcceptableResponseTimeMs);
            ExtentManager.getTest().pass("Average response time does not exceed threshold: " + avg + " < " + maxAcceptableResponseTimeMs);
        } catch (Exception e) {
            Log.error("Average response time exceeded threshold: " + avg + " > " + maxAcceptableResponseTimeMs);
            ExtentManager.getTest().fail("Average response time exceeded threshold: " + avg + " > " + maxAcceptableResponseTimeMs);
        }

    }


}
