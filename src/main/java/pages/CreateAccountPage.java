package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountPage extends PageObject {

    public CreateAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "*[name='firstName']")
    private WebElement firstName;

    @FindBy(css = "*[name='lastName']")
    private WebElement lastName;

    @FindBy(css = "*[name='email']")
    private WebElement email;

    @FindBy(css = "*[name='password']")
    private WebElement password;

    @FindBy(xpath = "//button[text()='REGISTER']")
    private WebElement registerBtn;

    @FindBy(css = ".c-error-message")
    private WebElement errorMsg;

    @FindBy(css = ".c-input-error")
    private WebElement inputErrorMsg;

    public boolean verifyPassword(String password) {
        String regEx = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*&])[A-Za-z\\d@$!%*&]{8,}$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean verifyEmail(String email) {
        String regEx = "([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

//    public boolean isErrorMsgDisplayed() {
//        return errorMsg.isDisplayed();
//    }
//
//    public boolean isInputErrorMsgDisplayed() {
//        return inputErrorMsg.isDisplayed();
//    }

    public void createAnAccount(String firstName, String lastName,String email, String password) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        registerBtn.click();
    }
}