package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonBarPage extends PageObject{

    @FindBy(xpath = "//a[text()='SHOP']")
    private WebElement shopLink;

    @FindBy(xpath = "//a[text()='MY ACCOUNT']")
    private WebElement accountLink;

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


}
