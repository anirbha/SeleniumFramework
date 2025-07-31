package StepDefinitions;

import Base.BaseTest;
import Pages.LoginPage;
import Utils.ExtentManager;
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

//    @Given("user is on login page")
//    public void user_is_on_login_page() {
//
//        loginPage.driversetup();
//        loginPage.launchUrl();
//    }
//    @When("user enters username {string} and password {string}")
//    public void user_enters_username_and_password(String username, String password) {
//        loginPage.enterUsername(username);
//        loginPage.enterPassword(password);
//    }
//    @When("user clicks login")
//    public void user_clicks_login() {
//        loginPage.clickLogin();
//    }
//    @Then("user should see homepage")
//    public void user_should_see_homepage() {
//        Assert.assertTrue(loginPage.isLoginSuccessful());
//    }

    @Given("as a new user I launch the website")
    public void asANewUserILaunchTheWebsite() throws IOException {
        test = extent.createTest("Login Scenario");
        loginPage.driversetup();
        loginPage.launchUrl();
        test.info("Navigated to login page");
    }

    @Then("I click on the SignUp button under Profile button to register")
    public void iClickOnTheSignUpButtonUnderProfileButtonToRegister() throws IOException, ParseException {
        loginPage.clickOnTheProfileIcon();
        test.info("Entered username and password");

    }


}
