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
    private WebElement startingPriceProduct;

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

    @FindBy(xpath = "//span[@class='c-last-item c-path-item']")
    private WebElement singleProduct;

    public String getActualProductName() {
        return productName.getText();
    }

    public double getActualStartingPriceOfProduct() {
        return Double.parseDouble(startingPriceProduct.getText().split("\\$")[1]);
    }

    public boolean isOpen() {
        return singleProduct.isDisplayed();
    }

    public boolean verifyNameAndPrice(String name, double price) {
        return productName.getText().equalsIgnoreCase(name) && getActualStartingPriceOfProduct() == price;
    }

    public void getInfoFromBidContainer() {
        double highestBid = 0;
        int numberOdBids = 0;
        String timeLeft = null;

        if (infoContainer != null) {
            for (WebElement w : infoBidContainer) {
                WebElement h = infoBidContainer.get(0);
                WebElement n = infoBidContainer.get(1);
                WebElement t = infoBidContainer.get(2);

                highestBid = Double.parseDouble(h.getText().split("\\$")[1]);
                numberOdBids = Integer.parseInt(n.getText());
//                timeLeft = Integer.parseInt(t.getText().split("\\w+")[0].trim());
                timeLeft = t.getText();
            }
        } else {
            getActualStartingPriceOfProduct();
        }
        System.out.println("highest bid: " + highestBid);
        System.out.println("Number of bids: " + numberOdBids);
        System.out.println("Time left: " + timeLeft);

    }
    public double getHighestBid() {
        double highestBid = 0;
        for (WebElement w : infoBidContainer) {
            w = infoBidContainer.get(0);
            highestBid = Double.parseDouble(w.getText().split("\\$")[1]);
            break;
        }
        return highestBid;
    }

    public boolean placeBid() {
        double bidPrice;
        if (infoContainer == null) {
            bidPrice = getHighestBid() + 1;
            inputField.sendKeys(String.valueOf(bidPrice));
            button.click();
            return  ((bidPrice > getActualStartingPriceOfProduct()) && (bidPrice > getHighestBid()));
        } else {
            bidPrice = getActualStartingPriceOfProduct() + 1;
            inputField.sendKeys(String.valueOf(bidPrice));
            button.click();
            return bidPrice > getActualStartingPriceOfProduct();
        }
    }

    public void placeSomeBid(double price) {
            inputField.sendKeys(String.valueOf(price));
            button.click();
    }

    public boolean getPlaceholderValue() {
        double placeholderValueD = Double.parseDouble(placeholderValue.getAttribute("placeholder")
                .split("\\$")[1].split("or")[0].trim());
        return placeholderValueD == getActualStartingPriceOfProduct() || placeholderValueD == getHighestBid();
    }

    public String getMessageCongrats() {
        return messageCongrats.getText();
    }

    public String getMessageForLowerPriceBid(){
        return messageErrorLowerPriceBid.getText();
    }

//    public void placeHighestBid(double price) {
//        if (infoContainer.isDisplayed() && price > getHighestBid()) {
//            inputField.sendKeys(String.valueOf(price));
//            button.click();
//        } else {
//            System.out.println("Entered price is smaller or equal to the highest bid");
//        }
//    }


}












