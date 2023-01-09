package tests.regression_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.CommonBarPage;
import pages.LandingPage;
import pages.LoginPage;
import pages.ProductPage;
import test_component.BaseTest;

import java.io.IOException;

import static test_component.Utilities.*;

public class ProductPageTest extends BaseTest {

    protected LandingPage landingPage;
    protected ProductPage productPage;
    protected LoginPage loginPage;
    protected CommonBarPage commonBarPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);
        commonBarPage = new CommonBarPage(driver);

        landingPage.goToLandingPage();
    }


    //??
    @Test
    public void verifyIfUserCanEnterSmallerAmountThanProductPriceOrLastBid() {
        commonBarPage.goToLoginPage();
        loginPage.login("kivi@gmail.com", "Pass123*");
        landingPage.clickOnItem("Watch");

        if (productPage.getHighestBid() > 0) {
            productPage.placeSomeBid(productPage.getHighestBid() - 1);
            Assert.assertEquals(productPage.getMessageForLowerPriceBid(), messageErrorLowerBid1, messageError());
        } else{
            productPage.placeSomeBid(productPage.getActualStartingPriceOfProduct() - 1);
            Assert.assertEquals(productPage.getMessageForLowerPriceBid(), messageErrorLowerBid, messageError());
        }

    }

    @Test
    public void verifyPlaceholderTextInInputField() {
        landingPage.clickOnItem(productName);
        Assert.assertTrue(productPage.getPlaceholderValue(), "It is not same price");
    }

    @Test
    public void verifyProductPageIsOpen() {
        landingPage.clickOnItem("Watch");
        Assert.assertTrue(productPage.isOpen());
    }

    @Test
    public void verifyIfUserCanGetMessageWhenHeIsOutbid() {

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
