package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

    public void createAnAccount(String firstName, String lastName,String email, String password) {
        this.firstName.sendKeys(firstName);
        this.lastName.sendKeys(lastName);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        registerBtn.click();
    }
    
}







