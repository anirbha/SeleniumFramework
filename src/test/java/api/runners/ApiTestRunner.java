package api.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/resources/features/api/CRUD.feature"},
        glue = {"api.stepdefinition","hooks"},
        plugin = {"pretty", "html:target/cucumber-reports/api-report.html"},
        tags = "@api"
)

public class ApiTestRunner {
}
