package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FacebookListOfPostsPage extends BasicActions {

    @FindBy (xpath = "//span[@dir='auto' and contains(.,'Delete')]")
    private WebElement deletePostMenuOption;

    String postTextP1 = "//div[@dir='auto']//div//div//div[contains(., '";
    String getPostTextP2 = "')]";

    String postMenuButtonP1 =  "(//div[@aria-label='Actions for this post'])[";
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
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        WebElement element = wait.until( ExpectedConditions.visibilityOfElementLocated(By.xpath(postMenuButtonP1 + orderOfPostFromTheTop + postMenuButtonP2)) );
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(postMenuButtonP1 + orderOfPostFromTheTop + postMenuButtonP2)));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        click(element, "post menu option");
        waitForVisibility(deletePostMenuOption, "deletePostMenuOption");
        click(deletePostMenuOption, "deletePostMenuOption");
        return new FacebookDeletePostPage(driver);
    }

}
