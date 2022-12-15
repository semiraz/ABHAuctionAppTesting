package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProductPage extends PageObject {

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".c-product-info h1")
    private WebElement productName;

    @FindBy(xpath = "//div[@class='c-product-info']/p[1]/span")
    private WebElement priceProduct;

    @FindBy(css = ".c-send-bid input[type='number']")
    private WebElement inputField;

    @FindBy(css = ".c-send-bid button")
    private WebElement button;

    @FindBy(css = ".c-highest-bid p")
    private WebElement messageCongrats;
    @FindBy(css = ".c-bid-error p")
    private WebElement messageErrorLowerPriceBid;

    @FindBy(css = ".c-bid-info p span")
    private List<WebElement> infoBidContainer;

    @FindBy(css = ".c-bid-info")
    private WebElement infoContainer;
    @FindBy(xpath = "//*[@class='c-send-bid']/input")
    private WebElement placeholderValue;

    private String getActualProductName() {
        return productName.getText();
    }

    public double getActualPriceOfProduct() {
        return Double.parseDouble(priceProduct.getText().split("\\$")[1]);
    }

    public boolean verifyNameAndPrice(String name, double price) {
        double priceD = Double.parseDouble(priceProduct.getText().split("\\$")[1]);
        return productName.getText().equalsIgnoreCase(name) && priceD == price;
    }

    //    public void getInfoFromBidContainer() {
//        double highestBid = 0;
//        int numberOdBids = 0;
//        int timeLeft = 0;
//
//        for (WebElement w : infoBidContainer) {
//            WebElement h = infoBidContainer.get(0);
//            WebElement n = infoBidContainer.get(1);
//            WebElement t = infoBidContainer.get(2);
//            highestBid = Double.parseDouble(h.getText().split("\\$")[1]);
//            numberOdBids = Integer.parseInt(n.getText());
//            timeLeft = Integer.parseInt(t.getText().split("days")[0].trim());
//        }
//        System.out.println("highest bid: " + highestBid);
//        System.out.println("Number of bids: " + numberOdBids);
//        System.out.println("Time left: " + timeLeft);
//
//    }
    public double getHighestBid() {
        double highestBid = 0;
        for (WebElement w : infoBidContainer) {
            w = infoBidContainer.get(0);
            highestBid = Double.parseDouble(w.getText().split("\\$")[1]);
            break;
        }
        return highestBid;
    }

    public boolean placeBid(double price) {
        inputField.sendKeys(String.valueOf(price));
        button.click();
        return price > getActualPriceOfProduct();
    }

    public boolean getPlaceholderValue() {
        double placeholderValueD = Double.parseDouble(placeholderValue.getAttribute("placeholder")
                .split("\\$")[1].split("or")[0].trim());
        return placeholderValueD == getActualPriceOfProduct() || placeholderValueD == getHighestBid();
    }

    public String getMessageCongrats() {
        return messageCongrats.getText();
    }

    public String getMessageForLowerPriceBid(){
        return messageErrorLowerPriceBid.getText();
    }

    public void placeHighestBid(double price) {
        if (infoContainer.isDisplayed() && price > getHighestBid()) {
            inputField.sendKeys(String.valueOf(price));
            button.click();
        } else {
            System.out.println("Entered price is smaller or equal to the highest bid");
        }
    }


}












