package hooks;

import ui.Base.DriverManager;
import ui.Utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

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
