package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class AboutUs extends PageObject{
    public AboutUs(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='c-other-images']/div")
    private List<WebElement> otherImages;
    @FindBy(xpath = "//div[@class='c-main-image']")
    private WebElement mainImage;

    public boolean verifyImageIsPresent() {
        for (WebElement w : otherImages) {
            if (w.isDisplayed()) {
                return true;
            }
        }
        return false;
    }

}










