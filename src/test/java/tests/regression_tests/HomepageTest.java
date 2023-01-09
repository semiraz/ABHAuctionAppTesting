package tests.regression_tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LandingPage;
import pages.ProductPage;
import test_component.BaseTest;

import java.io.IOException;
import java.util.List;

public class HomepageTest extends BaseTest {
    protected LandingPage landingPage;
    protected ProductPage productPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() throws IOException {
        driver = initializeDriver();
        landingPage = new LandingPage(driver);
        productPage = new ProductPage(driver);
        landingPage.goToLandingPage();
    }

    @Test
    public void verifyIfProductsAreShownBasedOnCreationDate() {

    }

    @Test(dataProvider = "getNavbarData")
    public void verifyIfNavbarItemsNameAndPriceAreAsExpected(String name) {
        List<WebElement> items = landingPage.getItemsName();
        List<WebElement> itemsPriceList = landingPage.getItemsPrice();

            for (int i = 0; i < items.size(); i++) {
                landingPage.getNavbarItemsPage(name);
                System.out.println(items.get(i).getText());
                String itemNames = items.get(i).getText();
                double itemPrice = Double.parseDouble(itemsPriceList.get(i).getText().split("\\$")[1]);
                items.get(i).click();
                if (productPage.isOpen()) {
//                    productPage.getInfoFromBidContainer();
                    Assert.assertEquals(itemNames, productPage.getActualProductName(), "It is not same name of the product");
                    Assert.assertEquals(itemPrice, productPage.getActualStartingPriceOfProduct(), "It is not same prices of product");
                }
                driver.navigate().back();
            }

    }

    @DataProvider
    public Object[][] getNavbarData() {
        return new Object[][] {
                {"Last Chance"},
                {"New Arrivals"}
        };
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        driver.close();
    }
}
















