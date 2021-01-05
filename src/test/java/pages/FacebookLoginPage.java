package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FacebookLoginPage extends BasicActions {

    @FindBy (xpath =  "//a[@title='English (US)']")
    private WebElement switchToEnglishLanguage;

    @FindBy ( name = "email")
    private WebElement email;

    @FindBy ( name = "pass")
    private WebElement password;

    @FindBy ( name = "login")
    private WebElement loginButton;

    @FindBy ( xpath = "//button[@data-testid='cookie-policy-banner-accept']")
    private WebElement acceptCookies;


    public FacebookLoginPage(WebDriver driver) {
        super( driver );
    }

    public FacebookWelcomePage successfulLogin (String email, String password) {
        clear(this.email, "email");
        sendKeys(this.email, email, "email");
        clear(this.password, "password");
        sendKeys(this.password, password, "password");
        click(acceptCookies, "acceptCookies");
        click(loginButton,"loginButton");
        return new FacebookWelcomePage (getDriver());
    }

    public FacebookIncorrectLoginPage unSuccessfulLogin (String email, String password) {
        clear(this.email, "email");
        sendKeys(this.email, email, "email");
        clear(this.password, "password");
        sendKeys(this.password, password, "password");
        click(acceptCookies, "acceptCookies");
        click(loginButton,"loginButton");
        return new FacebookIncorrectLoginPage (getDriver());
    }

    public void populateEmailAndPassword (String email, String password) {
        clear(this.email, "email"  );
        sendKeys(this.email, email, "email"  );
        clear( this.password, "password" );
        sendKeys( this.password, password, "password" );
    }

    public void clickOnAcceptCookies () {
        click(acceptCookies, "acceptCookies");
    }

}
