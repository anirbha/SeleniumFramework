package runners;
import Utils.ExtentManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(
        features = "src/test/java/features",
        glue = {"StepDefinitions"},
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests{
    @BeforeSuite
    public void setup() {
        ExtentManager.getInstance();
    }

    @AfterSuite
    public void tearDown() {
        ExtentManager.getInstance().flush();
    }
}
