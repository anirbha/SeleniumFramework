package Pages;

import Base.BaseTest;
import Base.DriverManager;
import Utils.ExtentManager;
import Utils.Log;
import Utils.TestUtils;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;

public class LoginPage extends BaseTest {

    String url;
    private WebDriver driver;
    Logger logger= TestUtils.logger;



    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }


    public void clickOnTheLoginLink() throws IOException, ParseException {
//        driverReference();
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LoginLink"));
        TestUtils.waitImplicitly(driver,3);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Login link Clicked",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Profile Icon not clicked");
        }
        Log.info("Clicked on the Sign up Link");

    }


    public void clickOnTheLoginButton() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LoginButton"));
        TestUtils.waitImplicitly(driver,3);
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Login Done",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Login Done");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful");
            Log.error("Login is not successful");
        }

    }

    public void waitFrPresenceOfEmail() throws IOException, ParseException {
        TestUtils.waitExplicitlyForWebElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBoxLogin"));
        String path=TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Login Pop up appeared",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Login pop up did not appear");
        }
        Log.info("Login pop up appeared");

    }

    public void enterEmailAddForLogin() throws IOException, ParseException {
        String emailaddress= TestUtils.ReadExcelData(0,1,1);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("EmailAddressTxtBoxLogin"),emailaddress);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Email id entered in the field");
        Log.info("Email id entered in the email id field");

    }

    public void enterPwdForLogin() throws IOException, ParseException {
        String password= TestUtils.ReadExcelData(0,1,2);
        TestUtils.EnterValue(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("PasswordTxtBoxLogin"),password);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Password entered in the field");
        Log.info("Password entered in the email id field");
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



    public void clickOnTheProfileIconAfterSignup() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("ProfileLink"));
        String expectedHeading=TestUtils.getProperty("ExpectedHeading");
        String actualHeading= TestUtils.getTextFromElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("MyAccountHeader"));
        String path=TestUtils.takeScreenshot(driver);
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

    public void clickOnTheLogOutButton() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
        TestUtils.ClickOnElements(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LogOutLink"));
        ExtentManager.getTest().info("Clicked on the Logout button");
        Log.info("Clicked on the Log Out button");

    }

    public void validateLogOut() throws IOException, ParseException {
        String path=TestUtils.takeScreenshot(driver);
        try
        {   TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));
            Assert.assertTrue(TestUtils.CheckPresenceOfElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LoginLink")));
            ExtentManager.getTest().pass("LogOut Successfully", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Log Out Successfully from the application");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Log Out is not successful",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Log Out failed");
        }

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }

    public void scrolldowntillPhoneIcon() throws IOException, ParseException {

        TestUtils.ScrollUsingJS(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("PhoneIcon"));

        String path=TestUtils.takeScreenshot(driver);
        try
        {
            ExtentManager.getTest().info("Scrolled down to the Phone Icon"
            );
            Log.info("Scrolled down to the Phone Icon");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Didn't Scrolled down to the Phone Icon");
            Log.error("Didn't Scrolled down to the Phone Icon");
        }


    }

    public void clickOnThePhoneIcon() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String)Objects.requireNonNull(TestUtils.ReadJsonData()).get("PhoneIcon"));
        String path=TestUtils.takeScreenshot(driver);
        try
        {
            ExtentManager.getTest().info("Clicked to the Phone Icon", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Clicked on the Phone Icon");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Didn't click the phone icon",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Didn't click the phone icon");
        }

    }

    public void alertPopUpAppears() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        String alerttxt= alert.getText();

        ExtentManager.getTest().info("The Alert text is" + alerttxt);
        Log.info("The Alert text is" + alerttxt);

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }
}
