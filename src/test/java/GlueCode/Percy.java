package GlueCode;

import cucumber.api.java.en.Given;

public class Percy {

    @Given("^I make Percy Visual Test validation with screenshot name: \"([^\"]*)\"")
    public void takeSnapshot(String screenshotName) {
        io.percy.selenium.Percy percy = new io.percy.selenium.Percy(GeneralStepDefinitions.getDriver());
        percy.snapshot(screenshotName);
    }
}
