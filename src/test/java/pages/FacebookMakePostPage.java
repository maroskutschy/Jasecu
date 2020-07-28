package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookMakePostPage extends BasicActions {

    @FindBy (xpath = "//div[@data-block='true']")
    private WebElement postTextArea;

    @FindBy (xpath = "//button[@type='submit']//span[contains(., 'Post')]")
    private WebElement postButton;

    public FacebookMakePostPage(WebDriver driver) {
        super(driver);
    }

    public FacebookListOfPostsPage makePost (String text) {
        waitForVisibility(postTextArea, "postTextArea");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        sendKeysViaActionsClass(postTextArea, text, "postTextArea");
        click(postButton, "postButton");
        return new FacebookListOfPostsPage(driver);
    }


}
