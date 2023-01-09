package test_component;

import org.apache.maven.surefire.shared.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;

import java.sql.Struct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Utilities {

    protected WebDriver driver;
    public static String productName = "Watch";
    public static String title = "Auction App";
    public static String navbarOptionName = "Last Chance";
    public static String messageCongrats = "Congrats! You are the highest bider!";
    public static String messageErrorLowerBid = "Bid price can't be lower than product price.";
    public static String messageErrorLowerBid1 = "Bid price can't be lower or equal to the highest bid price.";
    public static String messageErrorPlaceBid = "Entered price is smaller or equal to the product price or highest bid";
    public static String messageErrorBadHighestBid = "The highest bid is not same as entered one";
    public static String messagePasswordInvalid = "Password is invalid";
    public static String messageEmailInvalid = "Email is invalid";
    public static String messageErrorTimeLeft = "It is not displayed based on the time left";

    public static List<String> firstName = Arrays.asList("Mini", "Maxi", "Kada", "Ben", "Jim", "Jane", "Lili");
    public static List<String> lastName = Arrays.asList("Minic", "Maximic", "Smith", "Miller", "Jones", "Flofia", "Anderson");

    //MyAccountTest:
    //Profile info:
    public static String option = "Profile";
    public static String firName = "Maja";
    public static String lasName = "Kabiv";
    public static String phoneNum = "1234568";

    //Card info:
    public static String nameOnCard = "MAJA KAVIB";
    public static String cardNumb = "9865567891234545";
    public static String cvv = "589";

    //Shipping info:
    public static String street = "My Street 85";
    public static String city = "London";
    public static String zipCode = "85024";
    public static String state = "Sta";
    public static String country = "England";



    protected static String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    protected static String lower = "abcdefghijklmnopqrstuvwxyz";
    protected static String character = "!@#$%^&*";
    protected static String number = "1234567890";

    public static String pageLoadFail(String value) {
        return "Failed to load page " + value;
    }

    public static String getFirstName(List<String> firstNames) {
        String nameF = "";
        for (String name : firstNames) {
            Collections.shuffle(firstNames);
            nameF = name;
        }
        return nameF;
    }

    public static String getLastName(List<String> lastNames) {
        String last = "";
        for (String name : lastNames) {
            Collections.shuffle(lastNames);
            last = name;
        }
        return last;
    }

    public static String messageError() {
        return "Message is not correct";
    }

    public static String messageErrorInfo() {
        return "Info is not correct";
    }

    public static String generateRandomEmail(int length, String name, String lastName) {
//        String allowedChars = "abcdefghijklmnopqrstuvwxyz" + "1234567890" + "_-.";
        String allowedChars = "1234567890" + "_-.";
        String email = "";
        String temp = RandomStringUtils.random(length, allowedChars);
        email = name + temp + lastName + "@gmail.com";
//        email = temp.substring(0, temp.length() - 9) + "@gmail.com";
        return email;
    }

    public static String randomPassword(int lengthCharacter, int lengthUpper, int lengthLower, int lengthNumber) {
        //"^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$"
        String c = RandomStringUtils.random(lengthCharacter, character);
        String u = RandomStringUtils.random(lengthUpper, upper);
        String l = RandomStringUtils.random(lengthLower, lower);
        String n = RandomStringUtils.random(lengthNumber, number);
        return c.concat(u).concat(l).concat(n);
    }

//    public static String getEmail() {
//        String regEx = "([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
//    }


}











