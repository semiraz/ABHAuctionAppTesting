package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Objects;

public class LandingPage extends PageObject {
    public LandingPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//a[text()='HOME']")
    private WebElement homeLink;

    @FindBy(css = ".c-search-field input")
    private WebElement searchBar;

    @FindBy(css = ".c-navbar-logo")
    private WebElement logo;

    @FindBy(css = ".c-main-product .c-info h1:first-child")
    private WebElement highlightedName;

    @FindBy(css = ".c-navbar-item")
    private List<WebElement> navbarOptionItems;
    @FindBy(css = ".c-item a h3")
    private List<WebElement> items;

    public void goToLandingPage() {
        driver.get("http://localhost:3000/");
    }

    public boolean verifyTitle(String title) {
        return driver.getTitle().contains(title);
    }

    public boolean isLogoPresent() {
        return logo.isDisplayed();
    }

    public void enterSearchedWord(String productName) {
        searchBar.sendKeys(productName, Keys.ENTER);
    }

    public boolean isHighlightedProductPresent() {
        return highlightedName.isDisplayed();
    }

    public void getNavbarItemsPage(String navbarOptionName) {
        Objects.requireNonNull(navbarOptionItems.stream().filter(p -> p.getText().equalsIgnoreCase(navbarOptionName))
                .findAny().orElse(null)).click();
    }

    public boolean isDisplayedBasedOnTimeLeftInAuction(String itemName) {
         WebElement itemFirst = items.get(0);
        return itemFirst.getText().equalsIgnoreCase(itemName);
    }

    public void clickOnItem(String itemName) {
        Objects.requireNonNull(items.stream().filter(predicate -> predicate.getText().equalsIgnoreCase(itemName))
                .findAny().orElse(null)).click();
    }


}
