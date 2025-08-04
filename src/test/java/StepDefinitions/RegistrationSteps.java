package StepDefinitions;

import Pages.LoginPage;
import Pages.RegisterPage;
import Utils.ExtentManager;
import Utils.TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class RegistrationSteps
{

    RegisterPage registerPage = new RegisterPage();
    LoginPage loginPage=new LoginPage();
    static ExtentReports extentReports = ExtentManager.getInstance();
    ExtentTest extentTest;


    @Given("as a new user I launch the website")
    public void asANewUserILaunchTheWebsite() throws IOException {
        String testcase= TestUtils.ReadExcelData(0,1,0);
        extentTest = extentReports.createTest(testcase);

        registerPage.driversetup();
        registerPage.launchUrl();

//        extentTest.info("Launched the urban ladder website");

    }

//    @Then("I click on the SignUp {} under Profile button to register")
//    public void iClickOnTheLinkUnderProfileButtonToRegister(String linkname) throws IOException, ParseException, InterruptedException {
//        registerPage.clickOnTheProfileIcon();
//        registerPage.clickOnTheSignUpLink();
//        registerPage.waitFrPresenceOfEmail();
//        extentTest.info("Clicked on the Profile Icon");
//
//
//    }

    @Then("I click on the {string} under Profile button to register")
    public void iClickOnTheUnderProfileButtonToRegister(String linkname) throws IOException, ParseException {

        if(linkname.equalsIgnoreCase("Signup"))
        {   registerPage.clickOnTheProfileIcon();
            registerPage.clickOnTheSignUpLink();
            registerPage.waitFrPresenceOfEmail();
            extentTest.info("Clicked on the Sign up link");
        }
        else {
            registerPage.clickOnTheProfileIcon();
            loginPage.clickOnTheLoginLink();
            registerPage.waitFrPresenceOfEmail();
            extentTest.info("Clicked on the Login link");
        }
    }


    @Then("I enter my email address in the email address testbox")
    public void iEnterMyEmailAddressInTheEmailAddressTestbox() throws IOException, ParseException {
        registerPage.enterEmailAddForRegister();
        extentTest.info("Entered valid Email Address");
    }

    @Then("I enter my password in the password testbox")
    public void iEnterMyPasswordInThePasswordTestbox() throws IOException, ParseException {
        registerPage.enterPwdForRegister();
        extentTest.info("Entered valid Password");
    }

    @Then("I click on the SignUp button")
    public void iClickOnTheSignUpButton() throws IOException, ParseException {
        registerPage.clickOnTheSignUpButton();

        extentTest.info("Clicked on the Sign up button");

    }

    @Then("I click on the {string} button")
    public void iClickOnTheButton(String btnname) throws IOException, ParseException {
        if(btnname.equalsIgnoreCase("SignUp"))
        {
            registerPage.clickOnTheSignUpButton();
            extentTest.info("Clicked on the Sign up button");
        }
        else
        {
            loginPage.clickOnTheLoginButton();
            extentTest.info("Clicked on the Login button");
        }
    }

    @When("I click on the Profile button to validate the emailid")
    public void iClickOnTheProfileButtonToValidateTheEmailid() throws IOException, ParseException {
        registerPage.clickOnTheProfileIconAfterSignup(extentTest);

    }

    @Then("the email id should be same with the given email id")
    public void theEmailIdShouldBeSameWithTheGivenEmailId() throws IOException, ParseException {
        registerPage.validateEmailId(extentTest);
    }

    @Then("I click on the Edit button")
    public void iClickOnTheEditButton() throws IOException, ParseException {
        registerPage.clickOnTheEditBtn();
        extentTest.info("Clicked on the Edit button");
    }

    @And("I enter my name in the name textbox")
    public void iEnterMyNameInTheNameTextbox() throws IOException, ParseException {
        registerPage.enterNameForEdit();
        extentTest.info("Entered the name in the name textbox");
    }

    @And("I enter my mobile number in the mobile number textbox")
    public void iEnterMyNameInTheMobileNumberTextbox() throws IOException, ParseException {
        registerPage.enterPhNumberForEdit();
        extentTest.info("Entered the phone number in the name textbox");
    }

    @When("I click on the Confirm button")
    public void iClickOnTheConfirmButton() throws IOException, ParseException {
        registerPage.clickOnTheUpdateBtn();
        extentTest.info("Clicked on the Update button");
    }

    @Then("the name and mobile number should be same with the given details")
    public void theNameAndMobileNumberShouldBeSameWithTheGivenDetails() throws IOException, ParseException {
        registerPage.validateName(extentTest);
        registerPage.validatePhoneNumber(extentTest);
        registerPage.quitdriver();


    }



}
