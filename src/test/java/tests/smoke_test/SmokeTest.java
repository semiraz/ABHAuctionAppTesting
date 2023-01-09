package tests.smoke_test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import test_component.BaseTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static test_component.Utilities.*;

public class SmokeTest extends BaseTest {

    protected SoftAssert softAssert;
    protected LandingPage landingPage;
    protected ProductPage productPage;
    protected CreateAccountPage createAccountPage;
    protected CommonBarPage commonBarPage;
    protected LoginPage loginPage;
    protected String username;
    protected String password;
    protected String fName;
    protected String lName;
    protected double price;
    protected double highestInputBid;
    protected List<String> firstName = Arrays.asList("Mini", "Maxi", "Kada", "Ben", "Jim", "Jane", "Lili");
    protected List<String> lastName = Arrays.asList("Minic", "Maximic", "Smith", "Miller", "Jones", "Flofia", "Anderson");


    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
        softAssert = new SoftAssert();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        commonBarPage = new CommonBarPage(driver);
        loginPage = new LoginPage(driver);

        fName = getFirstName(firstName);
        lName = getLastName(lastName);
        password = randomPassword(2,1,2,3);
        username = generateRandomEmail(1, fName, lName);
    }

    @Test(testName = "Smoke Test")
    public void smokeTest() {
        landingPage.goToLandingPage();
        softAssert.assertTrue(landingPage.verifyTitle(title));
        softAssert.assertTrue(landingPage.isLogoPresent());
        softAssert.assertTrue(landingPage.isHighlightedProductPresent());

        commonBarPage.goToCreateAccountPage();
        createAccountPage.createAnAccount(fName, lName, username, password);
        softAssert.assertTrue(createAccountPage.verifyPassword(password), messagePasswordInvalid);
        softAssert.assertTrue(createAccountPage.verifyEmail(username), messageEmailInvalid);

        commonBarPage.goToLoginPage();
        loginPage.login(username, password);

        landingPage.getNavbarItemsPage(navbarOptionName);
        softAssert.assertTrue(landingPage.isDisplayedBasedOnTimeLeftInAuction("Jacket"), messageErrorTimeLeft);
        landingPage.clickOnItem(productName);

        price = productPage.getActualStartingPriceOfProduct();
        productPage.verifyNameAndPrice(productName, price);
        softAssert.assertTrue(productPage.placeBid(), messageErrorPlaceBid);

        highestInputBid = productPage.priceHighestBid();
        softAssert.assertEquals(productPage.getHighestBid(), highestInputBid, messageErrorBadHighestBid);

        softAssert.assertEquals(productPage.getMessageCongrats(), messageCongrats, messageError());

        commonBarPage.logout();
        softAssert.assertAll();
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}