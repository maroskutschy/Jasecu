package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookDeletePostPage extends BasicActions {

    @FindBy (xpath = "//button[contains(.,'Delete Post')]")
    private WebElement deletePostButton;

    private String deletePostButtonXpath = "//button[contains(.,'Delete Post')]";

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
