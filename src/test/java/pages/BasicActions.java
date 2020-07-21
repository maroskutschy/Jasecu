package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicActions {

    // Time out in seconds
    public static final int TIME_OUT = 12;

    public WebDriver driver;

    public WebDriverWait wait;

    public static final Logger LOGGER = LoggerFactory.getLogger(BasicActions.class);

    public BasicActions(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements( new AjaxElementLocatorFactory( driver, TIME_OUT ), this );
        wait = new WebDriverWait( driver, TIME_OUT );
    }


    public void checkElementNotPresent (WebElement element , String nameOfElement) throws InterruptedException {
        LOGGER.info("checking not presence of: " + nameOfElement + ".");
        waitForInVisibility(element, nameOfElement);
        LOGGER.info( "Done." );
    }

    public void checkElementNotPresent (String xpath , String nameOfElement) throws InterruptedException {
        LOGGER.info("checking not presence of: " + nameOfElement + ".");
        wait.until( ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)));
        LOGGER.info( "Done." );
    }

    public void checkElementPresent (WebElement element , String nameOfElement) throws InterruptedException {
        LOGGER.info("checking presence of: " + nameOfElement + ".");
        waitForVisibility(element, nameOfElement);
        LOGGER.info( "Done." );
    }

    public void checkElementPresent (String xpath , String nameOfElement) throws InterruptedException {
        WebElement element = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)) );
        checkElementPresent(element, nameOfElement);
    }

    public void clear (WebElement element, String elementName) {
        LOGGER.info("Clearing Element : " + elementName + ".");
        element.clear();
        LOGGER.info("Done");
    }

    public void click (WebElement element, String elementName) {
        LOGGER.info("Clicking on Element : " + elementName + ".");
        element.click();
        LOGGER.info("Done");
    }

    public void click (String xpath, String elementName) {
        WebElement element = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)) );
        click(element, elementName);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void sendKeys (WebElement element, String fillWith, String elementName) {
        LOGGER.info("Sending keys: " + fillWith +  " into element : " + elementName + ".");
        element.sendKeys(fillWith);
        LOGGER.info( "Done." );
    }

    public void sendKeysViaActionsClass (WebElement element, String fillWith, String elementName) {
        LOGGER.info("Sending keys via Actions class: " + fillWith +  " into element : " + elementName + ".");
        Actions actions = new Actions(driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(fillWith);
        actions.build().perform();
        LOGGER.info( "Done." );
    }

    public void waitForInVisibility(WebElement element, String elementName) {
        LOGGER.info("Waiting for invisibility of element : " + elementName + ".");
        wait.until( ExpectedConditions.invisibilityOf(element) );
        LOGGER.info( "Done." );
    }

    public void waitForInVisibility(String xpath, String elementName) {
        LOGGER.info("Waiting for invisibility of element : " + elementName + ".");
        wait.until( ExpectedConditions.invisibilityOfElementLocated(By.xpath(xpath)) );
        LOGGER.info( "Done." );
    }

    public void waitForVisibility(WebElement element, String elementName) {
        LOGGER.info("Waiting for visibility of element : " + elementName + ".");
        wait.until( ExpectedConditions.visibilityOf(element) );
        LOGGER.info( "Done." );
    }

}
