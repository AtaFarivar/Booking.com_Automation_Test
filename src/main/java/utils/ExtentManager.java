package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.Objects;

public class ExtentManager {

    private static ExtentReports extent;
    private static final ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static ExtentReports getInstance() {
        if (extent == null) {

            // Create or verify report directory
            String reportDir = "test-Report";
            File folder = new File(reportDir);
            if (!folder.exists()) folder.mkdir();

            // 🧹 Keep only the latest 1 report file, delete the older ones
            File[] htmlFiles = folder.listFiles((dir, name) -> name.endsWith(".html"));
            if (htmlFiles != null && htmlFiles.length > 1) {
                // Sort by last modified time (descending)
                Arrays.sort(htmlFiles, Comparator.comparingLong(File::lastModified).reversed());
                // Skip first file (newest) and delete others
                for (int i = 1; i < htmlFiles.length; i++) {
                    htmlFiles[i].delete();
                }
            }

            // Generate unique file name using current timestamp
            String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
            String reportPath = reportDir + "/Booking_Report_" + timestamp + ".html";

            // Initialize Spark reporter
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setTheme(Theme.STANDARD);
            spark.config().setDocumentTitle("Automation Test Report");
            spark.config().setReportName("Booking.com Automation Test");

            // Attach reporter and set system info
            extent = new ExtentReports();
            extent.attachReporter(spark);
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Tester", System.getProperty("user.name"));
            extent.setSystemInfo("Browser", "Chrome");
        }
        return extent;
    }

    public static ThreadLocal<ExtentTest> getExtentTest() {
        return extentTest;
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
}
