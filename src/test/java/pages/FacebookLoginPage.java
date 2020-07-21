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

    @FindBy ( xpath = "//input[@data-testid='royal_login_button']")
    private WebElement loginButton;

    public FacebookLoginPage(WebDriver driver) {
        super( driver );
    }

    public FacebookWelcomePage successfulLogin (String email, String password) {
        clear(this.email, "email");
        sendKeys(this.email, email, "email");
        clear(this.password, "password");
        sendKeys(this.password, password, "password");
        click(loginButton,"loginButton");
        return new FacebookWelcomePage (getDriver());
    }

    public FacebookIncorrectLoginPage unSuccessfulLogin (String email, String password) {
//        Actions builder = new Actions( driver );
//        builder.moveToElement( switchToEnglishLanguage ).click().build().perform();
//        click(switchToEnglishLanguage, "switchToEnglishLanguage");
        clear(this.email, "email");
        sendKeys(this.email, email, "email");
        clear(this.password, "password");
        sendKeys(this.password, password, "password");
        click(loginButton,"loginButton");
        return new FacebookIncorrectLoginPage (getDriver());
    }

}
