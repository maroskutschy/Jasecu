package GlueCode;

import TestRunners.TestDefaultValues;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.HashMap;
import java.util.Map;

public class GeneralStepDefinitions {

    public static WebDriver driver;
    private String operatingSystem = TestDefaultValues.getOperatingSystem();
    private String browser = TestDefaultValues.getBrowser();
    private String link = TestDefaultValues.getLink();

    @Before
    public void openBrowserWithLink() throws Throwable {

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
                // DISABLE CHROME NOTIFICATIONS:
                // Create object of HashMap Class
                Map<String, Object> prefs = new HashMap<String, Object>();
                // Set the notification setting it will override the default setting
                prefs.put("profile.default_content_setting_values.notifications", 2);
                // Create object of ChromeOption class
                ChromeOptions options = new ChromeOptions();
                // Set the experimental option
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
                break;
        }
        driver.get(link);

    }

    @After
    public void closeBrowser() {
       driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
