package runner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/features"},
        glue = {"hooks",
                "step_definitions" },
//        tags = {""},
        plugin = {"pretty", "html:latestreports/cucumber-htmlreports", "json:latestreports/cucumber.json"}
)

public class TestRunner {

}
