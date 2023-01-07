package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonBarPage extends PageObject{

    @FindBy(xpath = "//a[text()='SHOP']")
    private WebElement shopLink;

    @FindBy(xpath = "//a[text()='MY ACCOUNT']")
    private WebElement accountLink;

    @FindBy(xpath = "//a[text()='Create Account']")
    private WebElement createAccountBtn;

    @FindBy(xpath = "//a[text()='Login']")
    private WebElement loginBtn;

    @FindBy(xpath = "(//button[text()='Logout'])[1]")
    private WebElement logout;

    public CommonBarPage(WebDriver driver) {
        super(driver);
    }

    public ShopPage goToShopPage() {
        shopLink.click();
        return new ShopPage(driver);
    }

    public MyAccountPage goToMyAccountPage() {
        accountLink.click();
        return new MyAccountPage(driver);
    }

    public CreateAccountPage goToCreateAccountPage() {
        createAccountBtn.click();
        return new CreateAccountPage(driver);
    }

    public LoginPage goToLoginPage() {
        loginBtn.click();
        return new LoginPage(driver);
    }

    public LandingPage logout(){
        logout.click();
        return new LandingPage(driver);
    }

}
