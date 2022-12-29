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
        productPage = new ProductPage(driver);
    }

    @FindBy(xpath = "//a[text()='HOME']")
    private WebElement homeLink;

    @FindBy(css = ".c-search-field input")
    private WebElement searchBar;

    @FindBy(css = ".c-navbar-logo a")
    private WebElement logo;

    @FindBy(css = ".c-main-product .c-info h1:first-child")
    private WebElement highlightedName;

    @FindBy(css = ".c-navbar-item")
    private List<WebElement> navbarOptionItems;
    @FindBy(css = ".c-navbar-item.c-focus")
    private WebElement focusNavbarItem;
    @FindBy(css = ".c-item a h3")
    private List<WebElement> itemsName;

    @FindBy(css = ".c-item span")
    private List<WebElement> itemsPrice;

    public List<WebElement> getItemsPrice() {
        return itemsPrice;
    }

    public List<WebElement> getItemsName() {
        return itemsName;
    }

    @FindBy(css = ".c-item")
    private List<WebElement> items;

    protected ProductPage productPage;

    public void goToLandingPage() {
        driver.get("http://ec2-3-67-80-227.eu-central-1.compute.amazonaws.com:8090/");
    }

    public boolean isNavbarItemSelected() {
        return focusNavbarItem.isEnabled();
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
        WebElement itemFirst = itemsName.get(0);
        return itemFirst.getText().equalsIgnoreCase(itemName);
    }

    public void clickOnItem(String itemName) {
        Objects.requireNonNull(itemsName.stream().filter(predicate -> predicate.getText().equalsIgnoreCase(itemName))
                .findAny().orElse(null)).click();
    }

    public List<WebElement> getItems() {
        return items;
    }



}