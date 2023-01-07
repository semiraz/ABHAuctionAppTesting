package tests.regression_tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AboutUs;
import pages.FooterSection;
import pages.LandingPage;
import test_component.BaseTest;

import java.io.IOException;

public class FooterTest extends BaseTest {
    protected LandingPage landingPage;
    protected FooterSection footerSection;
    protected AboutUs aboutUs;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        footerSection = new FooterSection(driver);
        aboutUs = new AboutUs(driver);
        landingPage.goToLandingPage();
    }


    @Test(testName = "Verify Images On About Us page")
    public void verifyImagesIsPresentOnAboutUs() {
        footerSection.getAboutUs();
        Assert.assertTrue(aboutUs.verifyImageIsPresent(), "Image is not present");
    }


}








