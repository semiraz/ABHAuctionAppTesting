package pages;

import forms.CardInfo;
import forms.PersonalInfo;
import forms.ShippingInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class ProfilePage extends PageObject {

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstName;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastName;
    @FindBy(xpath = "//input[@name='email']")
    private WebElement email;
    @FindBy(xpath = "//input[@name='dateOfBirth']")
    private WebElement dateOfBirth;
    @FindBy(xpath = "//input[@name='phoneNumber']")
    private WebElement phoneNumber;
    @FindBy(xpath = "//div[@class='c-text-input']/input")
    private List<WebElement> placeholdersValue;
    @FindBy(xpath = "//input[@name='holderName']")
    private WebElement nameOnCard;
    @FindBy(xpath = "//input[@name='number']")
    private WebElement cardNumber;
    @FindBy(xpath = "//input[@name='expirationDate']")
    private WebElement expDateCard;
    @FindBy(xpath = "//input[@name='verificationValue']")
    private WebElement cvvVerification;
    @FindBy(xpath = "//input[@name='street']")
    private WebElement shippingStreet;
    @FindBy(xpath = "//input[@name='city']")
    private WebElement shippingCity;
    @FindBy(xpath = "//input[@name='zipCode']")
    private WebElement zipCode;
    @FindBy(xpath = "//input[@name='state']")
    private WebElement shippingState;
    @FindBy(xpath = "//input[@name='country']")
    private WebElement shippingCountry;
    @FindBy(css = "div[class='c-personal-image'] p")
    private WebElement nameOfPhoto;
    @FindBy(css = "div[class='c-personal-image'] label")
    private WebElement changePhotoBtn;
    @FindBy(css = "div[class='c-profile-wrapper'] button") //text()='UPDATING...' - button disabled
    private WebElement saveButton;
    @FindBy(css = "div[class='c-error-message'] p")
    private WebElement errorInputMsg;
    @FindBy(css = ".c-card-header")
    private WebElement cardFormDisplay;
    @FindBy(css = ".c-card-content .c-form-component")
    private WebElement cardContentOpen;
    @FindBy(css = ".c-location-header")
    private WebElement shippingFormDisplay;
    @FindBy(css = ".c-location-content")
    private WebElement shippingContentOpen;

    public void fillPersonalInfoForm(PersonalInfo personalInfo) {
        this.firstName.sendKeys(personalInfo.getFirstName());
        this.lastName.sendKeys(personalInfo.getLastName());
        this.phoneNumber.sendKeys(personalInfo.getPhoneNumber());
        this.dateOfBirth.sendKeys(personalInfo.getDateOfBirth());
    }

    public void fillCardInfoForm(CardInfo cardInfo) {
        this.nameOnCard.sendKeys(cardInfo.getNameOnCard());
        this.cardNumber.sendKeys(cardInfo.getCardNumber());
        this.expDateCard.sendKeys(cardInfo.getExpirationDate());
        this.cvvVerification.sendKeys(cardInfo.getCvvValue());
    }

    public void fillShippingInfoForm(ShippingInfo shippingInfo) {
        this.shippingStreet.sendKeys(shippingInfo.getStreet());
        this.shippingCity.sendKeys(shippingInfo.getCity());
        this.zipCode.sendKeys(shippingInfo.getZipCode());
        this.shippingState.sendKeys(shippingInfo.getState());
        this.shippingCountry.sendKeys(shippingInfo.getCountry());
    }

    public void saveInfo() {
        saveButton.click();
        waitForTextToBePresentInElement(saveButton, "UPDATING...");
    }

    public boolean arePersonalInfoChanged(PersonalInfo personalInfo) {
        ArrayList<String> plValue = new ArrayList<>();
        for (int i=0; i< placeholdersValue.size(); i++) {
            String pl = placeholdersValue.get(i).getAttribute("placeholder");
            if (!plValue.contains(pl)) {
                plValue.add(pl);
            }
        }
        return plValue.contains(personalInfo.getFirstName()) && plValue.contains(personalInfo.getLastName()) && plValue.contains(personalInfo.getPhoneNumber());
    }

    public boolean openCardInfoContainer() {
        cardFormDisplay.click();
        return cardContentOpen.isDisplayed();
    }

    public boolean openShippingInfoContainer() {
        shippingFormDisplay.click();
        return shippingContentOpen.isDisplayed();
    }

    private List<String> listChangedInfo() {
        ArrayList<String> plValue = new ArrayList<>();
        for (int i=0; i< placeholdersValue.size(); i++) {
            String pl = placeholdersValue.get(i).getAttribute("placeholder");
            if (!plValue.contains(pl)) {
                plValue.add(pl);
            }
        }
        System.out.println(plValue);
        return plValue;
    }

    public boolean areInfoChanged(String info) {
        List<String> placehol = listChangedInfo();
        return placehol.contains(info);
    }

    public void uploadPhoto(String url) throws AWTException {
        changePhotoBtn.click();
        Robot robot = new Robot();

        // copying File path to Clipboard
        StringSelection str = new StringSelection(url);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        //press Ctr+V for pasting
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);

        //release Ctr+V for pasting
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);

        //for pressing and releasing Enter
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }




}













