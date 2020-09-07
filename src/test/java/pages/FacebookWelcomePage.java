package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FacebookWelcomePage extends BasicActions {

    @FindBy (xpath = "//a[@data-testid='left_nav_item_Welcome']//div[contains(., 'Welcome')]")
    private WebElement welcomeLabel;

    @FindBy (xpath = "//div[@role='feed']")
    private WebElement newsFeed;

    @FindBy (xpath = "//span[contains(., 'on your mind')]")

    private WebElement makePostButton;

    @FindBy (xpath = "//a[@aria-label='News Feed Sort options']")
    private WebElement newsFeedThreeDotsMenu;

    @FindBy (xpath = "//span[contains(text(),'Most Recent')]")
    private WebElement mostRecentOption;

    public FacebookWelcomePage(WebDriver driver) {
        super( driver );
    }

    public FacebookWelcomePage validateFacebookWelcomePage () throws InterruptedException {
        checkElementPresent(newsFeed, "newsFeed");
        return this;
    }

    public FacebookMakePostPage openMakePostPage () {
        waitForVisibility(makePostButton, "makePostButton");
        click(makePostButton, "makePostButton");
        return new FacebookMakePostPage(driver);
    }

    public FacebookWelcomePage swithToNewsFeedMostRecentOption() {
        waitForVisibility(newsFeedThreeDotsMenu, "newsFeedThreeDotsMenu");
        click(newsFeedThreeDotsMenu, "newsFeedThreeDotsMenu");
        waitForVisibility(mostRecentOption, "mostRecentOption");
        click(mostRecentOption, "mostRecentOption");
        return this;
    }

}
