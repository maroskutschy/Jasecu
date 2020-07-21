package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookListOfPostsPage extends BasicActions {

    @FindBy (xpath = "//div[@class='uiContextualLayerPositioner uiLayer']//li[contains(@class,'MenuItem')]//a//span//span[contains(.,'Delete')]")
    private WebElement deletePostMenuOption;

    String postTextP1 = "//div[@role='article']//div[contains(@class,'userContentWrapper')]//div[contains(@class,'userContent')]/div[contains(.,'";
    String getPostTextP2 = "')]";

    String postMenuButtonP1 =  "(//a[@data-testid='post_chevron_button'])[";
    String postMenuButtonP2 = "]";

    public FacebookListOfPostsPage(WebDriver driver) {
        super(driver);
    }

    public FacebookListOfPostsPage validatePostText(String statusOfPost, String postText) throws InterruptedException {
        switch (statusOfPost) {
            case "saved":
                checkElementPresent(postTextP1 + postText + getPostTextP2, "Post with text: " + postText + ".");
                break;
            case "deleted":
                checkElementNotPresent(postTextP1 + postText + getPostTextP2, "Post with text: " + postText + ".");
        }
        return this;
    }

    public FacebookDeletePostPage deletePost (String orderOfPostFromTheTop) {
        click(postMenuButtonP1 + orderOfPostFromTheTop + postMenuButtonP2, "postMenuButton");
        waitForVisibility(deletePostMenuOption, "deletePostMenuOption");
        click(deletePostMenuOption, "deletePostMenuOption");
        return new FacebookDeletePostPage(driver);
    }

}
