package pages;


import models.HomePageModel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import utils.ElementHelper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static models.HomePageModel.selectedCityName;

public class HomePage {

    WebDriver driver;
    ElementHelper helper;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        helper = new ElementHelper(driver);
    }

    public void checkItems() {
        Assert.assertTrue(helper.isElementVisible(HomePageModel.bookingLogo), "'Booking Logo' is not visible!");
    }

    public void clickStaysTab() {
        helper.click(HomePageModel.staysTab);
    }

    public void setDestination() {
        helper.sendKeys(HomePageModel.destination, "Newyork");
    }

    public void selectCity() {
        helper.click(HomePageModel.cityName);
    }

    public void checkCityName(String expectedCity) {
        helper.waitForAttributeContains(selectedCityName, "value", expectedCity);
        String actualValue = helper.getValue(selectedCityName);
        if (!actualValue.toLowerCase().contains(expectedCity.toLowerCase())) {
            throw new AssertionError("Expected city to contain '" + expectedCity + "', but was: " + actualValue);
        }
    }

    public void selectTomorrowPlus7Nights() {
        LocalDate today = LocalDate.now();
        LocalDate checkIn = today.plusDays(1);
        LocalDate checkOut = checkIn.plusDays(7);

        // 1) Wait until the calendar is visible
        // (It usually opens automatically after selecting the destination)
        helper.isElementVisible(HomePageModel.datepickerCalendar);

        // 2) If tomorrow is in the next month, click "Next Month" once
        if (checkIn.getMonthValue() != today.getMonthValue()) {
            helper.click(HomePageModel.nextMonthBtn);
        }

        // 3) Select check-in date
        By inCell = HomePageModel.dayCell(checkIn.toString()); // Format: YYYY-MM-DD
        if (!helper.isElementVisible(inCell)) {
            // If not yet visible, click "Next Month" once more (some themes load lazily)
            helper.click(HomePageModel.nextMonthBtn);
        }
        helper.click(inCell);

        // 4) Select check-out date
        // If it's in the next month and not visible, click "Next Month" once
        By outCell = HomePageModel.dayCell(checkOut.toString());
        if (!helper.isElementVisible(outCell)) {
            helper.click(HomePageModel.nextMonthBtn);
        }
        helper.click(outCell);
    }

    // After selectTomorrowPlus7Nights(), this method verifies that the selected date summary is correct //
    public void verifyTomorrowPlus7NightsSummary() {
        LocalDate today = LocalDate.now();
        LocalDate checkIn = today.plusDays(1);
        LocalDate checkOut = checkIn.plusDays(7);

        // Format example: "Tue, Oct 14"
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("EEE, MMM d", Locale.ENGLISH);
        String expectedStart = checkIn.format(fmt);
        String expectedEnd = checkOut.format(fmt);

        // Read the displayed date summary text
        String summaryText = helper.getText(HomePageModel.datesContainer);

        // Verify that both start and end dates are correctly displayed (dash not important)
        Assert.assertTrue(summaryText.contains(expectedStart),
                "Start date not displayed correctly. Expected part: " + expectedStart + " | Actual: " + summaryText);
        Assert.assertTrue(summaryText.contains(expectedEnd),
                "End date not displayed correctly. Expected part: " + expectedEnd + " | Actual: " + summaryText);
    }
    // -------------------- Children Part --------------------
    public void openGuestsPopup() {
        helper.click(HomePageModel.occupancyButton);
    }
    public int getAdultsCount() {
        return Integer.parseInt(driver.findElement(HomePageModel.adultsValue).getText().trim());
    }

    public int getChildrenCount() {
        return Integer.parseInt(driver.findElement(HomePageModel.childrenValue).getText().trim());
    }

    public int getRoomsCount() {
        return Integer.parseInt(driver.findElement(HomePageModel.roomsValue).getText().trim());
    }

    public void clickAdultsPlus() {
        helper.click(HomePageModel.adultsPlus);
    }

    public void clickAdultsMinus() {
        helper.click(HomePageModel.adultsMinus);
    }

    // -------------------- Children --------------------

    public void clickChildrenPlus() {
        helper.click(HomePageModel.childrenPlus);
    }

    public void clickChildrenMinus() {
        helper.click(HomePageModel.childrenMinus);
    }
    /**
     * Select age for the first child after increasing child count
     */
    public void selectFirstChildAge(String age) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(HomePageModel.firstChildAgeDropdown));
        dropdown.click();
        Select select = new Select(dropdown);
        select.selectByVisibleText(age);
    }

    /**
     * Select age for the second child (if two children exist)
     */
    public void selectSecondChildAge(String age) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(HomePageModel.secondChildAgeDropdown));
        dropdown.click();
        Select select = new Select(dropdown);
        select.selectByVisibleText(age);
    }

    // -------------------- Rooms Section --------------------

    public void clickRoomsPlus() {
        helper.click(HomePageModel.roomsPlus);
    }

    public void clickRoomsMinus() {
        helper.click(HomePageModel.roomsMinus);
    }

    /**
     * Reads the summary box that displays total guests & rooms after selection
     */
    public String getGuestsSummaryText() {
        return driver.findElement(HomePageModel.occupancyButton).getText();
    }

    public void clickSearchButton() {
        helper.click(HomePageModel.searchButton);
    }


}

