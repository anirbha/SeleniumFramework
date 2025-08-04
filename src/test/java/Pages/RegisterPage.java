package Pages;

import Base.BaseTest;
import Utils.TestUtils;
import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;



public class RegisterPage extends BaseTest {

    String url;
    Logger logger=TestUtils.logger;


    public void driversetup()
    {
        initiatedriver();
        driver=getDriver();
        logger.info("Driver getting started");
    }

    public void launchUrl() throws IOException {

        url=TestUtils.getProperty("URL");
        TestUtils.LaunchUrl(driver,url);
//        String screnshtpath=TestUtils.takeScreenshot(driver);
//        extentTest.log(Status.INFO, (Markup) extentTest.addScreenCaptureFromPath(screnshtpath));
//        extentTest.log(Status.INFO, (Markup) extentTest.addScreenCaptureFromPath(screnshtpath));
        logger.info("Launch the url");



    }

    public void clickOnTheProfileIcon() throws IOException, ParseException {

        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        TestUtils.takeScreenshot(driver);

    }

    public void clickOnTheSignUpLink() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SignUpLink"));
        TestUtils.takeScreenshot(driver);
    }

    public void waitFrPresenceOfEmail() throws IOException, ParseException {
        TestUtils.waitExplicitlyForWebElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBox"));
    }

    public void enterEmailAddForRegister() throws IOException, ParseException {
        String emailaddress= TestUtils.ReadExcelData(0,1,1);

        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBox"),emailaddress);
        TestUtils.takeScreenshot(driver);
    }

    public void enterPwdForRegister() throws IOException, ParseException {
        String password= TestUtils.ReadExcelData(0,1,2);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("PasswordTxtBox"),password);
        TestUtils.takeScreenshot(driver);
    }

    public void clickOnTheSignUpButton() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SignUpButton"));
        TestUtils.waitImplicitly(driver,3);
        TestUtils.takeScreenshot(driver);

    }
    public void clickOnTheProfileIconAfterSignup(ExtentTest extentTest) throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("ProfileLink"));
        String expectedHeading=TestUtils.getProperty("ExpectedHeading");
        String actualHeading= TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("MyAccountHeader"));
        TestUtils.assertAndLogEquals(driver,extentTest,actualHeading,expectedHeading,"My Account Page Opened");
    }

    public void validateEmailId(ExtentTest extentTest) throws IOException, ParseException {
        String expectedEmailAdd= TestUtils.ReadExcelData(0,1,1);
        String actualEmailAdd=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredEmailAddress"));
        TestUtils.assertAndLogEquals(driver,extentTest,actualEmailAdd,expectedEmailAdd,"Email address is matching");
    }

    public void clickOnTheEditBtn() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EditButton"));
        TestUtils.waitImplicitly(driver,3);
        TestUtils.takeScreenshot(driver);
    }

    public void enterNameForEdit() throws IOException, ParseException {
        String name=TestUtils.ReadExcelData(0,1,3);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("NameTextBox"),name);
        TestUtils.takeScreenshot(driver);
    }

    public void enterPhNumberForEdit() throws IOException, ParseException {
        String phonenumber=TestUtils.ReadExcelData(0,1,4);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("MobileNumberTextBox"),phonenumber);
        TestUtils.takeScreenshot(driver);
    }

    public void clickOnTheUpdateBtn() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UpdateButton"));
        TestUtils.takeScreenshot(driver);
    }

    public void validateName(ExtentTest extentTest) throws IOException, ParseException {
        String expected_name=TestUtils.ReadExcelData(0,1,3);
        String actual_name=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredName"));
        TestUtils.assertAndLogEquals(driver,extentTest,actual_name,expected_name,"The Name is matching");
    }

    public void validatePhoneNumber(ExtentTest extentTest) throws IOException, ParseException {
        String expected_name=TestUtils.ReadExcelData(0,1,4);
        String actual_name=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredPhoneNumber"));
        TestUtils.assertAndLogEquals(driver,extentTest,actual_name,expected_name,"The Phone number is matching");
    }
}
