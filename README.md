# 🧭 Booking.com Automation Test Project

## 📌 Project Overview
This project is a **Selenium Test Automation Framework** built in **Java (JDK 16)** using **TestNG** for parallel execution and **Extent Reports** for advanced HTML reporting.  
It automates hotel search and booking flow on [Booking.com](https://www.booking.com) — including destination search, date selection, guest and room configuration.

---

## ⚙️ Tech Stack

| Component | Tool |
|------------|------|
| Programming Language | Java 16 |
| Build Tool | Maven |
| Test Framework | TestNG |
| UI Automation | Selenium WebDriver |
| Reporting | Extent Reports |
| Driver Management | WebDriverManager |
| Configuration | Config.properties |
| Parallel Execution | TestNG XML |
| IDE | IntelliJ IDEA Community |

---

## 🗂️ Project Structure
```
BookingTests/
 ├── src/
 │   ├── main/
 │   │   ├── java/
 │   │   │   ├── models/              → Element locators
 │   │   │   ├── pages/               → Page classes (POM)
 │   │   │   └── utils/               → Utilities (DriverFactory, ConfigReader, ExtentManager)
 │   │   └── resources/
 │   │        └── Config.properties   → Browser and URL settings
 │   └── test/
 │        └── java/
 │             └── Tests/
 │                  ├── BookingReservationTest.java
 │                  └── HomePage_Verification_Test.java
 ├── testng.xml                       → Defines suite & parallel execution
 ├── pom.xml                          → Maven dependencies
 ├── README.md                        → Project documentation
 └── test-Report/                     → HTML reports (Extent)
```

---

## 🔧 Configuration File — `Config.properties`
Define your preferred browser and base URL:

```properties
browser=chrome
url=https://www.booking.com
```

**Supported browsers:**  
🟢 Chrome | 🦊 Firefox | 🟦 Edge | 🧭 Safari  

---

## 🧩 ConfigReader.java
Utility class that loads configuration values automatically.

```java
String browser = ConfigReader.get("browser");
String url = ConfigReader.get("url");
```

---

## 🚀 DriverFactory.java
Uses `switch-case` to launch browsers dynamically:

```java
switch (browser) {
    case "chrome":
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
    case "firefox":
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        break;
    case "edge":
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
    case "safari":
        driver = new SafariDriver();
        break;
    default:
        throw new RuntimeException("Invalid browser in Config.properties");
}
```

---

## 🧪 Parallel Execution with TestNG
Run tests in parallel using multiple threads for faster execution:

```xml

<suite name="Booking Parallel Suite" parallel="classes" thread-count="2" verbose="1">

    <test name="Booking Automation Tests">
        <packages>
            <package name="Tests"/>
        </packages>
    </test>

</suite>

```

---

## 📊 Reports
After execution, HTML reports are generated automatically in:
```
test-Report/Booking_Report_<timestamp>.html
```

Each report includes:
- Test status (PASS/FAIL/SKIP)
- Screenshots (optional)
- Execution timeline
- Environment and browser info

---

## ▶️ How to Run

### 🖥️ Run from IntelliJ IDEA
Right-click on `testng.xml` → **Run 'testng.xml'**

### 🧩 Run from Terminal (Maven)
```bash
mvn clean test
```

### 🌐 Change Browser
Just edit your `Config.properties`:
```properties
browser=edge
```

---

## 💡 Features

✅ Parallel test execution  
✅ Dynamic browser configuration  
✅ Extent HTML reporting  
✅ Thread-safe WebDriver instance  
✅ Clear POM-based architecture  
✅ Configurable URL and environment  

---

## 👨‍💻 Author

**Ata Farivar** — QA Automation Engineer  
📎 [LinkedIn Profile](https://www.linkedin.com/in/ata-pourfarivarnezhad/)  

---

## 🏁 License
This project is intended for educational and professional QA automation portfolio use.
