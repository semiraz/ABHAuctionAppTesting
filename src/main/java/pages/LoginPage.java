package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageObject {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "*[name='email']")
    private WebElement email;

    @FindBy(css = "*[name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='LOGIN']")
    private WebElement loginBtn;

    @FindBy(css = ".c-login-page")
    private WebElement loginPageElement;

    @FindBy(css = ".c-input-error")
    private WebElement inputError;

    @FindBy(css = ".c-error-message")
    private WebElement errorMsg;

    public void login(String email, String password) {
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        loginBtn.click();
    }

    public boolean isLoginPageOpen() {
        if (inputError == null) {
            return loginPageElement.isDisplayed();
        } else {
            return !inputError.isDisplayed();
        }
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }

    public boolean isErrorMsg() {
        return errorMsg.isDisplayed();
    }
}























