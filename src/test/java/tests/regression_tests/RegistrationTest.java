package tests.regression_tests;

import org.bouncycastle.asn1.tsp.TSTInfo;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.*;
import test_component.BaseTest;

import java.io.IOException;

public class RegistrationTest extends BaseTest {
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

    @Test(dataProvider = "getCreateAccountData")
    public void verifyIfUserCanCreateAccount(String name, String lastName, String email, String password) {
        CreateAccountPage createAccountPage = commonBarPage.goToCreateAccountPage();
        createAccountPage.createAnAccount(name, lastName, email, password);
        Assert.assertTrue(loginPage.isLoginPageOpen(), "Somethings went wrong");
    }

    @DataProvider
    public Object[][] getCreateAccountData() {
        return new Object[][] {
                {"Lala", "Lalic", "k5a7a@gmail.com", "Pass123*"},
                {"Bad", "Bak", "Bademail*.@gmail.com", "Pass123*"},
                {"Mini", "Poli", "mpppoll1_5@gmail.com", "Pass123*"}
        };
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }

}
