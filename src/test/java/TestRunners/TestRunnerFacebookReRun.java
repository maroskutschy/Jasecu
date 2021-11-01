package TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

@CucumberOptions(
		features = "@rerun.txt",
		//features = "src/test/resources/Feature_Files",
		//features = "classpath:",
		//features = "classpath:Feature_Files/Facebook",
		glue={"GlueCode"},
		monochrome = true,
		format = {"pretty", "html:target/site/cucumber-pretty", "json:target/cucumber.json"}
)

public class TestRunnerFacebookReRun extends AbstractTestNGCucumberTests  {

	@Parameters ({"operatingSystem", "browser", "link", "user", "password"})
	@BeforeClass
	public void beforeClass (String operatingSystem, String browser, String link, String user, String password) {

		TestDefaultValues.setOperatingSystem( operatingSystem );
 		TestDefaultValues.setBrowser( browser );
		TestDefaultValues.setLink( link );
		TestDefaultValues.setUser( user );
		TestDefaultValues.setPassword( password );
	}

}
