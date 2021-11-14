package TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty" },
		features = "src/test/resources/Feature_Files/Facebook/Galen",
		glue={"GlueCode"},
		format = {"html:target/site/cucumber-pretty", "json:target/cucumber.json", "rerun:rerun.txt"})
public class TestRunnerFacebookGalenJunit {

		@BeforeClass
		public static void beforeClass() {
		TestDefaultValues.setOperatingSystem(System.getProperty("operatingSystem"));
		TestDefaultValues.setBrowser( System.getProperty("browser"));
		TestDefaultValues.setLink( System.getProperty("link" ));
		TestDefaultValues.setUser( System.getProperty("user" ));
		TestDefaultValues.setPassword( System.getProperty("password"));
		}

}
