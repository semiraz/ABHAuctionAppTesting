package tests.regression_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductPage;
import test_component.BaseTest;

import java.io.IOException;

import static test_component.Utilities.*;

public class ProductPageTest extends BaseTest {

    protected LandingPage landingPage;
    protected ProductPage productPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        landingPage.goToLandingPage();
        landingPage.clickOnItem(productName);
    }

    @Test
    public void verifyIfUserCanEnterSmallerAmountThanProductPrice() {
        double actualPrice = productPage.getActualPriceOfProduct();
        Assert.assertFalse(productPage.placeBid(actualPrice - 1));
        Assert.assertEquals(productPage.getMessageForLowerPriceBid(), messageErrorLowerBid, messageError());
    }

    @Test
    public void verifyPlaceholderTextInInputField() {
        Assert.assertTrue(productPage.getPlaceholderValue(), "It is not same price");
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
