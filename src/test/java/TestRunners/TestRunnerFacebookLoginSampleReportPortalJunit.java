package TestRunners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"Utils.ReportPortalScenarioReporterNoSuite" }, features = "classpath:Feature_Files/Facebook/Login_Sample", glue={"GlueCode"})
public class TestRunnerFacebookLoginSampleReportPortalJunit {
}
