package Tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import utils.ConfigReader;
import utils.DriverFactory;
import utils.ElementHelper;
import utils.ExtentManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class BookingReservationTest {
    WebDriver driver;
    HomePage homePage;
    ElementHelper helper;
    ExtentReports report;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
        // ✅ Initialize browser from Config.properties
        DriverFactory.setDriver();
        driver = DriverFactory.getDriver();

        // ✅ Initialize helper and page classes
        helper = new ElementHelper(driver);
        homePage = new HomePage(driver);

        // ✅ Initialize report
        extent = ExtentManager.getInstance();
        test = extent.createTest("Booking.com Hotel Reservation Test");
        ExtentManager.setExtentTest(test);

        // ✅ Load URL from config file
        String baseUrl = ConfigReader.get("url");
        driver.get(baseUrl);
        driver.manage().window().maximize();

        test.log(Status.INFO, "🌐 Browser launched and navigated to: " + baseUrl);
    }


    @Test(description = "Verify that user can search hotels in New York")
    public void verifyHotelSearchFunctionality() {
        //Select the Destination
        homePage.clickStaysTab();
        homePage.setDestination();
        homePage.selectCity();
        //Verify the selected city name
        homePage.checkCityName("New York");
        test.log(Status.PASS, "✅ City verification successful: New York selected correctly");

        //Select the check-in checkout Dates
        homePage.selectTomorrowPlus7Nights();
        //Verify the check-in checkout Dates
        homePage.verifyTomorrowPlus7NightsSummary();

        // 📅 Calculate the dates for the log
        LocalDate today = LocalDate.now();
        LocalDate checkIn = today.plusDays(1);
        LocalDate checkOut = checkIn.plusDays(7);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM d", Locale.ENGLISH);
        String formattedCheckIn = checkIn.format(fmt);
        String formattedCheckOut = checkOut.format(fmt);

        // Loge message ✅
        test.log(Status.PASS, "✅ Date verification successful: from " + formattedCheckIn + " to " + formattedCheckOut);

        // Verify Defualt Guests and room number
        homePage.openGuestsPopup();
        int adults = homePage.getAdultsCount();
        int children = homePage.getChildrenCount();
        int rooms = homePage.getRoomsCount();

        Assert.assertEquals(adults, 2, "Default Adults count should be 2");
        Assert.assertEquals(children, 0, "Default Children count should be 0");
        Assert.assertEquals(rooms, 1, "Default Rooms count should be 1");

        test.log(Status.PASS, "✅ Default guest configuration verified successfully: " + adults + " adults, " + children + " children, " + rooms + " room(s)");
        //-------------------Adults Part-------------//

        int initialCount = homePage.getAdultsCount();
        // Increase adults
        homePage.clickAdultsPlus();
        helper.waitForSeconds(1);
        int increasedCount = homePage.getAdultsCount();
        Assert.assertEquals(increasedCount, initialCount + 1, "Adult count should increase by 1");

        test.log(Status.PASS, "✅ Adults increased successfully: " + initialCount + " → " + increasedCount);
        // Decrease adults
        homePage.clickAdultsMinus();
        helper.waitForSeconds(1);
        int decreasedCount = homePage.getAdultsCount();
        Assert.assertEquals(decreasedCount, initialCount, "Adult count should return to initial value");

        test.log(Status.PASS, "✅ Adults decreased successfully: " + increasedCount + " → " + decreasedCount);

        //-------------------Children Part-------------//
        test.log(Status.INFO, "👨‍👩‍👧 Starting Children Selection Test");

        // Increase children count to 2
        homePage.clickChildrenPlus();
        helper.waitForSeconds(1);
        homePage.clickChildrenPlus();
        helper.waitForSeconds(1);

        int increasedChildren = homePage.getChildrenCount();
        Assert.assertEquals(increasedChildren, 2, "Children count should increase to 2");
        test.log(Status.PASS, "✅ Children increased successfully to: " + increasedChildren);

        // Decrease children count back to 1
        homePage.clickChildrenMinus();
        helper.waitForSeconds(1);

        int decreasedChildren = homePage.getChildrenCount();
        Assert.assertEquals(decreasedChildren, 1, "Children count should decrease to 1");
        test.log(Status.PASS, "✅ Children decreased successfully to: " + decreasedChildren);

        // Increase children count again to 2
        homePage.clickChildrenPlus();
        helper.waitForSeconds(1);

        int finalChildrenCount = homePage.getChildrenCount();
        Assert.assertEquals(finalChildrenCount, 2, "Children count should increase back to 2");
        test.log(Status.PASS, "✅ Children increased again successfully to: " + finalChildrenCount);

        // Step 4 — Select ages for both children
        homePage.selectFirstChildAge("6 years old");
        test.log(Status.INFO, "🧒 First child age set to 6 years old");
        helper.waitForSeconds(1);

        homePage.selectSecondChildAge("7 years old");
        homePage.selectSecondChildAge("7 years old");
        test.log(Status.INFO, "🧒 Second child age set to 7 years old");
        helper.waitForSeconds(1);

        // Final confirmation
        test.log(Status.INFO, "👨‍👩‍👧 Children Selection Test Completed Successfully ✅");


        //-------------------Rooms Part-------------//
        test.log(Status.INFO, "🏨 Starting Rooms Selection Test");

        //  Increase rooms count to 2
        homePage.clickRoomsPlus();
        helper.waitForSeconds(1);

        int increasedRooms = homePage.getRoomsCount();
        Assert.assertEquals(increasedRooms, 2, "Rooms count should increase to 2");
        test.log(Status.PASS, "✅ Rooms increased successfully to: " + increasedRooms);

        // Decrease rooms count back to 1
        homePage.clickRoomsMinus();
        helper.waitForSeconds(1);

        int decreasedRooms = homePage.getRoomsCount();
        Assert.assertEquals(decreasedRooms, 1, "Rooms count should decrease to 1");
        test.log(Status.PASS, "✅ Rooms decreased successfully to: " + decreasedRooms);

        // Increase again to 2
        homePage.clickRoomsPlus();
        helper.waitForSeconds(1);

        int finalRoomsCount = homePage.getRoomsCount();
        Assert.assertEquals(finalRoomsCount, 2, "Rooms count should increase again to 2");
        test.log(Status.PASS, "✅ Rooms increased again successfully to: " + finalRoomsCount);

        // Verify main guests summary box text
        String summaryText = homePage.getGuestsSummaryText();
        test.log(Status.INFO, "📋 Current summary box: " + summaryText);
        Assert.assertTrue(summaryText.contains("2"), "Summary should show 2 rooms or correct guest info");

        //Click on Search button
        homePage.clickSearchButton();
        test.log(Status.PASS, "🔍 Clicked on 'Search' button successfully");

        test.log(Status.INFO, "🏨 Rooms Selection Test Completed Successfully ✅");
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
        ExtentManager.flushReport();
    }

}



