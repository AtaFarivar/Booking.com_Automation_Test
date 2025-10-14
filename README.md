# 🏨 Booking.com Test Automation Framework (Selenium + TestNG)

This project automates the **hotel reservation flow** on
[Booking.com](https://www.booking.com/) using **Java, Selenium
WebDriver, TestNG**, and **Extent Reports**.\
It verifies functionalities such as **city selection, date selection,
guest configuration (adults, children, rooms)**, and runs tests **in
parallel** for better efficiency.

------------------------------------------------------------------------

## 🚀 Features

-   ✅ **End-to-End Booking Flow**: From selecting destination to
    verifying search results.\
-   🧠 **Page Object Model (POM)**: Clean, maintainable, and scalable
    structure.\
-   📆 **Dynamic Date Handling**: Automatically selects tomorrow's date
    and adds 7 nights.\
-   👨‍👩‍👧 **Guest Configuration Tests**: Verifies adult, child, and room
    adjustments including child age dropdowns.\
-   ⚙️ **Parallel Execution** via TestNG XML\
-   📊 **Beautiful Extent Reports** (auto-cleans old reports, keeps
    latest)\
-   💻 Works cross-platform (Mac, Windows, Linux)

------------------------------------------------------------------------

## 🧱 Project Structure

    BookingTests/
    │
    ├── src/
    │   ├── main/java/
    │   │   ├── models/          # Element locators (By objects)
    │   │   ├── pages/           # Page classes (POM)
    │   │   ├── utils/           # Helpers, DriverFactory, ExtentManager
    │   │
    │   └── test/java/
    │       └── Tests/
    │           ├── BookingReservationTest.java
    │           ├── HomePage_Verification_Test.java
    │
    ├── test-Report/             # Auto-generated Extent HTML reports
    ├── pom.xml                  # Maven dependencies
    ├── testng.xml               # TestNG configuration (parallel execution)
    └── README.md

------------------------------------------------------------------------

## 🧩 Tech Stack

  Tool / Library                  Purpose
  ------------------------------- -------------------------------------
  **Java 11+**                    Programming language
  **Selenium WebDriver 4.27.0**   Browser automation
  **TestNG 7.10.2**               Test framework + parallel execution
  **Extent Reports 4.1.7**        Test reporting
  **WebDriverManager 5.9.3**      Automatic driver management
  **Maven**                       Build & dependency management

------------------------------------------------------------------------

## ⚙️ Running Tests

### ▶️ 1. Run All Tests Sequentially

``` bash
mvn clean test
```

### ⚡ 2. Run Tests in Parallel

``` bash
mvn clean test -DsuiteXmlFile=testng.xml
```

Your `testng.xml` already runs tests in **parallel**:

``` xml
<suite name="Booking Parallel Suite" parallel="tests" thread-count="2">
    <test name="Booking Reservation Flow">
        <classes>
            <class name="Tests.BookingReservationTest"/>
        </classes>
    </test>

    <test name="Home Page Verification">
        <classes>
            <class name="Tests.HomePage_Verification_Test"/>
        </classes>
    </test>
</suite>
```

------------------------------------------------------------------------

## 📊 Reports

After each run, a new Extent Report is generated under:

    test-Report/
    └── Booking_Report_<timestamp>.html

Each new run **cleans older reports** and keeps only the latest one.

------------------------------------------------------------------------

## 🧪 Test Coverage Summary

  ----------------------------------------------------------------------------------
  Test                             Description
  -------------------------------- -------------------------------------------------
  **HomePage_Verification_Test**   Verifies booking.com home UI and essential
                                   elements

  **BookingReservationTest**       Full reservation flow: destination → dates →
                                   guests → rooms → search
  ----------------------------------------------------------------------------------

------------------------------------------------------------------------

## 🧠 Future Enhancements

-   🧩 Add **API layer tests** (for Booking API endpoints)
-   🔄 Integrate **Jenkins CI/CD**
-   📬 Email Extent Report automatically after each test run
-   🧱 Add **data-driven testing** with JSON

------------------------------------------------------------------------

## 🧑‍💻 Author

**Ata Pourfarivar**\
📍 Istanbul, Turkey\
💼 Test Automation Engineer\
📧 [LinkedIn Profile](https://www.linkedin.com/in/ata-pourfarivarnezhad/)

------------------------------------------------------------------------

## 🏁 License

This project is licensed under the **MIT License** --- free to use and
modify.
