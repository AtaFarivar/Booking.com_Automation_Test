package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void setDriver() {
        if (driver.get() == null) {

            // 🧩 Read from ConfigReader
            String browser = ConfigReader.get("browser").toLowerCase();
            String url = ConfigReader.get("url");

            WebDriver webDriver;

            switch (browser) {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    webDriver = new ChromeDriver();
                    System.out.println("🚀Launching Chrome browser...");
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    webDriver = new FirefoxDriver();
                    System.out.println("🔥Launching Firefox browser...");
                    break;

                case "edge":
                    WebDriverManager.edgedriver().setup();
                    webDriver = new EdgeDriver();
                    System.out.println("🟦Launching Edge browser...");
                    break;

                case "safari":
                    webDriver = new SafariDriver();
                    System.out.println("🧭Launching Safari browser...");
                    break;

                default:
                    throw new RuntimeException("❌Invalid browser name in Config.properties: " + browser);
            }

            // 🌍 Common setup
            webDriver.manage().window().maximize();
            webDriver.get(url);
            driver.set(webDriver);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
