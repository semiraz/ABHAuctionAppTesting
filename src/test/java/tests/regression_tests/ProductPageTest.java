package tests.regression_tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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
    protected SoftAssert softAssert;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        loginPage = new LoginPage(driver);
        commonBarPage = new CommonBarPage(driver);
        softAssert = new SoftAssert();

        landingPage.goToLandingPage();
    }

    //??
    @Test
    public void verifyIfUserCanEnterSmallerAmountThanProductPriceOrLastBid() {
        commonBarPage.goToLoginPage();
        loginPage.login("mel_nova@gmail.com", "Pass123*");
        landingPage.clickOnItem("Bag");

        if (productPage.getHighestBid() > 0) {
            productPage.placeSomeBid(productPage.getHighestBid() - 1);
            softAssert.assertEquals(productPage.getMessageForLowerPriceBid(), messageErrorLowerBid1, messageError());
        } else{
            productPage.placeSomeBid(productPage.getActualStartingPriceOfProduct() - 1);
            softAssert.assertEquals(productPage.getMessageForLowerPriceBid(), messageErrorLowerBid, messageError());
        }
        softAssert.assertAll();
    }

    @Test
    public void verifyPlaceholderTextInInputField() {
        landingPage.clickOnItem(productName);
        softAssert.assertTrue(productPage.getPlaceholderValue(), "It is not the same price");
        softAssert.assertAll();
    }

    @Test
    public void verifyProductPageIsOpen() {
        landingPage.clickOnItem(productName);
        softAssert.assertTrue(productPage.isOpen());
        softAssert.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}