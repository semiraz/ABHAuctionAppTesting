package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class FooterSection extends PageObject{

    public FooterSection(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".c-footer")
    private WebElement footer;

    @FindBy(xpath = "//a[text()='About US']")
    private WebElement aboutUsSection;
    @FindBy(xpath = "//a[text()='Terms and Conditions']")
    private WebElement termsSection;
    @FindBy(xpath = "//a[text()='Privacy and policy']")
    private WebElement privacySection;

    public AboutUs getAboutUs() {
        aboutUsSection.click();
        return new AboutUs(driver);
    }

    public TermsAndCondition getTerms() {
        termsSection.click();
        return new TermsAndCondition(driver);
    }

    public PrivacyAndPolicy getPrivacy() {
        privacySection.click();
        return new PrivacyAndPolicy(driver);
    }

}













