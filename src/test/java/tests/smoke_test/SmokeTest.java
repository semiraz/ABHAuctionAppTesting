package tests.smoke_test;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductPage;
import test_component.BaseTest;

import java.io.IOException;
import static test_component.Utilities.*;

public class SmokeTest extends BaseTest {

    protected LandingPage landingPage;
    protected ProductPage productPage;

    @BeforeTest
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
    }

    @Test(testName = "Smoke Test")
    public void smokeTest() {
        landingPage.goToLandingPage();
        Assert.assertTrue(landingPage.isLogoPresent());
        Assert.assertTrue(landingPage.isHighlightedProductPresent());

        landingPage.getNavbarItemsPage("Last Chance");
        Assert.assertTrue(landingPage.isDisplayedBasedOnTimeLeftInAuction(productName));
        landingPage.clickOnItem(productName);

        productPage.verifyNameAndPrice(productName, price);
        Assert.assertTrue(productPage.placeBid(price), "Entered price is smaller or equal to the product price");

        Assert.assertEquals(productPage.getHighestBid(), price, "The highest bid is not same as entered one");

        Assert.assertEquals(productPage.getMessageCongrats(), messageCongrats, messageError());

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}


















