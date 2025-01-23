import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/java/resources"},
        glue = {"org.example","RestAssured"},
        monochrome = true, tags = "@CreationTest or @GetResultByPassingId or @CheckCreatedIdIsPresent or @GetResult or @NegativeTest",
        plugin = {"pretty"
        })
public class cucumberRunner
{

}
