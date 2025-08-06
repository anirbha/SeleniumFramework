package runners;
import Utils.ExtentManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/features/WishList.feature",
                    },
        glue = {"StepDefinitions","hooks"},
        tags = "",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests{
    @Before
    public void setup() {
        ExtentManager.getInstance();
    }

    @After
    public void tearDown() {
        ExtentManager.getInstance().flush();
    }
}
