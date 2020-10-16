package GlueCode;

import TestRunners.TestDefaultValues;
import com.applitools.eyes.BatchInfo;
import com.applitools.eyes.TestResults;
import com.applitools.eyes.selenium.BrowserType;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.visualgrid.model.DeviceName;
import com.applitools.eyes.visualgrid.model.ScreenOrientation;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.HashMap;
import java.util.Map;

public class GeneralStepDefinitions {

    public static WebDriver driver;
    private String operatingSystem = TestDefaultValues.getOperatingSystem();
    private String browser = TestDefaultValues.getBrowser();
    private String link = TestDefaultValues.getLink();
    public static Eyes eyes;
    public static VisualGridRunner visualGridRunner;
    public static BatchInfo batch;
    private static String scenarioName;


    @Before
    public void openBrowserWithLink(Scenario scenario) throws Throwable {

        if (!scenario.getName().contains("Galen")) {
            scenarioName = scenario.getName().replaceAll( " ", "_" );
        }

        Map<String, Object> prefs = new HashMap<String, Object>();
        // Set the notification setting it will override the default setting
        prefs.put("profile.default_content_setting_values.notifications", 2);
        // Create object of ChromeOption class
        ChromeOptions options = new ChromeOptions();
        // Set the experimental option
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-extensions");
        options.addArguments("--disabled-new-style-notification");
        options.addArguments("--allow-silent-push");
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);

        switch (browser) {
            case "Firefox" :
                if (operatingSystem.equalsIgnoreCase("Windows")) {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/"+ operatingSystem +"/geckodriver.exe");
                } else {
                    System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/" + operatingSystem + "/geckodriver");
                }
                driver = new FirefoxDriver();
                break;
            case "Chrome" :
                if (operatingSystem.equalsIgnoreCase("Windows")) {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/" + operatingSystem + "/chromedriver.exe");
                } else {
                    System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/" + operatingSystem + "/chromedriver");
                }

                driver = new ChromeDriver(options);
                break;
        }
        driver.get(link);
        if (scenario.getName().contains("Applitools")) {
            batch = new BatchInfo(scenario.getName().replace(" - Applitools", ""));
            visualGridRunner = new VisualGridRunner(10);
            eyes = new Eyes(visualGridRunner);
            // Initialize eyes Configuration
            Configuration config = new Configuration();
            // You can get your api key from the Applitools dashboard
            config.setApiKey("YOUR_APPLITOOLS_EYES_API_KEY");
            config.setBatch(batch);
            // Add browsers with different viewports
            config.addBrowser(1200, 700, BrowserType.CHROME);
            config.addBrowser(1200, 700, BrowserType.FIREFOX);
            config.addBrowser(1200, 700, BrowserType.EDGE_CHROMIUM);
            config.addBrowser(768, 700, BrowserType.CHROME);
            config.addBrowser(768, 700, BrowserType.FIREFOX);
            config.addBrowser(768, 700, BrowserType.EDGE_CHROMIUM);
            // Add mobile emulation devices in Portrait mode
            config.addDeviceEmulation(DeviceName.iPhone_X, ScreenOrientation.PORTRAIT);
            //config.addDeviceEmulation(DeviceName.Pixel_2, ScreenOrientation.PORTRAIT);
            // Set the configuration object to eyes
            eyes.setConfiguration(config);
        }

    }

    @After
    public void closeBrowser(Scenario scenario) {

        driver.quit();
        if (scenario.getName().contains("Applitools")) {
            try {
                //Choose if a difference in screenshot should fail your test, e.g. false will not fail your test
                TestResults result = eyes.close(true);
                String resultStr;
                String url;
                if (result == null) {
                    resultStr = "Test aborted";
                    url = "undefined";
                } else {
                    url = result.getUrl();
                    int totalSteps = result.getSteps();
                    if (result.isNew()) {
                        resultStr = "New Baseline Created: " + totalSteps + " steps";
                    } else if (result.isPassed()) {
                        resultStr = "All steps passed:     " + totalSteps + " steps";
                    } else {
                        resultStr = "Test Failed     :     " + totalSteps + " steps";
                        resultStr += " matches=" +  result.getMatches();      /*  matched the baseline */
                        resultStr += " missing=" + result.getMissing();       /* missing in the test*/
                        resultStr += " mismatches=" + result.getMismatches(); /* did not match the baseline */
                    }
                }
                resultStr += "\n" + "results at " + url;
                System.out.println(resultStr);
            } finally {
                eyes.abortIfNotClosed();
            }
        }
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static Eyes getEyes() {
        return eyes;
    }
    public static String getScenarioName() {
        return scenarioName;
    }

    @Given("^And pause for \"([^\"]*)\" seconds$")
    public void pause(String numberOfSeconds) throws Throwable {
        Thread.sleep(Integer.parseInt(numberOfSeconds));
    }
}
