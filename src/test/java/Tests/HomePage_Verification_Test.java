package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import models.HomePageModel;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utils.DriverFactory;
import utils.ElementHelper;
import utils.ExtentManager;

public class HomePage_Verification_Test {

    WebDriver driver;
    ElementHelper helper;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // Start WebDriver using DriverFactory
        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();
        helper = new ElementHelper(driver);

        // Initialize Extent Report using ExtentManager
        extent = ExtentManager.getInstance();
        test = extent.createTest("Booking.com Home Page Test");
        ExtentManager.setExtentTest(test);

        test.log(Status.INFO, "Opened Booking.com homepage");
    }

    @Test(description = "Verify Booking.com homepage essential elements")
    public void verifyHomePageElements() {
        try {
            // Check Booking.com logo
            HomePage homePage = new HomePage(driver);
            homePage.checkItems();
            test.log(Status.PASS, "Booking logo is visible");

            // Check Register and Sign-in links
            boolean registerVisible = helper.isElementVisible(HomePageModel.registerItem);
            boolean signinVisible = helper.isElementVisible(HomePageModel.signinItem);

            Assert.assertTrue(registerVisible, "'Register' link not visible!");
            Assert.assertTrue(signinVisible, "'Sign in' link not visible!");
            test.log(Status.PASS, "Register and Sign-in links are visible");

        } catch (Exception e) {
            test.log(Status.FAIL, "Test failed: " + e.getMessage());
            Assert.fail(e.getMessage());
        }
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentManager.flushReport();
    }
}

