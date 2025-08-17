package ui.runners;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/java/resources/features/ui/Order.feature"},
        glue = {"ui/StepDefinitions","hooks"},
        tags = "",
        plugin = {"pretty", "html:target/cucumber-report.html"}
)

public class UITestRunner extends AbstractTestNGCucumberTests{

}
