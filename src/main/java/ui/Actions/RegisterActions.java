package ui.Actions;



import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import ui.Utils.*;
import ui.pages.RegisterPage;
import java.io.IOException;

public class RegisterActions {

    private WebDriver driver;

    RegisterPage registerPage = new RegisterPage();

    public RegisterActions(WebDriver driver) {
        this.driver = driver;
    }

    //This method will click the Sign up link present under profile icon
    public void clickOnTheSignUpLink() {

        driver.findElement(registerPage.SignUpLink).click();
        String path = TestUtils.takeScreenshot(driver);
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

    // This method will fetch email id from excel and enter it in the email id textbox
    public void enterEmailAddForRegister()  {

        WaitUtils.waitExplicitlyForWebElementVisible(driver,registerPage.EmailAddressTxtBoxRegister);

        try{
            String emailaddress= ExcelUtils.readExcelData(0,1,1);

            driver.findElement(registerPage.EmailAddressTxtBoxRegister).sendKeys(emailaddress);

            ExtentManager.getTest().info("Email id entered in the field");

            Log.info("Email id entered in the email id field");
         } catch (Exception e) {

            ExtentManager.getTest().fail("Email id not entered in the field");

            Log.info("Email id not entered in the email id field");

        }

    }

    //This method will fetch password from excel and enter it in the password textbox
    public void enterPwdForRegister()  {

        try{
            String password= ExcelUtils.readExcelData(0,1,2);

            driver.findElement(registerPage.PasswordTxtBoxRegister).sendKeys(password);

        } catch (IOException e) {

            ExtentManager.getTest().fail("Exception occurred while getting value from excel"+e);
        }

        String path=TestUtils.takeScreenshot(driver);

        ExtentManager.getTest().info("Password entered in the field",MediaEntityBuilder.createScreenCaptureFromPath(path).build());

        Log.info("Password entered in the email id field");
    }
    //This method will click on the Sign up button
    public void clickOnTheSignUpButton() {

        driver.findElement(registerPage.SignUpButton).click();

        WaitUtils.waitImplicitly(driver, 3);

        String path = TestUtils.takeScreenshot(driver);

        try {
            ExtentManager.getTest().info(
                    "Sign Up Done",
                    MediaEntityBuilder.createScreenCaptureFromPath(path).build()
            );
            Log.info("Sign Up Done");
        } catch (Exception e) {
            ExtentManager.getTest().fail("Sign up is not successful", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
            Log.error("Sign Up not successful");
        }
    }
    //This method will Click on the Edit button
    public void clickOnTheEditBtn() {
        driver.findElement(registerPage.EditButton).click();

        WaitUtils.waitImplicitly(driver, 3);

        String path = TestUtils.takeScreenshot(driver);
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
    //This method will enter Name
    public void enterNameForEdit() throws IOException {
        String name=ExcelUtils.readExcelData(0,1,3);

        driver.findElement(registerPage.NameTextBox).sendKeys(name);

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
    //This method will enter Phone number
    public void enterPhNumberForEdit() throws IOException {
        String phonenumber=ExcelUtils.readExcelData(0,1,4);

        driver.findElement(registerPage.MobileNumberTextBox).sendKeys(phonenumber);

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
    //This method will click on the update button
    public void clickOnTheUpdateBtn() {
        driver.findElement(registerPage.UpdateButton).click();
        TestUtils.takeScreenshot(driver);
        ExtentManager.getTest().info("Update button clicked");
        Log.info("Update button clicked");

    }

}
