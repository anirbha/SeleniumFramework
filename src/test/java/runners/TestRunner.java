package runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/resources/features/Login.feature"},
        glue = {"StepDefinitions","hooks"},
        tags = "@LogOut",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class TestRunner extends AbstractTestNGCucumberTests{

}
