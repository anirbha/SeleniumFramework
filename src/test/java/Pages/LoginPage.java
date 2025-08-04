package Pages;

import Base.BaseTest;
import Utils.TestUtils;
import org.apache.logging.log4j.Logger;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Objects;

public class LoginPage extends BaseTest {

   Logger logger= TestUtils.logger;






    public void clickOnTheLoginLink() throws IOException, ParseException {
//        driverReference();
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LoginLink"));
        TestUtils.waitImplicitly(driver,3);
        TestUtils.takeScreenshot(driver);

    }


    public void clickOnTheLoginButton() throws IOException, ParseException {
        TestUtils.ClickOnTheElement(driver,(String) Objects.requireNonNull(TestUtils.ReadJsonData()).get("LoginButton"));
        TestUtils.waitImplicitly(driver,3);
        TestUtils.takeScreenshot(driver);

    }
}
