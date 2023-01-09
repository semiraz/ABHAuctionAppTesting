package tests.regression_tests;

import forms.*;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import test_component.BaseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

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
    protected List<String> firstName = Arrays.asList("Mini", "Maxi", "Kada", "Ben", "Jim", "Jane", "Lili");
    protected List<String> lastName = Arrays.asList("Minic", "Maximic", "Smith", "Miller", "Jones", "Flofia", "Anderson");
    protected String option = "Profile";
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        commonBarPage = new CommonBarPage(driver);
        profilePage = new ProfilePage(driver);
    }

    @BeforeMethod(alwaysRun = true)
    public void createAnAccount() {
        landingPage.goToLandingPage();
//        username = generateRandomEmail(20);
        password = randomPassword(2,1,2,3);
        fName = getFirstName(firstName);
        lName = getLastName(lastName);
//
//        CreateAccountPage createAccountPage = commonBarPage.goToCreateAccountPage();
//        createAccountPage.createAnAccount(fName, lName, username, password);
    }

    @Test
    public void verifyIfUserCanChangePersonalInformation() {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login("kivi@gmail.com", "Pass123*");

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);
        Assert.assertEquals(myAccountPage.isCorrectPageOpened(), option);

        PersonalInfo personalInfo = new PersonalInfo.PersonalInfoBuilder().setFirstName("MyNamr")
                .setLastName("My LastName").setPhoneNumber("265898").setDateOfBirth("12.03.1998").build();
        profilePage.fillPersonalInfoForm(personalInfo);
        profilePage.saveInfo();
//        Assert.assertTrue(profilePage.verifySaveButtonUpdating(), "Something went wrong");

        Assert.assertTrue(profilePage.arePersonalInfoChanged(personalInfo));

    }

    @Test
    public void verifyIfUserCanChangeCardInfo() {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login("kivi@gmail.com", "Pass123*");

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);
        Assert.assertTrue(profilePage.openCardInfoContainer(), pageLoadFail("CardInfoContainer"));

        CardInfo cardInfo = new CardInfo.CardInfoBuilder().setNameOnCard("SOME NAME")
                .setCardNumber("1234567891234562").setExpirationDate("12.12.2030").setCvvValue("123").build();
        profilePage.fillCardInfoForm(cardInfo);
        profilePage.saveInfo();
    }

    @Test
    public void verifyIfUserCanChangeShippingInfo() throws InterruptedException {
        LoginPage loginPage = commonBarPage.goToLoginPage();
        loginPage.login("kivi@gmail.com", "Pass123*");

        MyAccountPage myAccountPage = commonBarPage.goToMyAccountPage();
        myAccountPage.chooseNavbar(option);
        
        Assert.assertTrue(profilePage.openShippingInfoContainer(), pageLoadFail("ShippingFormContainer"));

        ShippingInfo shippingInfo = new ShippingInfo.ShippingInfoBuilder().setStreet("Mki25").setCity("Sarajevo")
                .setZipCode("71210").setState("St").setCountry("BiH").build();
        profilePage.fillShippingInfoForm(shippingInfo);
        profilePage.saveInfo();

//        Thread.sleep(2000);
        Assert.assertTrue(profilePage.openShippingInfoContainer(), pageLoadFail("ShippingFormContainer"));
        js.executeScript("window.scrollBy(0,1000)");
        Assert.assertTrue(profilePage.areInfoChanged("Mk25"));
    }

}













