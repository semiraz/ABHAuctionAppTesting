package tests.regression_tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LandingPage;
import pages.ProductPage;
import test_component.BaseTest;

import java.io.IOException;

import static test_component.Utilities.productName;

public class HomepageTest extends BaseTest {
    protected LandingPage landingPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        landingPage.goToLandingPage();
    }

    @Test
    public void verifyIfProductsAreShownBasedOnCreationDate() {

    }

}










