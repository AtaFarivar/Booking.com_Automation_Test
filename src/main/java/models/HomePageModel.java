package models;

import org.checkerframework.checker.index.qual.PolyUpperBound;
import org.openqa.selenium.By;

public class HomePageModel {
    public static By bookingLogo = By.xpath("//a[@aria-label='Booking.com']//*[name()='svg']");
    public static By registerItem = By.xpath("//a[@aria-label='Register an account']");
    public static By signinItem = By.xpath("//a[@aria-label='Sign in']");
    public static By staysTab = By.xpath("//a[@id='accommodations']");
    public static By destination = By.xpath("//input[@aria-label='Where are you going?']");
    public static By cityName = By.xpath("//div[normalize-space()='New York']");
    public static By selectedCityName = By.cssSelector("input[name='ss']");
    public static By dayCell(String iso) {
        return By.cssSelector("[data-testid='searchbox-datepicker-calendar'] [data-date='" + iso + "']");
    }
    //Calendar Section
    public static final By datepickerCalendar = By.cssSelector("[data-testid='searchbox-datepicker-calendar']");
    public static final By nextMonthBtn = By.cssSelector("button[aria-label='Next month']");
    public static final By datesContainer = By.cssSelector("[data-testid='searchbox-dates-container']");

    // Guests & Rooms Section
    public static By occupancyButton = By.cssSelector("button[data-testid='occupancy-config']");;

    // Adults
    public static By adultsValue = By.xpath("//label[@for='group_adults']/ancestor::div[1]/following-sibling::div//span[normalize-space()]");
    public static final By adultsMinus = By.xpath("//label[@for='group_adults']/ancestor::div[1]/following-sibling::div//button[1]");
    public static final By adultsPlus  = By.xpath("//label[@for='group_adults']/ancestor::div[1]/following-sibling::div//button[last()]");

    // Children
    public static By childrenValue = By.xpath("//label[@for='group_children']/ancestor::div[1]/following-sibling::div//span[normalize-space()]");
    public static final By childrenMinus = By.xpath("//label[@for='group_children']/ancestor::div[1]/following-sibling::div//button[1]");
    public static final By childrenPlus  = By.xpath("//label[@for='group_children']/ancestor::div[1]/following-sibling::div//button[last()]");

    // First child's age dropdown
    public static By firstChildAgeDropdown = By.xpath("(//select[@name='age'])[1]");

    // Second child's age dropdown (if more than one child)
    public static By secondChildAgeDropdown = By.xpath("(//select[@name='age'])[2]");

    // ---- Rooms row (anchor by label[for='no_rooms']) ----
    public static By roomsValue = By.xpath("//label[@for='no_rooms']/ancestor::div[1]/following-sibling::div//span[normalize-space()]");
    public static final By roomsMinus = By.xpath("//label[@for='no_rooms']/ancestor::div[1]/following-sibling::div//button[1]");
    public static final By roomsPlus  = By.xpath("//label[@for='no_rooms']/ancestor::div[1]/following-sibling::div//button[last()]");

    public static By searchButton = By.cssSelector("button[type='submit']");

}
