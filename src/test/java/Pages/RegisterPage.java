package Pages;

import Base.BaseTest;
import Base.DriverManager;
import Utils.ExtentManager;
import Utils.Log;
import Utils.TestUtils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Objects;



public class RegisterPage extends BaseTest {

    String url;
    private WebDriver driver;

    public ExtentReports extentReports = ExtentManager.getInstance();
//    public ExtentTest extentTest=extentReports.createTest("testcase");

//    Logger logger=TestUtils.logger;

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }


    public void launchUrl() throws IOException {
        url=TestUtils.getProperty("URL");
        TestUtils.LaunchUrl(driver,url);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                   "Website is launched",
                   MediaEntityBuilder.createScreenCaptureFromPath(path).build()
           );
       } catch (Exception e) {
           ExtentManager.getTest().fail("Website not launched");
       }
        Log.info("Launch the url");


    }

    public void clickOnTheProfileIcon() throws IOException, ParseException {

        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Profile Icon Clicked",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Profile Icon not clicked");
        }
        Log.info("Clicked on the Profile Icon");

    }

    public void clickOnTheSignUpLink() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SignUpLink"));

        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Sign Up link Clicked",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign Up link not clicked");
        }
        Log.info("Clicked on the Sign up Link");

    }

    public void waitFrPresenceOfEmail() throws IOException, ParseException {
        TestUtils.waitExplicitlyForWebElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBox"));
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Sign Up Pop up appeared",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign Up pop up did not appear");
        }
        Log.info("Sign up pop up appeared");

    }

    public void enterEmailAddForRegister() throws IOException, ParseException {
        String emailaddress= TestUtils.ReadExcelData(0,1,1);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBox"),emailaddress);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Email id entered in the field");
        Log.info("Email id entered in the email id field");
    }

    public void enterPwdForRegister() throws IOException, ParseException {
        String password= TestUtils.ReadExcelData(0,1,2);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("PasswordTxtBox"),password);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Password entered in the field");
        Log.info("Password entered in the email id field");
    }

    public void clickOnTheSignUpButton() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("SignUpButton"));
        TestUtils.waitImplicitly(driver,3);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Sign Up Done",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Sign Up Done");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful");
            Log.error("Sign Up not successful");
        }

    }
    public void clickOnTheProfileIconAfterSignup() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("ProfileLink"));
        String expectedHeading=TestUtils.getProperty("ExpectedHeading");
        String actualHeading= TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("MyAccountHeader"));
        String path=TestUtils.takeScreenshot(driver);
//        TestUtils.assertAndLogEquals(driver,extentTest,actualHeading,expectedHeading,"My Account Page Opened");
        try
        {
            Assert.assertEquals(actualHeading, expectedHeading, "My Account Page Opened");
            ExtentManager.getTest().pass("Sign Up Done", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("My Account Page Opened");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("My Account Page Opened");
        }

    }

    public void validateEmailId() throws IOException, ParseException {
        String expectedEmailAdd= TestUtils.ReadExcelData(0,1,1);
        String actualEmailAdd=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredEmailAddress"));
        String path=TestUtils.takeScreenshot(driver);
//        TestUtils.assertAndLogEquals(driver,extentTest,actualEmailAdd,expectedEmailAdd,"Email address is matching");
        try
        {
            Assert.assertEquals(actualEmailAdd, expectedEmailAdd, "Email address is matching");
            ExtentManager.getTest().pass("Email address is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Email id is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Email address is not matching",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Email id is matching with the expected");
        }

    }

    public void clickOnTheEditBtn() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EditButton"));
        TestUtils.waitImplicitly(driver,3);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Click on the Edit Button",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Email id is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Edit button didn't clicked");
            Log.error("Email id is matching with the expected");
        }

    }

    public void enterNameForEdit() throws IOException, ParseException {
        String name=TestUtils.ReadExcelData(0,1,3);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("NameTextBox"),name);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Entered name for editing",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Entered name for editing");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Edit button didn't clicked");
            Log.error("Couldn't enter name for editing");
        }
    }

    public void enterPhNumberForEdit() throws IOException, ParseException {
        String phonenumber=TestUtils.ReadExcelData(0,1,4);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("MobileNumberTextBox"),phonenumber);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Entered phone number for editing",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.info("Entered phone number for editing");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Edit button didn't clicked");
            Log.error("Couldn't enter phone number for editing");
        }
    }

    public void clickOnTheUpdateBtn() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UpdateButton"));
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Update button clicked");
        Log.info("Update button clicked");

    }

    public void validateName() throws IOException, ParseException {
        String expected_name=TestUtils.ReadExcelData(0,1,3);
        String actual_name=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredName"));
        String path=TestUtils.takeScreenshot(driver);

        try
        {
            Assert.assertEquals(actual_name, expected_name, "Name is matching");
            ExtentManager.getTest().pass("Name is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Name is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Email address is not matching",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Name is matching with the expected");
        }
    }

    public void validatePhoneNumber() throws IOException, ParseException {
        String expected_name=TestUtils.ReadExcelData(0,1,4);
        String actual_name=TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("RegisteredPhoneNumber"));
        String path=TestUtils.takeScreenshot(driver);

        try
        {
            Assert.assertEquals(actual_name, expected_name, "Phone number is matching");
            ExtentManager.getTest().pass("Phone number is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Phone number is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Phone number is not matching",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Phone number is matching with the expected");
        }

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();

    }
}
