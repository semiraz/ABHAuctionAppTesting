package tests.regression_tests;

import forms.*;
import org.openqa.selenium.JavascriptExecutor;

import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import test_component.BaseTest;
import test_component.Utilities;

import java.awt.*;
import java.io.IOException;

import static test_component.Utilities.*;
import static test_component.Utilities.getLastName;

public class MyAccountTest extends BaseTest {

    protected LandingPage landingPage;
    protected ProductPage productPage;
    protected CommonBarPage commonBarPage;
    protected ProfilePage profilePage;
    protected String username;
    protected String password;
    protected String fName;
    protected String lName;
    JavascriptExecutor js;
    protected SoftAssert softAssert;

    @BeforeClass
    public void createDataForAccount() {
        password = randomPassword(2, 1, 2, 3);
        fName = getFirstName(firstName);
        lName = getLastName(lastName);
        username = generateRandomEmail(1, fName, lName);
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        commonBarPage = new CommonBarPage(driver);
        profilePage = new ProfilePage(driver);
        softAssert = new SoftAssert();
        js = (JavascriptExecutor) driver;

        landingPage.goToLandingPage();
    }

    @Test(priority = 1)
    public void createAnAccount() throws InterruptedException {
        CreateAccountPage createAccountPage = commonBarPage.goToCreateAccountPage();
        createAccountPage.createAnAccount(fName, lName, username, password);
        Thread.sleep(3000);
    }

    @Test(priority = 2)
    public void verifyIfUserCanChangePersonalInformation() throws InterruptedException {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login(username, password);

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        Thread.sleep(2000);
        myAccountPage.chooseNavbar(option);
        Thread.sleep(2000);
        softAssert.assertEquals(myAccountPage.isCorrectPageOpened(), option);

        PersonalInfo personalInfo = new PersonalInfo.PersonalInfoBuilder().setFirstName(firName)
                .setLastName(lasName).setPhoneNumber(phoneNum).setDateOfBirth("12.03.1998").build();
        profilePage.fillPersonalInfoForm(personalInfo);
        profilePage.saveInfo();
//        Assert.assertTrue(profilePage.verifySaveButtonUpdating(), "Something went wrong");
        Thread.sleep(3000);
        commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);

        softAssert.assertTrue(profilePage.arePersonalInfoChanged(personalInfo));
        softAssert.assertAll();
    }

    @Test(priority = 3)
    public void verifyIfUserCanChangePhoto() throws InterruptedException, AWTException {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login(username, password);

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        Thread.sleep(2000);
        myAccountPage.chooseNavbar(option);
        Thread.sleep(2000);
        softAssert.assertEquals(myAccountPage.isCorrectPageOpened(), option);

        profilePage.uploadPhoto("/Users/semira/Downloads/profileImg.webp");
        profilePage.saveInfo();
        Thread.sleep(2000);
        softAssert.assertAll();
    }

    @Test(priority = 4)
    public void verifyIfUserCanChangeCardInfo() throws InterruptedException {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login(username, password);

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);
        softAssert.assertTrue(profilePage.openCardInfoContainer(), pageLoadFail("CardInfoContainer"));

        CardInfo cardInfo = new CardInfo.CardInfoBuilder().setNameOnCard(nameOnCard)
                .setCardNumber(cardNumb).setExpirationDate("12.12.2030").setCvvValue(cvv).build();
        profilePage.fillCardInfoForm(cardInfo);
        profilePage.saveInfo();
        Thread.sleep(3000);

        commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);

        softAssert.assertTrue(profilePage.openCardInfoContainer(), pageLoadFail("CardInfoContainer"));
        js.executeScript("window.scrollBy(0,900)");
        softAssert.assertTrue(profilePage.areInfoChanged(nameOnCard), Utilities.messageErrorInfo());
        softAssert.assertTrue(profilePage.areInfoChanged(cardNumb), Utilities.messageErrorInfo());
        softAssert.assertAll();
    }

    @Test(priority = 5)
    public void verifyIfUserCanChangeShippingInfo() throws InterruptedException {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login(username, password);

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);

        softAssert.assertTrue(profilePage.openShippingInfoContainer(), pageLoadFail("ShippingFormContainer"));

        ShippingInfo shippingInfo = new ShippingInfo.ShippingInfoBuilder().setStreet(street).setCity(city)
                .setZipCode(zipCode).setState("St").setCountry(country).build();
        profilePage.fillShippingInfoForm(shippingInfo);
        profilePage.saveInfo();

        Thread.sleep(3000);
        commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);
        softAssert.assertTrue(profilePage.openShippingInfoContainer(), pageLoadFail("ShippingFormContainer"));
        js.executeScript("window.scrollBy(0,1000)");
        softAssert.assertTrue(profilePage.areInfoChanged(country), Utilities.messageErrorInfo());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}