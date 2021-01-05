package GlueCode;

import TestRunners.TestDefaultValues;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.*;

import java.util.Map;

public class FacebookStepDefinitios {

    private String email = TestDefaultValues.getUser();
    private String password = TestDefaultValues.getPassword();
    private WebDriver driver = GeneralStepDefinitions.getDriver();
    private FacebookLoginPage facebookLoginPage;
    private FacebookWelcomePage facebookWelcomePage;
    private FacebookIncorrectLoginPage facebookIncorrectLoginPage;
    private FacebookMakePostPage facebookMakePostPage;
    private FacebookListOfPostsPage facebookListOfPostsPage;
    private FacebookDeletePostPage facebookDeletePostPage;
    private FacebookWelcomePage facebookWellcomePage;

    @Given ("^I delete following post from the top:$")
    public void deleteThePostWithText(DataTable orderOfPosts) throws Throwable {
        for (Map<String, String> data : orderOfPosts.asMaps(String.class, String.class)) {
            facebookListOfPostsPage = new FacebookListOfPostsPage(driver);
            facebookDeletePostPage = facebookListOfPostsPage.deletePost(data.get("Order of post"));
            facebookDeletePostPage.deletePost();
        }
    }

    @Given ("I login to facebook with default credentials")
    public void loginToFacebookWithDefaultCredentials() throws Throwable {
        facebookLoginPage = new FacebookLoginPage(driver);
        facebookWelcomePage = facebookLoginPage.successfulLogin(email, password);
    }

    @Given ("^I successfully login to facebook with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void loginToFacebookWithCredentials(String email, String password) throws Throwable {
        facebookLoginPage = new FacebookLoginPage(driver);
        facebookWelcomePage = facebookLoginPage.successfulLogin(email, password);
    }

    @Given ("^I make the post with text:$")
    public void makeThePostWithText(DataTable textsOfPosts) throws Throwable {
        for (Map<String, String> data : textsOfPosts.asMaps(String.class, String.class)) {
            facebookMakePostPage = facebookWelcomePage.openMakePostPage();
            facebookListOfPostsPage = facebookMakePostPage.makePost(data.get("Text of the post"));
        }
    }

    @Given ("^I unsuccessfully login to facebook with email \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void unsuccessfulLoginToFacebookWithCredentials(String email, String password) throws Throwable {
        facebookLoginPage = new FacebookLoginPage(driver);
        facebookIncorrectLoginPage = facebookLoginPage.unSuccessfulLogin(email, password);
    }

    @Given ("^Facebook Welcome Page is successfully displayed$")
    public void validateFacebookWelcomePage() throws Throwable {
        facebookWelcomePage.validateFacebookWelcomePage();
    }

    @Given ("^Because of \"([^\"]*)\" Incorrect Login Page is displayed with following message \"([^\"]*)\"$")
    public void validateFacebookIncorrectLoinPageMessage(String reason, String message) throws Throwable {
        facebookIncorrectLoginPage.checkIncorrectPasswordMessage(message);
    }

    @Given ("^The post with following text is successfully (saved|deleted):$")
    public void ValidateSavedFacebookPost(String statusOfPost, DataTable textsOfPosts) throws Throwable {
        for (Map<String, String> data : textsOfPosts.asMaps(String.class, String.class)) {
            facebookListOfPostsPage.validatePostText(statusOfPost, data.get("Text of the post"));
        }
    }

    @Given ("^switch to News Feed Most Recent option$")
    public void swithToNewsFeedMostRecentOption() throws Throwable {
        facebookWelcomePage.swithToNewsFeedMostRecentOption();
    }

    @When("^I populate values: email \"([^\"]*)\" , password \"([^\"]*)\"$")
    public void populateEmailAndPassword(String email, String password) throws Throwable {
        //facebookLoginPage = new FacebookLoginPage(driver  );
        facebookLoginPage.populateEmailAndPassword(email, password  );
    }

    @Given ("^I click on accept cookies$")
    public void clickOnAcceptCookies() throws Throwable {
        facebookLoginPage = new FacebookLoginPage(driver);
        facebookLoginPage.clickOnAcceptCookies();
    }

}
