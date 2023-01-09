package pages;

import forms.PersonalInfo;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyAccountPage extends PageObject {
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div[class='c-navbar-list'] div")
    private List<WebElement> navbarElementList;
    @FindBy(css = ".c-navbar-element.c-navbar-element-focused")
    private WebElement focusedNavbarEl;

    public void chooseNavbar(String option) {
        for (WebElement s : navbarElementList) {
            if (s.getText().equalsIgnoreCase(option)) {
                s.click();
                break;
            }
        }
    }

    public String isCorrectPageOpened() {
        return focusedNavbarEl.getText();
    }




}
