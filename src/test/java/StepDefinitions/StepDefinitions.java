package StepDefinitions;

import Base.DriverManager;
import Pages.LoginPage;
import Pages.RegisterPage;
import Pages.WishListPage;
import Utils.ExtentManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class StepDefinitions
{
    WebDriver driver = DriverManager.getDriver();

    RegisterPage registerPage = new RegisterPage(driver);
    LoginPage loginPage=new LoginPage(driver);
    WishListPage wishListPage=new WishListPage(driver);

    @Given("as a new user I launch the website{string}")
    public void asANewUserILaunchTheWebsite(String testcase) throws IOException {
        ExtentManager.createTest(testcase);
        registerPage.launchUrl();
    }

    @Then("I click on the {string} under Profile button")
    public void iClickOnTheUnderProfileButton(String link) throws IOException, ParseException {

        if(link.equalsIgnoreCase("SignUp"))
        {
            registerPage.clickOnTheProfileIcon();
            registerPage.clickOnTheSignUpLink();
            registerPage.waitFrPresenceOfEmail();
        }
        else {
            loginPage.clickOnTheProfileIcon();
            loginPage.clickOnTheLoginLink();
            loginPage.waitFrPresenceOfEmail();
        }
    }

    @Then("I enter my email address in the email address textbox")
    public void iEnterMyEmailAddressInTheEmailAddressTextbox() throws IOException, ParseException {
        registerPage.enterEmailAddForRegister();
    }

    @Then("I enter my password in the password textbox")
    public void iEnterMyPasswordInThePasswordTextbox() throws IOException, ParseException {
        registerPage.enterPwdForRegister();
    }

    @Then("I click on the {string} button")
    public void iClickOnTheButton(String buttonname) throws IOException, ParseException {
        if(buttonname.equalsIgnoreCase("SignUp"))
        {
            registerPage.clickOnTheSignUpButton();
        }
        else {
            loginPage.clickOnTheLoginButton();
        }
    }

    @When("I click on the Profile button to validate the emailid")
    public void iClickOnTheProfileButtonToValidateTheEmailid() throws IOException, ParseException {
        registerPage.clickOnTheProfileIconAfterSignup();
    }

    @Then("the email id should be same with the given email id")
    public void theEmailIdShouldBeSameWithTheGivenEmailId() throws IOException, ParseException {
        registerPage.validateEmailId();
    }

    @Then("I click on the Edit button")
    public void iClickOnTheEditButton() throws IOException, ParseException {
        registerPage.clickOnTheEditBtn();
    }

    @And("I enter my name in the name textbox")
    public void iEnterMyNameInTheNameTextbox() throws IOException, ParseException {
        registerPage.enterNameForEdit();

    }

    @And("I enter my mobile number in the mobile number textbox")
    public void iEnterMyNameInTheMobileNumberTextbox() throws IOException, ParseException {
        registerPage.enterPhNumberForEdit();

    }

    @When("I click on the Confirm button")
    public void iClickOnTheConfirmButton() throws IOException, ParseException {
        registerPage.clickOnTheUpdateBtn();

    }

    @Then("the name and mobile number should be same with the given details")
    public void theNameAndMobileNumberShouldBeSameWithTheGivenDetails() throws IOException, ParseException {
        registerPage.validateName();
        registerPage.validatePhoneNumber();
        registerPage.quitdriver();
        ExtentManager.getInstance().flush();
    }
    //-------------------------------------Login Steps-----------------------------------------//

    @Then("I enter my email address in the email address textbox for login")
    public void iEnterMyEmailAddressInTheEmailAddressTextboxForLogin() throws IOException, ParseException {
        loginPage.enterEmailAddForLogin();
    }

    @Then("I enter my password in the password textbox for login")
    public void iEnterMyPasswordInThePasswordTextboxForLogin() throws IOException, ParseException {
        loginPage.enterPwdForLogin();
    }

    //------------------------------------LogOut Steps--------------------------------------------//

    @Then("I click on the LogOut button")
    public void iClickOnTheLogOutButton() throws IOException, ParseException {
        loginPage.clickOnTheLogOutButton();
    }

    @Then("I validate the Logout functionality")
    public void iValidateTheLogoutFunctionality() throws IOException, ParseException {
        loginPage.validateLogOut();
    }
    //-------------------------------------WishList Steps-----------------------------------------//

    @When("I enter the searchitem in searchbox")
    public void iEnterTheSearchitemInSearchbox() throws IOException, ParseException {

        wishListPage.enterSearchItem();
    }

    @Then("the searchitem should be appeared in the search results")
    public void theSearchitemShouldBeAppearedInTheSearchResults() throws IOException, ParseException {
        wishListPage.verifyTheSearchHeader();
    }

    @Then("I Apply all the filters")
    public void iApplyAllTheFilters() throws IOException, ParseException, InterruptedException {
        wishListPage.clickOnAllFilters();

    }

    @And("I add three items in the wishlist")
    public void iAddThreeItemsInTheWishlist() throws IOException, ParseException, InterruptedException {
        wishListPage.addWishlistItems();
        wishListPage.retriveWishListedItems();
    }

    @When("I navigate wishlist page")
    public void iNavigateWishlistPage() throws IOException, ParseException {
        wishListPage.navigateToWishListPage();
    }

    @Then("the wishlisted items should be present")
    public void theWishlistedItemsShouldBePresent() throws IOException, ParseException {
        wishListPage.validationOfWishListedItems();
    }

    //--------------------------------------------Home Page-------------------------------------------------------//
    @And("I scroll down till phone icon")
    public void iScrollDownTillPhoneIcon() throws IOException, ParseException {
        loginPage.scrolldowntillPhoneIcon();
    }

    @When("I click on the Phone icon")
    public void iClickOnThePhoneIcon() throws IOException, ParseException {
        loginPage.clickOnThePhoneIcon();

    }

    @Then("I retrieve the text from the alert pop up and cancel it")
    public void iRetrieveTheTextAndCancelThePopup() {
        loginPage.alertPopUpAppears();
    }
}
