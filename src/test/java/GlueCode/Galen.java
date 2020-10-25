package GlueCode;

import GlueCode.GeneralStepDefinitions;
import com.galenframework.reports.GalenTestInfo;
import com.galenframework.reports.HtmlReportBuilder;
import com.galenframework.reports.model.LayoutReport;
import cucumber.api.java.en.And;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static com.galenframework.api.Galen.checkLayout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.BasicActions;


public class Galen {

    WebDriver driver = GeneralStepDefinitions.getDriver();
    String scenarioName = GeneralStepDefinitions.getScenarioName();
    public static final Logger LOGGER = LoggerFactory.getLogger( BasicActions.class );

    @And("^I validate page using Galen Framework \"([^\"]*)\" file$")
    public void validatePageUsingGalenSpecFile(String fileName) throws Throwable {

        LayoutReport layoutReport = checkLayout( driver, "/specs/" + fileName+ ".gspec", Arrays.asList( "desktop"));

        //Create a tests list
        List<GalenTestInfo> tests = new LinkedList<GalenTestInfo>();

        //Create a GalenTestInfo object
        GalenTestInfo test = GalenTestInfo.fromString(scenarioName + " layout");

        //Get layoutReport and assign to test object
        test.getReport().layout(layoutReport, "check " + scenarioName +  " layout");

        //Add test object to the tests list
        tests.add(test);

        //Create a htmlReportBuilder object
        HtmlReportBuilder htmlReportBuilder = new HtmlReportBuilder();

        //Create a report under /target folder based on tests list
        htmlReportBuilder.build(tests, "target//Galen//" + scenarioName + "-" + fileName + "//galen_report");

        //If layoutReport has errors Assert Fail
        if (layoutReport.errors() > 0)
        {
            Assert.fail( "Galen Layout test of scenario: " + scenarioName + "-" + fileName + " failed, see report here: " + "file:///" + System.getProperty( "user.dir") + "\\target\\Galen\\" + scenarioName + "-" + fileName + "\\galen_report\\report.html");
            //Assert.fail( "Galen Layout test of scenario: " + scenarioName + " failed, see report here: " + "<a href=\"file:///" + System.getProperty("user.dir") + "\\target\\" + scenarioName + "\\galen_report\\report.html\">Galen report</a>");

        } else {
            LOGGER.info( "Galen Layout test of scenario: " + scenarioName + "-" + fileName + " PASSED, see report here: " + "file:///" + System.getProperty( "user.dir") + "\\target\\Galen\\" + scenarioName + "-" + fileName + "\\galen_report\\report.html" );
        }

    }

}
