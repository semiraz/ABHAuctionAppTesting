package tests.smoke_test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.*;
import test_component.BaseTest;

import java.io.IOException;
import static test_component.Utilities.*;

public class SmokeTest extends BaseTest {

    protected LandingPage landingPage;
    protected ProductPage productPage;
    protected CreateAccountPage createAccountPage;
    protected CommonBarPage commonBarPage;
    protected LoginPage loginPage;

    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        createAccountPage = new CreateAccountPage(driver);
        commonBarPage = new CommonBarPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test(testName = "Smoke Test")
    public void smokeTest() throws InterruptedException {
        landingPage.goToLandingPage();
        Assert.assertTrue(landingPage.verifyTitle("Auction App"));
        Assert.assertTrue(landingPage.isLogoPresent());
        Assert.assertTrue(landingPage.isHighlightedProductPresent());

//        Thread.sleep(2000);
//        commonBarPage.goToCreateAccountPage();
//        createAccountPage.createAnAccount("Name", "Last", "email@gmail.com", "Pass123*");
        //Jane Doe user@gmail.com Pass123*
        Thread.sleep(2000);
        commonBarPage.goToLoginPage();
        loginPage.login("email@gmail.com", "Pass123*");

        Thread.sleep(2000);
        landingPage.getNavbarItemsPage("Last Chance");
        Assert.assertTrue(landingPage.isDisplayedBasedOnTimeLeftInAuction(productName));
        landingPage.clickOnItem(productName);

        productPage.verifyNameAndPrice(productName, price);
        Assert.assertTrue(productPage.placeBid(), "Entered price is smaller or equal to the product price or highest bid");

//        Thread.sleep(2000);
//        Assert.assertEquals(productPage.getHighestBid(), price, "The highest bid is not same as entered one");

        Thread.sleep(2000);
        Assert.assertEquals(productPage.getMessageCongrats(), messageCongrats, messageError());

        commonBarPage.logout();

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}









