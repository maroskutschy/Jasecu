package GlueCode;

import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.Eyes;
import cucumber.api.java.en.Given;
import org.openqa.selenium.By;

public class Applitools {

    Eyes eyes = GeneralStepDefinitions.getEyes();

    @Given("^I make Applitools Visual Test validation running via UltraFast Grid with test name: \"([^\"]*)\" and step name: \"([^\"]*)\"")
    public void takeSnapshotUltraFastGrid(String testName, String stepName) {
        eyes.open(GeneralStepDefinitions.getDriver(), "AppliFashion", testName, new RectangleSize(800, 600));
        eyes.checkWindow(stepName);
        eyes.closeAsync();
    }

    @Given("^I make Applitools Visual Test validation of region: \"([^\"]*)\" running via UltraFast Grid with test name: \"([^\"]*)\" and step name: \"([^\"]*)\"")
    public void takeSnapshotUltraFastGridRegion(String region, String testName, String stepName) {
        eyes.open(GeneralStepDefinitions.getDriver(), "AppliFashion", testName, new RectangleSize(800, 600));
        eyes.checkRegion(By.id(region),stepName);
        eyes.closeAsync();
    }

}
