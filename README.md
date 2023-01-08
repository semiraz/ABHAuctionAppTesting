
# Auction App - API test

### Test automation suite for Auction App.  

## General info

Auction App is a website for auction where users can bid for some product by placing a bid for it. 
A user needs to create an account and log in and then a user can put and sell their product and bid for other products.

## Test suite
Test automation suite for "Auction App" web application written with Selenium TestNG
 framework in Java using Maven following POM pattern.

In the Utilities class("/src/test/java/test_components/Utilities") are all
 variables and random methods so we can change them according to our wishes to pass or fail the test. 

Created and automate extent reports log and created Listeners class which is implementing 
ITestListener - listener for test running. It captures screenshots for failed cases and attaches 
them to the extent reporter.

### Smoke test suite
 Contains E2E smoke test for core functionality. All pages are separated in the pages package("/src/main/java/pages")
  to not violate SRP.
  
### Regression test suite
Contains tests separated into classes by their functionalities. 

For MyAccountTest it is created separate builder classes following Builder design pattern("/src/main/java/forms")
 which is used for creation of personalInfo, cardInfo and shippingInfo forms. 

In the test/java/test_components package is a json file(createAccountData.json) 
with all data for creation account for RegistrationTest.



## Environment
Homepage: [Auction App](http://ec2-3-67-80-227.eu-central-1.compute.amazonaws.com:8090/ "Auction App")

Java Version 17.0.5

Google Chrome Version 108

## Deployment

To run smoke_testng.xml:

```mvn test -PSmoke```

To run regression_tests.xml:

```mvn test -PRegression```


## Status
This test contains smoke test and regresion tests. While application is in the progress,
 more tests would be added in regression package. See logs in browser after running .xml files.

Smoke test - "/src/test/java/smoke_test" package

Regression tests - "/src/test/java/regression_tests" package
