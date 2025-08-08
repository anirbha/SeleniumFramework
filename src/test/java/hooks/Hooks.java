package hooks;

import Base.BaseTest;
import Base.DriverManager;
import Utils.ExtentManager;
import Utils.TestUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks extends BaseTest {

    @Before
    public void setUp(Scenario scenario) {

        ExtentManager.createTest(scenario.getName());
    }

    @After
    public void tearDown(Scenario scenario) {

        DriverManager.quitDriver();
        ExtentManager.getInstance().flush();
    }

}
