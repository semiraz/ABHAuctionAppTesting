package tests.regression_tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;
import test_component.BaseTest;
import test_component.DataReader;
import test_component.Utilities;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class RegistrationTest extends BaseTest {
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

    @Test(dataProvider = "getCreateAccountData")
    public void verifyIfUserCanCreateAccount(HashMap<String, String> data) {
        CreateAccountPage createAccountPage = commonBarPage.goToCreateAccountPage();
        createAccountPage.createAnAccount(data.get("firstName"), data.get("lastName"), data.get("email"), data.get("password"));
        System.out.println(loginPage.isLoginPageOpen());
        softAssert.assertTrue(loginPage.isLoginPageOpen(), Utilities.pageLoadFail("LoginPage"));
        softAssert.assertAll();
    }

    @DataProvider
    public Object[][] getCreateAccountData() throws IOException {
        List<HashMap<String, String>> data = DataReader.getJsonDataToMap(System.getProperty("user.dir") + "/src/test/java/test_components/createAccountData.json.json");
        return new Object[][]{
                {data.get(0)},
                {data.get(1)},
                {data.get(2)},
        };
//        return new Object[][] {
//                {"Lala", "Lalic", "lal5pic-beste@gmolail.com", "Pass123*"},
//                {"Bad", "Bak", "Bademail*.@-gmail.1com", "Pass123*"},
//                {"Mini", "Poli", "lminsipo_5@dmain.com.ba", "Pass123*"}
//        };
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.quit();
    }

}
