package Pages;

import Base.BaseTest;
import Utils.TestUtils;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Objects;

public class LoginPage extends BaseTest {
    WebDriver driver;
    String url;

    public void driversetup()
    {
        driver=setUp();

    }

    public void launchUrl() throws IOException {

        url=TestUtils.getProperty("URL");
        TestUtils.LaunchUrl(driver,url);

    }

    public void clickOnTheProfileIcon() throws IOException, ParseException {

        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("UserProfileIcon"));


    }





}
