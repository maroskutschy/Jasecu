package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookIncorrectLoginPage extends BasicActions {

    @FindBy (xpath = "//div[@class='uiContextualLayer uiContextualLayerRight']//div//div")
    private WebElement incorrectPasswordMessage;

    public FacebookIncorrectLoginPage(WebDriver driver) {
        super(driver);
    }

    public FacebookIncorrectLoginPage checkIncorrectPasswordMessage (String expectedMessage) throws InterruptedException {
        checkElementPresent(incorrectPasswordMessage, "incorrectPasswordMessage");
        String actualMessage = incorrectPasswordMessage.getText();
        LOGGER.info("Checking message: actual message is: '" + actualMessage + "', expected message is: '" + expectedMessage + "'.");
        Assert.assertTrue("Message is not correct: actual message is: '" + actualMessage + "', expected message is: '" + expectedMessage + "'.", actualMessage.contentEquals(expectedMessage));
        return this;
    }



}
