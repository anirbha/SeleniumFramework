package StepDefinitions;

import Base.BaseTest;
import Pages.LoginPage;
import Utils.ExtentManager;
import Utils.TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.IOException;

public class LoginSteps extends BaseTest {
    LoginPage loginPage = new LoginPage();
    static ExtentReports extent = ExtentManager.getInstance();
    ExtentTest test;

    @Given("as a new user I launch the website")
    public void asANewUserILaunchTheWebsite() throws IOException {
        String testcasename;
        test = extent.createTest("Login Scenario");
        loginPage.driversetup();
        loginPage.launchUrl();
        test.info("Launched the urban ladder website");

    }

    @Then("I click on the SignUp button under Profile button to register")
    public void iClickOnTheSignUpButtonUnderProfileButtonToRegister() throws IOException, ParseException, InterruptedException {
        loginPage.clickOnTheProfileIcon();
        loginPage.clickOnTheSignUpLink();

        test.info("Clicked on the Profile Icon");

    }


    @Then("I enter my email address in the email address testbox")
    public void iEnterMyEmailAddressInTheEmailAddressTestbox() throws IOException, ParseException {
        loginPage.enterEmailAddForRegister();
        test.info("Entered valid Email Address");
    }

    @Then("I enter my password in the password testbox")
    public void iEnterMyPasswordInThePasswordTestbox() {
        loginPage.enterPwdForRegister();
    }

    @Then("I click on the SignUp button")
    public void iClickOnTheSignUpButton() {
        loginPage.clickOnTheSubmitButton();
    }

    @When("I click on the Profile button to validate the emailid")
    public void iClickOnTheProfileButtonToValidateTheEmailid() throws IOException, ParseException {
        loginPage.clickOnTheProfileIcon();
    }

    @Then("the email id should be same with the given email id")
    public void theEmailIdShouldBeSameWithTheGivenEmailId() {
    }
}
