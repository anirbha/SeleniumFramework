package StepDefinitions;

import Actions.LoginActions;
import Actions.RegisterActions;
import Base.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class RegisterSteps {

    private WebDriver driver;
    private RegisterActions registerActions;
    private LoginActions loginActions;
    public RegisterSteps()
    {
        this.driver= DriverManager.getDriver();
        this.registerActions=new RegisterActions(driver);
        this.loginActions=new LoginActions(driver);

    }

    @And("I click on the SignUp Link under Profile button")
    public void iClickOnTheSignUpLinkUnderProfileButton() throws IOException, ParseException {
        loginActions.clickOnTheUserProfileIcon();
        registerActions.clickOnTheSignUpLink();
    }

    @And("I enter my email address in the email address textbox")
    public void iEnterMyEmailAddressInTheEmailAddressTextbox() throws IOException, ParseException {
        registerActions.enterEmailAddForRegister();
    }

    @And("I enter my password in the password textbox")
    public void iEnterMyPasswordInThePasswordTextbox() throws IOException, ParseException {
        registerActions.enterPwdForRegister();
    }

    @And("I click on the SignUp button")
    public void iClickOnTheSignUpButton() throws IOException, ParseException {
        registerActions.clickOnTheSignUpButton();
    }

    @When("I click on the Edit button")
    public void iClickOnTheEditButton() throws IOException, ParseException {
        registerActions.clickOnTheEditBtn();
    }

    @And("I enter my name in the name textbox")
    public void iEnterMyNameInTheNameTextbox() throws IOException, ParseException {
        registerActions.enterNameForEdit();
    }

    @And("I enter my mobile number in the mobile number textbox")
    public void iEnterMyMobileNumberInTheMobileNumberTextbox() throws IOException, ParseException {
        registerActions.enterPhNumberForEdit();
    }

    @When("I click on the Confirm button")
    public void iClickOnTheConfirmButton() throws IOException, ParseException {
        registerActions.clickOnTheUpdateBtn();
    }
}
