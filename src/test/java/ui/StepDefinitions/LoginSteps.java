package ui.StepDefinitions;

import ui.Actions.LoginActions;
import ui.Base.DriverManager;
import ui.Utils.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;


public class LoginSteps {
    private WebDriver driver;
    private LoginActions loginActions;

    public LoginSteps()
    {
        this.driver=DriverManager.getDriver();
        this.loginActions=new LoginActions(driver);
    }

    @Given("as a user I launch the website")
    public void asAUserILaunchTheWebsite()  {
        loginActions.launchUrl();
    }

    @And("I click on the Login Link under Profile button")
    public void iClickOnTheLoginLinkUnderProfileButton()  {
        loginActions.clickOnTheUserProfileIcon();
        loginActions.clickOnTheLoginLink();
        loginActions.waitFrPresenceOfEmail();
    }

    @And("I enter my email address in the email address textbox for login")
    public void iEnterMyEmailAddressInTheEmailAddressTextboxForLogin() throws IOException {
        loginActions.enterEmailAddForLogin();
        
    }

    @And("I enter my password in the password textbox for login")
    public void iEnterMyPasswordInThePasswordTextboxForLogin() throws IOException {
        loginActions.enterPwdForLogin();
        
    }

    @When("I click on the Login button")
    public void iClickOnTheLoginButton() throws IOException {
        loginActions.clickOnTheLoginButton();
        
    }

    @When("I click on the Profile button to validate the emailid")
    public void iClickOnTheProfileButtonToValidateTheEmailid()  {
        loginActions.clickOnTheProfileLink();
    }

    @Then("the email id should be same with the given email id")
    public void theEmailIdShouldBeSameWithTheGivenEmailId() throws IOException {
        loginActions.validateEmailId();
    }

    @Then("the name and mobile number should be same with the given details")
    public void theNameAndMobileNumberShouldBeSameWithTheGivenDetails() throws IOException {
        loginActions.validateName();
        loginActions.validatePhoneNumber();

    }

    @When("I click on the LogOut button")
    public void iClickOnTheLogOutButton() throws IOException{
        loginActions.clickOnTheLogOutButton();
    }

    @Then("I validate the Logout functionality")
    public void iValidateTheLogoutFunctionality() throws IOException, org.json.simple.parser.ParseException {
        loginActions.validateLogOut();


    }

    @And("I scroll down till phone icon")
    public void iScrollDownTillPhoneIcon() throws IOException {
        loginActions.scrolldowntillPhoneIcon();
    }

    @When("I click on the Phone icon")
    public void iClickOnThePhoneIcon() throws IOException {
        loginActions.clickOnThePhoneIcon();
    }

    @Then("I retrieve the text from the alert pop up and cancel it")
    public void iRetrieveTheTextFromTheAlertPopUpAndCancelIt() throws InterruptedException, AWTException {
        loginActions.alertPopUpAppears();

    }
}
