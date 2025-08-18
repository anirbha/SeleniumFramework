package ui.Actions;



import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import ui.Utils.*;
import ui.pages.LoginPage;

import java.io.IOException;
import java.time.Duration;

public class LoginActions {

    String url;
    private WebDriver driver;
    LoginPage loginPage = new LoginPage();

    public LoginActions(WebDriver driver) {
        this.driver = driver;

    }
    // This method will launch the URL
    public void launchUrl() {
        url = TestUtils.getProperty("URL");
        TestUtils.launchUrl(driver, url);
        String path = TestUtils.takeScreenshot(driver);
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

    //This method will click on the Profile Icon
    public void clickOnTheUserProfileIcon() {

        String path = TestUtils.takeScreenshot(driver);

        driver.findElement(loginPage.UserProfileIcon).click();

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

    //This method will click on the Login link
    public void clickOnTheLoginLink() {

        driver.findElement(loginPage.LoginLink).click();

        String path = TestUtils.takeScreenshot(driver);
        try {
            ExtentManager.getTest().info(
                    "Login link Clicked",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
        } catch (Exception e) {
            ExtentManager.getTest().fail("Profile Icon not clicked");
        }
        Log.info("Clicked on the Login Link");

    }
    //This method will wait for the presence of email field
    public void waitFrPresenceOfEmail() {

        WaitUtils.waitExplicitlyForWebElementVisible(driver, loginPage.EmailAddressTxtBoxLogin);
        String path = TestUtils.takeScreenshot(driver);
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

    //This method will enter email id for login
    public void enterEmailAddForLogin() throws IOException{
        String emailaddress = ExcelUtils.readExcelData(0, 1, 1);
        driver.findElement(loginPage.EmailAddressTxtBoxLogin).sendKeys(emailaddress);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Email id entered in the field");
        Log.info("Email id entered in the email id field");

    }

    //This method will enter password for login
    public void enterPwdForLogin() throws IOException {
        String password = ExcelUtils.readExcelData(0, 1, 2);
        driver.findElement(loginPage.PasswordTxtBoxLogin).sendKeys(password);
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Password entered in the field");
        Log.info("Password entered in the email id field");
    }

    //This method will click on the Login button
    public void clickOnTheLoginButton() throws IOException {
        driver.findElement(loginPage.LoginButton).click();
        WaitUtils.waitImplicitly(driver, 3);
        String path = TestUtils.takeScreenshot(driver);
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

    //This method will click on the Profile link
    public void clickOnTheProfileLink() {
        driver.findElement(loginPage.UserProfileIcon).click();
        driver.findElement(loginPage.ProfileLink).click();
        String expectedHeading = TestUtils.getProperty("ExpectedHeading");
        String actualHeading = TestUtils.getTextFromElement(driver, loginPage.MyAccountHeader);
        String path = TestUtils.takeScreenshot(driver);
        try {
            TestUtils.assertEquals(actualHeading, expectedHeading, "My Account Page Opened");
            ExtentManager.getTest().pass("My Account Page Opened", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("My Account Page Opened");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("My Account Page Opened");
        }

    }
    //This method will validate the email id of the profile
    public void validateEmailId() throws IOException {
        String expectedEmailAdd = ExcelUtils.readExcelData(0, 1, 1);
        String actualEmailAdd = TestUtils.getTextFromElement(driver,loginPage.RegisteredEmailAddress);
        String path = TestUtils.takeScreenshot(driver);

        try {
            TestUtils.assertEquals(actualEmailAdd, expectedEmailAdd, "Email address is matching");
            ExtentManager.getTest().pass("Email address is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Email id is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Email address is not matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Email id is matching with the expected");
        }
    }
    //This method will validate the name of the profile
    public void validateName() throws IOException {
        String expected_name = ExcelUtils.readExcelData(0, 1, 3);
        String actual_name = TestUtils.getTextFromElement(driver, loginPage.RegisteredName);
        String path = TestUtils.takeScreenshot(driver);

        try {
            TestUtils.assertEquals(actual_name, expected_name, "Name is matching");
            ExtentManager.getTest().pass("Name is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Name is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Email address is not matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Name is matching with the expected");
        }
    }
    //This email id will validate the phone number of the profile
    public void validatePhoneNumber() throws IOException{
        String expected_name = ExcelUtils.readExcelData(0, 1, 4);
        String actual_name = TestUtils.getTextFromElement(driver, loginPage.RegisteredPhoneNumber);
        String path = TestUtils.takeScreenshot(driver);

        try {
            TestUtils.assertEquals(actual_name, expected_name, "Phone number is matching");
            ExtentManager.getTest().pass("Phone number is matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Phone number is matching with the expected");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Phone number is not matching", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Phone number is matching with the expected");
        }
    }

    //----------------------------------Testcase8--LogOut Steps----------------------------------------------//
    //This method will click on the Logout button
    public void clickOnTheLogOutButton() throws IOException{
            driver.findElement(loginPage.UserProfileIcon).click();
            driver.findElement(loginPage.LogOutLink).click();

            ExtentManager.getTest().info("Clicked on the Logout button");
            Log.info("Clicked on the Log Out button");
    }

    //This method will validate the logout functionality
    public void validateLogOut() throws IOException {
        String path = TestUtils.takeScreenshot(driver);
        try {
            driver.findElement(loginPage.UserProfileIcon).click();
            Assert.assertTrue(TestUtils.checkPresenceOfElement(driver, loginPage.LoginLink));
            ExtentManager.getTest().pass("LogOut Successfully", MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Log Out Successfully from the application");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Log Out is not successful", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Log Out failed");
        }
    }

    //--------------------------Testcase7---------Help Icon--------------------------------------------------------//

    //This method will scroll down to the last of the page to the phone icon
    public void scrolldowntillPhoneIcon() throws IOException {

        TestUtils.scrollUsingJS(driver,loginPage.PhoneIcon);

        String path=TestUtils.takeScreenshot(driver);
        try
        {
            ExtentManager.getTest().info("Scrolled down to the Phone Icon",MediaEntityBuilder.createScreenCaptureFromPath(path).build());

            Log.info("Scrolled down to the Phone Icon");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Didn't Scrolled down to the Phone Icon",MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Didn't Scrolled down to the Phone Icon");
        }


    }

    //This method will click on the Phone icon
    public void clickOnThePhoneIcon() throws IOException {

        driver.findElement(loginPage.PhoneIcon).click();
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

    }
}