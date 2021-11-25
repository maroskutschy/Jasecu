package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookMakePostPage extends BasicActions {

    @FindBy (xpath = "//div[@role='presentation']//div[@data-block='true']")
    private WebElement postTextArea;

    @FindBy (xpath = "(//div[contains(., 'Post') and @role='button' and @tabindex=0])[2]")
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
        sendKeys(postTextArea, text, "postTextArea");
        click(postButton, "postButton");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new FacebookListOfPostsPage(driver);
    }


}
