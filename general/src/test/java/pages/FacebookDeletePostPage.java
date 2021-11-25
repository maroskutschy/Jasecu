package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookDeletePostPage extends BasicActions {

    @FindBy (xpath = "//div[@aria-label='Delete']//span[contains(.,'Delete') and @dir='auto']")
    private WebElement deletePostButton;

    private String deletePostButtonXpath = "//button[contains(.,'Delete')]";

    public FacebookDeletePostPage(WebDriver driver) {
        super(driver);
    }

    public FacebookDeletePostPage deletePost () {
        waitForVisibility(deletePostButton, "deletePostButton");
        click(deletePostButton, "deletePostButton");
        waitForInVisibility(deletePostButtonXpath, "deletePostButton");
        return this;
    }

}
